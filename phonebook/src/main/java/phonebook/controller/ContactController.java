package phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import phonebook.entity.Contact;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {
    private List<Contact> contacts;


    public ContactController() {
        this.contacts = new ArrayList<>();
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("contacts", this.contacts);
        return modelAndView;
    }

    @PostMapping("/")
    public String storeContact(Contact contact) {
        this.contacts.add(contact);
        return "redirect:/";
    }

    @DeleteMapping("/contacts/{name}")
    public String deleteContact(@PathVariable String name) {
        Contact contact = this.contacts.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        this.contacts.remove(contact);

        return "redirect:/";
    }
}
