package com.ernstblignaut.pinkroccade.controllers;

import com.ernstblignaut.pinkroccade.models.Person;
import com.ernstblignaut.pinkroccade.pojo.PersonPOJO;
import com.ernstblignaut.pinkroccade.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PeopleController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/")
    public String testAPI() {
        return "Welcome, The API is working...";
    }

    // Requirement 3
    // Retrieve Filtered Persons in CSV Base64 format
    @GetMapping(value = "/requirement3")
    public String getFilteredPeopleBase64() throws IOException {
        return personService.getFilteredPersonsBase64();
    }

    // Requirement 4
    // Insert new Person
    @PostMapping(value = "/insert")
    public Person insertPerson(@RequestBody Person person) {
        return personService.insertPerson(person);
    }

    // Requirement 4
    // Update existing Person
    @PutMapping(value = "/update/{id}")
    public Person updateUser(@PathVariable Long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    // Requirement 4
    // Delete a Person
    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        if(personService.deletePerson(id)){
            return "Deleted...";
        }
        return "Failed to delete...";
    }

    // Requirement 5
    // Retrieve all Persons ordered by Id
    @GetMapping(value = "/people")
    public List<PersonPOJO> getPeople() {
        return personService.getPersonsSortedById();
    }
}
