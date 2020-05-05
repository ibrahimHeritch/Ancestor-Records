package com.uncc.ticket.controller;

import com.uncc.ticket.model.BlogEntity;
import com.uncc.ticket.model.PersonEntity;
import com.uncc.ticket.model.RelationEntity;
import com.uncc.ticket.model.UsersEntity;
import com.uncc.ticket.service.PersonService;
import com.uncc.ticket.service.RelationService;
import com.uncc.ticket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        if(person.getFollowing()==null){
            person.setFollowing(new ArrayList<PersonEntity>());
        }
        person.addFollowing(personService.findById(id));
        personService.storePerson(person);
        return "redirect:/";
    }

    @RequestMapping(value = "/persons/addPersons", method = RequestMethod.GET)
    public String addPerson(Model model) {
        model.addAttribute("person", new PersonEntity());
        return "persons/AddPerson";
    }

    @RequestMapping(value = "/persons/addPersons", method = RequestMethod.POST)
    public String storePerson(@ModelAttribute(name = "person") @Valid PersonEntity person, BindingResult bindingResult) {
        personService.storePerson(person);
        return "redirect:/";
    }
}
