package com.ernstblignaut.pinkroccade.services;

import com.ernstblignaut.pinkroccade.models.Person;
import com.ernstblignaut.pinkroccade.pojo.PersonPOJO;
import com.ernstblignaut.pinkroccade.repositories.PersonRepo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;

    public List<Person> getAll(){
        return personRepo.findAll();
    }

    // Requirement 3
    public String getFilteredPersonsBase64() throws IOException {
        List<Person> result = new ArrayList<>();

        for(Person value : getAll()){
            if(value.getPartner() != null) {
                if(haveThreeChildren(value) && haveThreeChildren(value.getPartner())
                        && isOneBelow18(value.getChildren())) {
                    result.add(value);
                }
            }
        }

        // Requirement 3b Base64 encode CSV file
        File file = new FileUtilityService().getCSV(result);
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        file.delete();

        return encodedString;
    }

    public Boolean haveThreeChildren(Person person) {
        return person.getChildren().size() >= 3;
    }

    public Boolean isOneBelow18(List<Person> children) {
        LocalDate today = LocalDate.now();
        LocalDate birthday;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate;
        for(Person child: children) {
            tempDate = formatter.format(child.getBirthDate());
            birthday = LocalDate.parse(tempDate);
            if(calculateAge(birthday, today) < 18) {
                return true;
            }
        }
        return false;
    }

    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    // Requirement 4
    // Inserting a New Person
    public Person insertPerson(Person person) {
        personRepo.save(person);
        return person;
    }

    // Deleting a Person
    public boolean deletePerson(Long id) {
        Person person = personRepo.findById(id).get();
        personRepo.delete(person);
        return true;
    }

    // Updating a Person
    public Person updatePerson(Long id, Person person) {
        Person updatedPerson = personRepo.findById(id).get();
        updatedPerson.setBirthDate(person.getBirthDate());
        updatedPerson.setName(person.getName());
        if(person.getParent1() != null || person.getParent1().getId() != null) {
            updatedPerson.setParent1(personRepo.findById(person.getParent1().getId()).get());
        } else {
            updatedPerson.setParent1(null);
        }

        if(person.getParent2() != null || person.getParent2().getId() != null) {
            updatedPerson.setParent2(personRepo.findById(person.getParent2().getId()).get());
        } else {
            updatedPerson.setParent2(null);
        }

        if(person.getPartner() != null || person.getPartner().getId() != null) {
            updatedPerson.setPartner(personRepo.findById(person.getPartner().getId()).get());
        } else {
            updatedPerson.setPartner(null);
        }

        if(person.getChildren() != null && person.getChildren().size() > 0 ) {
            List<Person> children = new ArrayList<>();
            for(Person entry: person.getChildren()) {
                children.add(personRepo.findById(entry.getId()).get());
            }
            updatedPerson.setChildren(children);
        }

        personRepo.save(updatedPerson);
        return person;
    }

    //Requirement 5
    public List<PersonPOJO> getPersonsSortedById(){
        List<Person> persons = personRepo.findAllByOrderByIdDesc();
        List<PersonPOJO> result = new ArrayList<>();
        PersonPOJO tempPerson;
        for(Person person: persons) {
            tempPerson = new PersonPOJO();
            tempPerson.setName(person.getName());
            tempPerson.setId(person.getId());
            if(person.getParent2() != null & person.getParent1() != null){
                tempPerson.setParents(person.getParent1().getName() + ", " + person.getParent2().getName());
            }
            if(person.getPartner() != null) {
                tempPerson.setPartner(person.getPartner().getName());
            }
            result.add(tempPerson);
        }
        return result;
    }
}
