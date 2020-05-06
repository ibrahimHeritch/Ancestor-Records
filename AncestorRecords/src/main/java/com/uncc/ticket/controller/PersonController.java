package com.uncc.ticket.controller;

import com.uncc.ticket.model.BlogEntity;
import com.uncc.ticket.model.PersonEntity;
import com.uncc.ticket.model.RelationEntity;
import com.uncc.ticket.model.UsersEntity;
import com.uncc.ticket.service.PersonService;
import com.uncc.ticket.service.RelationService;
import com.uncc.ticket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public String showSearchResults(Model model, Principal principal) {
        UsersEntity user = usersService.findByEmail(principal.getName());
        PersonEntity person = user.getPerson();
        model.addAttribute("relation", new RelationEntity(person,person,"unknown"));
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("user", user.getPerson());
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

    //Sets relation
    @RequestMapping(value = "/persons/family/{id}", method = RequestMethod.POST)
    public String addRelation(Model model, @ModelAttribute(name = "relation") @Valid RelationEntity Relation, @PathVariable("id") Long id, @RequestParam("relationType") String relationType, @RequestParam("otherPerson") PersonEntity otherPerson, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "search/searchResults";
        }
        try {
            PersonEntity person1 = usersService.findByEmail(principal.getName()).getPerson();
            PersonEntity person2 = otherPerson;
            RelationEntity r = new RelationEntity(person1, person2, relationType);
            relationService.storeRelation(r);
            return "redirect:/";
        } catch (DataIntegrityViolationException e) {
            return "search/searchResults";
        }
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
    
    @RequestMapping(value = "/persons/myProfile", method = RequestMethod.GET)
    public String profile(Model model, Principal principal) {
        UsersEntity user = usersService.findByEmail(principal.getName());
        PersonEntity person = user.getPerson();
        model.addAttribute("person", person);
        return "persons/profile";
    }

    @RequestMapping(value = "/persons/profiles/{id}", method = RequestMethod.GET)
    public String showProfile(Model model, Principal principal, @PathVariable("id") Long id) {
        PersonEntity person = personService.findById(id);
        model.addAttribute("person", person);

        return "persons/profile";



    }
}
