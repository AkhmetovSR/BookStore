package com.example.bookstore.config;

import com.example.bookstore.services.PersonDetailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class
SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncode(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                //.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/authentication", "/error", "/registration", "/product/info/{id}", "/product/info", "/index", "/", "", "/search/allPerson", "/img/{}", "/infoProductAllperson", "/resources/**", "/static/**", "/static.css/**", "/index.css", "/css/**", "/image/**", "/user/index", "/static/css/index.css").permitAll()
                .requestMatchers("/admin", "/productAdd", "/admin/product/add", "/addProduct", "/getPerson", "/admin/changeRole", "/personInfo/{id}", "/changeRole/{id}", "/allorder","/allOrders", "/updateStatus", "/orderInfo", "/admin/product/delete/{id}", "/order/search", "/foundorder").hasRole("ADMIN")
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().loginPage("/authentication")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/personalAccount", true)
                .failureUrl("/authentication?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
        return http.build();
    }
    private final PersonDetailService personDetailService;

    public SecurityConfig(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }

    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder.userDetailsService(personDetailService)
                    .passwordEncoder(getPasswordEncode());
        }
    }

