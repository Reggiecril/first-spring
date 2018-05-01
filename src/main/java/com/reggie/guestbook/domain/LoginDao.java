package com.reggie.guestbook.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao extends CrudRepository<User , Long> {

    public List<User> findByUsernameAndPassword(String name, String password);
}