package com.silicatewastes.weaselware2.controllers;

import com.silicatewastes.weaselware2.models.Event;
import com.silicatewastes.weaselware2.models.Task;
import com.silicatewastes.weaselware2.models.data.EventDao;
import com.silicatewastes.weaselware2.models.data.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("tasks")
public class IndexController {

  //he got rid of private in the demo?
  @Autowired
  private TaskDao taskDao;

  @Autowired
  EventDao eventDao;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    model.addAttribute("comments", taskDao.findAll());
    model.addAttribute("title", "Tasks");

    model.addAttribute(new Task());
    model.addAttribute("eventTypes", eventDao.findAll());

    return "tasks/index";
  }

  @RequestMapping(value = "delete", method = RequestMethod.POST)
  public String indexdelete(Model model, @RequestParam Integer done) {
    taskDao.deleteById(done);
    model.addAttribute("comments", taskDao.findAll());
    model.addAttribute("title", "Tasks");

    model.addAttribute(new Task());
    model.addAttribute("eventTypes", eventDao.findAll());

    return "tasks/index";
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public String indexprocess(Model model,
                             @ModelAttribute @Valid Task newTask, Errors errors,
                             @RequestParam(value = "eventId", required = false) Integer eventId) {

    if(!errors.hasErrors()) {
      Optional<Event> event = eventDao.findById(eventId);
      newTask.setEvent(event.get());
      taskDao.saveAndFlush(newTask);
      taskDao.clear();
    }

    //load up all tasks
    List<Task> comments = taskDao.findAll();
    model.addAttribute("comments", comments);
    newTask.Clear();

    model.addAttribute("title", "Tasks");

    //why in the world do i not have to pass newTask back to the html?
    model.addAttribute("eventTypes", eventDao.findAll());

    /////

    return "tasks/index";
  }

  @RequestMapping(value = "event", method = RequestMethod.GET)
  public String event(Model model, @RequestParam int id) {
    Optional<Event> event = eventDao.findById(id);
    List<Task> tasks = event.get().getTasks();
    model.addAttribute("tasks", tasks);
    model.addAttribute("title", "Create New Event Type" + event.get().getName());

    return "tasks/index";
  }
}
