package com.uncc.ticket.controller;

import com.uncc.ticket.model.BlogEntity;
import com.uncc.ticket.model.PersonEntity;
import com.uncc.ticket.model.RelationEntity;
import com.uncc.ticket.service.PersonService;
import com.uncc.ticket.service.RelationService;
import com.uncc.ticket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    private final UsersService usersService;
    private PersonService personService;
    private RelationService relationService;
    @Autowired

    public PersonController(PersonService personService, UsersService usersService,RelationService relationService) {
        this.personService = personService;
        this.usersService=usersService;
        this.relationService=relationService;
    }

    @RequestMapping(value = "/search/searchResults", method = RequestMethod.GET)
    public String showSearchResults(Model model) {
        model.addAttribute("persons", personService.getAllPersons()); //change this
        return "search/searchResult";
    }

    @RequestMapping(value = "/persons/follow/{id}", method = RequestMethod.GET)
    public String addFollower(Model model,@PathVariable("id") Long id, Principal principal) {
        PersonEntity person = usersService.findByEmail(principal.getName()).getPerson();

        if(person.getFollowing()==null){
            person.setFollowing(new ArrayList<PersonEntity>());
        }
        person.addFollowing(personService.findById(id));
        personService.storePerson(person);
        return "redirect:/";
    }

    @RequestMapping(value = "/persons/family/{id}", method = RequestMethod.GET)
    public String addRelation(Model model,@PathVariable("id") Long id, Principal principal) {
        PersonEntity person = usersService.findByEmail(principal.getName()).getPerson();
        RelationEntity r= new RelationEntity(person,personService.findById(id),"UNKNOWN");
        relationService.storeRelation(r);
        return "redirect:/";
    }
}
