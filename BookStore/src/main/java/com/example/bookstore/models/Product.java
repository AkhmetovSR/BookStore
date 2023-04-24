package com.example.bookstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "product")
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Пожалуйста, введите название книги")
    @Column(name="title", nullable = false, columnDefinition = "text", unique = true)
    private String title;

    @NotEmpty(message = "Пожалуйста, укажите автора")
    @Column(name="author", nullable = false, columnDefinition = "text")
    private String author;

    @Min(value = 10, message = "Пожалуйста, укажите корректное количество страниц")
    @Column(name="pages", nullable = false, columnDefinition = "int")
    private int pages;

    @NotEmpty(message = "Пожалуйста, укажите страну")
    @Column(name="country", nullable = false, columnDefinition = "text")
    private String country;

    @NotEmpty(message = "Пожалуйста, укажите год издания")
    @Column(name="year_of_issue", nullable = false, columnDefinition = "text")
    private String year_of_issue;

    @NotEmpty(message = "Пожалуйста, введите наименование издательства")
    @Column(name="publisher", nullable = false, columnDefinition = "text")
    private String publisher;

    @NotEmpty(message = "Пожалуйста, введите описание")
    @Column(name="descriptions", nullable = false, columnDefinition = "text")
    private String descriptions;

    @Min(value = 1, message = "Пожалуйста, укажите цену товара")
    @Column(name="price", nullable = false, columnDefinition = "int")
    private int price;

    @OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, mappedBy = "product")
    private List<Image> imageList = new ArrayList<>();

    public void addImageProduct(Image image){
        image.setProduct(this);
        imageList.add(image);
    }

    @ManyToMany
    @JoinTable(name="product_cart", joinColumns = @JoinColumn(name="product_id"), inverseJoinColumns = @JoinColumn(name="person_id"))
    private List<Person> personList;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Order> orderList;

    //----------------------------------------------------------------------------------------------
    public Product(){

    }

    //----------------------------------------------------------------------------------------------


    public Product(String title, String author, int pages, String country, String year_of_issue, String publisher, String descriptions, int price, List<Image> imageList) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.country = country;
        this.year_of_issue = year_of_issue;
        this.publisher = publisher;
        this.descriptions = descriptions;
        this.price = price;
        this.imageList = imageList;
    }

    //----------------------------------------------------------------------------------------------
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //----------------------------------------------------------------------------------------------
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    //----------------------------------------------------------------------------------------------
    public int getPages(){
        return pages;
    }
    public void setPages(int pages){
        this.pages = pages;
    }

    //----------------------------------------------------------------------------------------------
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }

    //----------------------------------------------------------------------------------------------
    public String getYear_of_issue(){
        return year_of_issue;
    }
    public void setYear_of_issue(String year_of_issue){
        this.year_of_issue = year_of_issue;
    }

    //----------------------------------------------------------------------------------------------
    public String getPublisher(){
        return publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    //----------------------------------------------------------------------------------------------
    public String getDescriptions(){
        return descriptions;
    }
    public void setDescriptions(String descriptions){
        this.descriptions = descriptions;
    }

    //----------------------------------------------------------------------------------------------
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }

    //----------------------------------------------------------------------------------------------
    public List<Image> getImageList() {
        return imageList;
    }
    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    //----------------------------------------------------------------------------------------------
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", country='" + country + '\'' +
                ", year_of_issue='" + year_of_issue + '\'' +
                ", publisher='" + publisher + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", price=" + price +
                ", imageList=" + imageList +
                '}';
    }
}
