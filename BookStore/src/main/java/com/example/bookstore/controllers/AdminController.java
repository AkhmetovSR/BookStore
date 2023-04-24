package com.example.bookstore.controllers;

import com.example.bookstore.enumm.Status;
import com.example.bookstore.models.Image;
import com.example.bookstore.models.Order;
import com.example.bookstore.models.Product;
import com.example.bookstore.repositories.OrderRepository;
import com.example.bookstore.repositories.PersonRepository;
import com.example.bookstore.services.OrderService;
import com.example.bookstore.services.PersonService;
import com.example.bookstore.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String admin(Model model){

        model.addAttribute("products", productService.getAllProduct());
        return "admin";
    }

    @Value("${upload.path}")
    private String uploadPath;
    private final ProductService productService;
    private final PersonService personService;

    private final PersonRepository personRepository;

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    public AdminController(ProductService productService, PersonService personService, PersonRepository personRepository, OrderRepository orderRepository, OrderService orderService) {
        this.productService = productService;
        this.personService = personService;
        this.personRepository = personRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping("/admin/product/add")
    public String addBook(Model model){
        model.addAttribute("product", new Product());
        return "product/addProduct";
    }

    @PostMapping("/admin/product/add")
    public String addBook(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one")MultipartFile file_one, @RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three, Model model) throws IOException {
        if(bindingResult.hasErrors()){
            return "product/addProduct";
        }
        if(file_one != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(file_two != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(file_three != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        productService.saveProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.getProduct(id));
        return "product/editProduct";
    }
    @PostMapping("/admin/product/edit/{id}")
    public String editProductSave(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "product/editProduct";
        }
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }


    //Посмотреть список пользователей
    @GetMapping("/allperson")
    public String getAllPerson(Model model){
        model.addAttribute("persons", personService.getAllPerson());
        return "/allperson";
    }

    @GetMapping("/personInfo/{id}")
    public String getPrson(@PathVariable("id") int id, Model model){
        model.addAttribute(personService.getPersonById(id));
        return "personinfo";
    }

    @PostMapping("/changeRole/{id}")
    public String changePersonRole(@PathVariable("id") int id, Model model){
        personService.updatePersonRole(id);
        return "redirect:/allperson";
    }

    @GetMapping("/allorder")
    public String getOrder(Model model){
        model.addAttribute("orders", orderRepository.findAll());
        List<Order> orderList = orderRepository.findAll();
        for(Order order : orderList){
            order.getId();
        }
        return "allOrders";
    }

    @GetMapping("/updateStatus/{id}")
    public String updateOrderStatus(@PathVariable("id") int id, Model model){
        model.addAttribute("updateOrder", orderRepository.findById(id));
        return "orderInfo";
    }

    @PostMapping("/updateStatus/{id}")
    public String editOrderStatus(@ModelAttribute("updateOrder") Order order, Model model){
        orderService.updateStatus(order.getId(), order);
        model.addAttribute("updateOrder", orderRepository.findById(order.getId()));
        return "orderInfo";
    }

    @PostMapping("/order/search")
    public String searchProduct(@RequestParam("search") String search, Model model) {
        int search_value = Integer.parseInt(search);
        System.out.println(orderRepository.findById(search_value).getId());
        model.addAttribute("foundOrder", orderRepository.findById(search_value));
        return "foundorder";
    }
}
