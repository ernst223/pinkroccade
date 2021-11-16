package com.ernstblignaut.pinkroccade.repositories;

import com.ernstblignaut.pinkroccade.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Long> {
    public List<Person> findAllByOrderByIdDesc();
}
