package com.example.bookstore.controllers;

import com.example.bookstore.repositories.ProductRepository;
import com.example.bookstore.services.PersonDetailService;
import com.example.bookstore.services.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Value("${upload.path}")
    private String uploadPath;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final PersonDetailService personDetailService;

    public ProductController(ProductService productService, ProductRepository productRepository, PersonDetailService personDetailService) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.personDetailService = personDetailService;
    }

    @GetMapping("/")
    public String getAllProductAllPerson(Model model){
        model.addAttribute("allProducts", productService.getAllProduct());
        return "index";
    }

    @GetMapping("/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProduct(id));
        return "/product/infoProduct";
    }

    @PostMapping("/product/search")
    public String searchProduct(@RequestParam("search") String search, @RequestParam("from") String from, @RequestParam("to") String to, @RequestParam(value = "price", required = false, defaultValue = "") String price, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String person_id = authentication.getName();

        //Если введено только название (поиск только по наименованию товара)
        if(!search.isEmpty() & from.isEmpty() & to.isEmpty() & price.isEmpty()){
            model.addAttribute("searchProduct", productRepository.findByTitleContainingIgnoreCase(search));
        }
        //Если введено название и указана цена от и до
        else if(!search.isEmpty() & !from.isEmpty() & !to.isEmpty()){
            //Если сортировка не указана и ищем только название и диапазон
            if(price.equals("")){
                model.addAttribute("searchProduct", productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(search.toLowerCase(), Integer.parseInt(from), Integer.parseInt(to)));
            }

            else if(price.equals("sorted_by_ascending_price")){
                model.addAttribute("searchProduct", productRepository.findByTitleOrderByPriceAsc(search.toLowerCase(), Integer.parseInt(from), Integer.parseInt(to)));
            }

            else if(price.equals("sorted_by_descending_price")){
                model.addAttribute("searchProduct", productRepository.findByTitleOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(from), Float.parseFloat(to)));
            }
        }

        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("valueSearch", search);
        model.addAttribute("valuePriceFrom", from);
        model.addAttribute("valuePriceTo", to);
        model.addAttribute("sorted_by_ascending_price", price);
        model.addAttribute("sorted_by_descending_price", price);
        model.addAttribute("account", person_id);
        return "/product";
    }

    @PostMapping("/search/allPerson")
    public String searchProductAllPeron(@RequestParam("search") String search, @RequestParam("from") String from, @RequestParam("to") String to, @RequestParam(value = "price", required = false, defaultValue = "") String price, Model model){

        //Если введено только название (поиск только по наименованию товара)
        if(!search.isEmpty() & from.isEmpty() & to.isEmpty() & price.isEmpty()){
            model.addAttribute("foundProducts", productRepository.findByTitleContainingIgnoreCase(search));
        }
        //Если введено название и указана цена от и до
        else if(!search.isEmpty() & !from.isEmpty() & !to.isEmpty()){
            //Если сортировка не указана и ищем только название и диапазон
            if(price.equals("")){
                model.addAttribute("foundProducts", productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(search.toLowerCase(), Integer.parseInt(from), Integer.parseInt(to)));
            }

            else if(price.equals("sorted_by_ascending_price")){
                model.addAttribute("foundProducts", productRepository.findByTitleOrderByPriceAsc(search.toLowerCase(), Integer.parseInt(from), Integer.parseInt(to)));
            }

            else if(price.equals("sorted_by_descending_price")){
                model.addAttribute("foundProducts", productRepository.findByTitleOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(from), Float.parseFloat(to)));
            }
        }

        model.addAttribute("allProducts", productService.getAllProduct());
        model.addAttribute("valueSearch", search);
        model.addAttribute("valuePriceFrom", from);
        model.addAttribute("valuePriceTo", to);
        model.addAttribute("sorted_by_ascending_price", price);
        model.addAttribute("sorted_by_descending_price", price);
        return "index";
    }
}
