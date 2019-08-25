package com.silicatewastes.weaselware2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//copying demo for now. later every Task holds one Event class.
@Entity
public class Task {

  @Id
  @GeneratedValue
  private int id;

  @NotNull
  @Size(min=3, max=15)
  private String comment;

  @NotNull
  @Size(min=1, message = "enter something at least")
  private String extraComment;

  @ManyToOne
  private Event event;

  public Task(String comment, String extraComment) {
    this.comment = comment;
    this.extraComment = extraComment;
  }

  public Task() { }

  public int getId() {
    return id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getExtraComment() {
    return extraComment;
  }

  public void setExtraComment(String extraComment) {
    this.extraComment = extraComment;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public void Clear() {
    this.comment="";
    this.extraComment="";
  }

  /*public HotPotato getSpud() {
    return spud;
  }

  public void setSpud(HotPotato spud) {
    this.spud = spud;
  }*/
}
