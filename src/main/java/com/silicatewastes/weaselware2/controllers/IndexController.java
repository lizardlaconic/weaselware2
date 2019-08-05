package com.silicatewastes.weaselware2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("tasks")
public class IndexController {
  //eventually this will be an ArrayList, then a DB table of class type Comment or something,
  //will contain a String comment but also another class similar to CheeseType.  Event.
  static ArrayList<String> comments= new ArrayList<String>();

  @RequestMapping(value = "")
  public String index(Model model) {
    model.addAttribute("comments", comments);
    model.addAttribute("title", "Tasks");

    return "tasks/index";
  }

  //just adding this for the sake of having everything from the tutorials.
  //gut it out later, this is not at all how i want this to work.
  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String displayAddCommentForm(Model model) {
    model.addAttribute("title", "Add Comment");

    return "tasks/add";
  }

  //same, just for reference, trash this later
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public String processAddCommentForm(@RequestParam String commentName) {
    comments.add(commentName);

    return "redirect:";

  }
}
