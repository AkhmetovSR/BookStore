package com.example.bookstore.services;

import com.example.bookstore.models.Person;
import com.example.bookstore.repositories.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person findByEmail(Person person){
        Optional<Person> person_db = personRepository.findByEmail(person.getEmail());
        return person_db.orElse(null);
    }

    public Person getPersonById(int id){
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void registrationPerson(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("USER");
        personRepository.save(person);
    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    @Transactional
    public void updatePersonRole(int id){
        Optional<Person> person_cr = personRepository.findById(id);
        if((person_cr.get().getRole()).equals("ADMIN")){
            person_cr.get().setRole("USER");
            personRepository.save(person_cr.get());
        }
        else if((person_cr.get().getRole()).equals("USER")){
            person_cr.get().setRole("ADMIN");
            personRepository.save(person_cr.get());
        }
    }
}
