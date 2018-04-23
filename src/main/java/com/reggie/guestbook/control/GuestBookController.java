package com.reggie.guestbook.control;

import com.reggie.guestbook.domain.GuestBookEntry;
import com.reggie.guestbook.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuestBookController {
    @Autowired
    private GuestBookService guestBookService;
    @GetMapping("/")
    public List<GuestBookEntry> testMapping () {
        return guestBookService.findAllEntries ();
    }
}
