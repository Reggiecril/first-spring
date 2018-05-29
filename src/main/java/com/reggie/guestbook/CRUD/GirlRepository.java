package com.reggie.guestbook.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GirlRepository extends JpaRepository<Girl,Integer> {

    Girl findAllByAge(Integer integer);
    Girl findAllById(Integer integer);
}
