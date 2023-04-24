package com.example.bookstore.util;

import com.example.bookstore.models.Person;
import com.example.bookstore.services.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personService.findByEmail(person) != null){
            errors.rejectValue("email", "1", "Данный email уже зарегестрирован");
        }
    }
}
