package com.example.bookstore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @NotEmpty(message = "Пожалуйста, введите имя")
    @Column(name = "first_name", nullable = false, columnDefinition = "text")
    String first_name;

    @NotEmpty(message = "Пожалуйста, введите фамилию")
    @Column(name = "last_name", nullable = false, columnDefinition = "text")
    String last_name;

    @Column(name = "patronymic", nullable = true, columnDefinition = "text")
    String patronymic;

    @Column(name = "age", nullable = false, columnDefinition = "int")
    @Min(value = 14, message = "Регистрация на сайте 14+")
    int age;

    @NotEmpty(message = "Пожалуйста, введите email")
    @Column(name = "email", nullable = false, columnDefinition = "text", unique = true)
    @Email(message = "Email указан некорректно")
    String email;

    @NotEmpty(message = "Пожалуйста, введите номер телефона")
    @Column(name = "phone", nullable = false, columnDefinition = "text")
    @Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})$", message = "Номер телефона введен некорректно")
    String phone;

    @NotEmpty(message = "Пожалуйста, введите пароль")
    @Column(name = "password", nullable = false, columnDefinition = "text")
    String password;

    @Column(name="role")
    private String role;

    @ManyToMany
    @JoinTable(name="product_cart", joinColumns = @JoinColumn(name="person_id"), inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> productList;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<Order> orderList;

    //----------------------------------------------------------------------------------------------
    public Person(){

    }

    //----------------------------------------------------------------------------------------------
    public Person(String first_name, String last_name, String patronymic, int age, String email, String phone, String password, String role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    //----------------------------------------------------------------------------------------------
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    //----------------------------------------------------------------------------------------------
    public String getFirst_name(){
        return first_name;
    }
    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    //----------------------------------------------------------------------------------------------
    public String getLast_name(){
        return last_name;
    }
    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    //----------------------------------------------------------------------------------------------
    public String getPatronymic(){
        return patronymic;
    }
    public void setPatronymic(String patronymic){
        this.patronymic = patronymic;
    }

    //----------------------------------------------------------------------------------------------
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    //----------------------------------------------------------------------------------------------
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //----------------------------------------------------------------------------------------------
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    //----------------------------------------------------------------------------------------------
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    //----------------------------------------------------------------------------------------------
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
