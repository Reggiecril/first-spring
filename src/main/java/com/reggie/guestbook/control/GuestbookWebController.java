package com.reggie.guestbook.control;

import com.reggie.guestbook.domain.GuestBookEntry;
import com.reggie.guestbook.service.GuestBookService;
import com.reggie.guestbook.service.ScheduledTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
@RequestMapping("/user")
@Controller
public class GuestbookWebController {

    private static final String GUESTBOOK_TEMPLATE = "guestbook";
    private static final String ENTRIES_TEMPLATE_ID = "entries";
    private static final String HOMEPAGE_REDIRECT = "redirect:/user/";
    private static final String NEW_ENTRY_TEMPLATE_ID = "newEntry";
    private static final String GUESTBOOK_FORM_HEADER_ID = "formHeader";
    @Autowired
    private GuestBookService guestBookService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @GetMapping ("/")
    public String displayGuestBook (Model model) {

        model.addAttribute (GUESTBOOK_FORM_HEADER_ID, "Add a New Comment");
        model.addAttribute (ENTRIES_TEMPLATE_ID, this.guestBookService.findAllEntries ());
        model.addAttribute (NEW_ENTRY_TEMPLATE_ID, new GuestBookEntry());
        return GUESTBOOK_TEMPLATE;
    }
    @GetMapping ("/delete/{id}")
    public String deleteComment (@PathVariable Integer id) {
        this.guestBookService.deleteGuestBookEntryById (id);
        return HOMEPAGE_REDIRECT;
    }
    @PostMapping ("/")
    public String addComment (Model model,
                              @Valid @ModelAttribute (NEW_ENTRY_TEMPLATE_ID)
                                      GuestBookEntry newEntry, BindingResult bindingResult) {
        if (!bindingResult.hasErrors ()) {
            this.guestBookService.save (newEntry);
            return HOMEPAGE_REDIRECT;
        }
        else {
            model.addAttribute (GUESTBOOK_FORM_HEADER_ID, "Please Correct the Comment");
            model.addAttribute (ENTRIES_TEMPLATE_ID, this.guestBookService.findAllEntries ());
            return GUESTBOOK_TEMPLATE;
        }
    }
    @GetMapping ("update/{id}")
    public String editComment (Model model, @PathVariable Integer id) {
        model.addAttribute (ENTRIES_TEMPLATE_ID, this.guestBookService.findAllEntries ());
        model.addAttribute (GUESTBOOK_FORM_HEADER_ID, "Please Change the Comment");
        model.addAttribute (NEW_ENTRY_TEMPLATE_ID, this.guestBookService.findOne (id));
        return GUESTBOOK_TEMPLATE;
    }
    @PostMapping ("update/{id}")
    public String saveComment (Model model,
                               @PathVariable Integer id,
                               @Valid @ModelAttribute (NEW_ENTRY_TEMPLATE_ID) GuestBookEntry newEntry,
                               BindingResult bindingResult) {
        if (!bindingResult.hasErrors ()) {
            GuestBookEntry current = this.guestBookService.findOne (id);
            current.setUser (newEntry.getUser ());
            current.setComment (newEntry.getComment ());
            this.guestBookService.save (current);
            return HOMEPAGE_REDIRECT;
        }
        else {
            model.addAttribute (GUESTBOOK_FORM_HEADER_ID, "Please Correct the Comment");
            model.addAttribute (ENTRIES_TEMPLATE_ID, this.guestBookService.findAllEntries ());
            return GUESTBOOK_TEMPLATE;
        }
    }


}
