package com.silicatewastes.weaselware2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

  @Id
  @GeneratedValue
  private int id;

  @NotNull
  @Size(min=3, max=15)
  private String name;

  @OneToMany
  @JoinColumn(name = "event_id")
  private List<Task> tasks = new ArrayList<>();

  public Event() {}

  public Event(String name) { this.name = name; }

  public int getId() { return id; }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public List<Task> getTasks() { return tasks; }
}
