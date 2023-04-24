package com.example.bookstore.services;
import com.example.bookstore.models.Person;
import com.example.bookstore.repositories.PersonRepository;
//import com.example.bookstore.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public String getRole(String email){
        Optional<Person> person = personRepository.findByEmail(email);
        String role = person.get().getRole();
        return role;
    }

    public int getPersonId(String email){
        Optional<Person> person = personRepository.findByEmail(email);
        int id = person.get().getId();
        return id;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByEmail(username);
        if(person.get().getEmail().isEmpty()){
            throw  new UsernameNotFoundException("нет");
        }
        UserDetails user = User.builder().username(person.get().getEmail()).password(person.get().getPassword()).roles(person.get().getRole()).build();
        return user;

    }
}