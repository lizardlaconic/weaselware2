package com.silicatewastes.weaselware2.controllers;

import com.silicatewastes.weaselware2.models.Event;
import com.silicatewastes.weaselware2.models.data.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("event")
public class EventController {

  @Autowired
  private EventDao eventDao;

  @RequestMapping(value = "")
  public String index(Model model) {
    model.addAttribute("events", eventDao.findAll());
    model.addAttribute("title", "Events");

    return "events/index";
  }

  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String displayAddEventForm(Model model) {
    model.addAttribute("title", "Create New Event Type");
    model.addAttribute(new Event());
    //model.addAttribute("eventTypes", eventDao.findAll());

    return "events/add";
  }

  @RequestMapping(value = "add", method = RequestMethod.POST)
  public String processAddEventForm(@ModelAttribute @Valid Event event, Errors errors, Model model) {

    if(errors.hasErrors()) {
      //why don't i have to pass newTask back to the html? like it get add above?
      model.addAttribute("title", "Create New Event Type");
      //model.addAttribute("eventTypes", eventDao.findAll());
      return "tasks/add";
    }

    //Optional<Event> event = eventDao.findById(eventId);
    //newTask.setEvent(event);
    eventDao.save(event);
    return "redirect:";
  }
}
