package com.example.bookstore.controllers;
import com.example.bookstore.enumm.Status;
import com.example.bookstore.models.Cart;
import com.example.bookstore.models.Order;
import com.example.bookstore.models.Person;
import com.example.bookstore.models.Product;
import com.example.bookstore.repositories.CartRepository;
import com.example.bookstore.repositories.OrderRepository;
import com.example.bookstore.services.PersonDetailService;
import com.example.bookstore.services.PersonService;
import com.example.bookstore.services.ProductService;
import com.example.bookstore.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;
    private final PersonValidator personValidator;
    private final PersonService personService;
    private final PersonDetailService personDetailService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    private final CartRepository cartRepository;

    public MainController(PersonValidator personValidator, PersonService personService, PersonDetailService personDetailService, ProductService productService, OrderRepository orderRepository, CartRepository cartRepository) {
        this.personValidator = personValidator;
        this.personService = personService;
        this.personDetailService = personDetailService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping("/personalAccount")
    public String getMainPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = personDetailService.getRole(authentication.getName());
        String person_id = authentication.getName();
        if(role.equals("ADMIN")){
            return "redirect:/admin";
        }
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("account", person_id);
        return "product";
    }

    @GetMapping("/authentication")
    public String login(){
        return "authentication";
    }

    @GetMapping("/registration")
    public String newUser(Model model){
        model.addAttribute("person", new Person());
        return "registration";
    }

    @PostMapping("/registration")
    public String resultRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        personService.registrationPerson(person);
        return "redirect:/authentication";
    }

    @GetMapping("/product/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProduct(id));
        return "infoProductAllperson";
    }

    @GetMapping("/cart/add/{id}")
    public String productAddToCart(@PathVariable("id") int id, Model model){
        Product product = productService.getProduct(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int person_id = personDetailService.getPersonId(authentication.getName());

        Cart cart = new Cart(person_id, product.getId());
        cartRepository.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int person_id = personDetailService.getPersonId(authentication.getName());
        List<Cart> cartList = cartRepository.findByPersonId(person_id);
        List<Product> productList = new ArrayList<>();
        for (Cart cart: cartList){
            productList.add(productService.getProduct(cart.getProductId()));
        }
        float price = 0;
        for(Product product : productList){
            price += product.getPrice();
        }
        model.addAttribute("price", price);
        model.addAttribute("cart_product", productList);
        return "cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteProductFromCart(Model model, @PathVariable("id") int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int person_id = personDetailService.getPersonId(authentication.getName());
        List<Cart> cartList = cartRepository.findByPersonId(person_id);
        List<Product> productList = new ArrayList<>();
        for (Cart cart: cartList){
            productList.add(productService.getProduct(cart.getProductId()));
        }
        cartRepository.deleteCartByProductId(id);
        return "redirect:/cart";
    }

    @GetMapping("/order/create")
    public String order(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int person_id = personDetailService.getPersonId(authentication.getName());
        List<Cart> cartList = cartRepository.findByPersonId(person_id);
        List<Product> productList = new ArrayList<>();
        for (Cart cart: cartList){
            productList.add(productService.getProduct(cart.getProductId()));
        }
        float price = 0;
        for(Product product : productList){
            price += product.getPrice();
        }

        for(Product product : productList){
            Order newOrder = new Order(product, personService.getPersonById(person_id), 1, product.getPrice(), Status.Принят);
            orderRepository.save(newOrder);
            cartRepository.deleteCartByProductId(product.getId());
        }
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int person_id = personDetailService.getPersonId(authentication.getName());
        List<Order> orderList = orderRepository.findByPerson(personService.getPersonById(person_id));
        model.addAttribute("orders", orderList);
        return "/orders";
    }

}
