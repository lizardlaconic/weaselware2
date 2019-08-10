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

import javax.validation.Valid;
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

  @RequestMapping(value = "")
  public String index(Model model) {
    model.addAttribute("comments", taskDao.findAll());
    model.addAttribute("title", "Tasks");

    return "tasks/index";
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public String indexprocess(Model model, @RequestParam int done) {
    taskDao.deleteById(done);

    model.addAttribute("comments", taskDao.findAll());
    model.addAttribute("title", "Tasks");

    return "tasks/index";
  }

  //just adding this for the sake of having everything from the tutorials.
  //gut it out later, this is not at all how i want this to work.
  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String displayAddCommentForm(Model model) {
    model.addAttribute("title", "Add Comment");
    model.addAttribute(new Task());
    model.addAttribute("eventTypes", eventDao.findAll());

    return "tasks/add";
  }

  //same, just for reference, trash this later
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public String processAddCommentForm(@ModelAttribute @Valid Task newTask, Errors errors,
                                      @RequestParam int eventId, Model model) {

    if(errors.hasErrors()) {
      //why don't i have to pass newTask back to the html? like it get add above?
      model.addAttribute("title", "Add Comment");
      model.addAttribute("eventTypes", eventDao.findAll());
      return "tasks/add";
    }

    Optional<Event> event = eventDao.findById(eventId);
    newTask.setEvent(event.get());
    taskDao.save(newTask);
    return "redirect:";
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
