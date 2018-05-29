package com.reggie.guestbook.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud")
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @GetMapping(value = "/find")
    public List<Girl> findGirl(){
        return girlRepository.findAll();

    }
    @PostMapping(value = "/find")
    public Girl addGirl(){
        Girl girl=new Girl();
        girl.setAge(12);
        girl.setCupSize("b");
        girl.setHair("long");
        return girlRepository.save(girl);
    }
    @GetMapping(value = "/find/{id}")
    public Girl findSingalGirl(@PathVariable("id") Integer id){


        return girlRepository.findAllById(id);
    }
    @DeleteMapping(value="/delete/{id}")
    public void deleteGirl(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
    }
}
