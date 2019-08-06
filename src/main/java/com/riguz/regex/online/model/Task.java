package com.riguz.regex.online.model;

import java.util.List;

public class Task {

  private int id;
  private List<String> sources;
  private List<String> expectedOutput;
  private String title;
  private TaskType type;
  private List<String> answers;
  private String description;
  private int level;

  public Task() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<String> getSources() {
    return sources;
  }

  public void setSources(List<String> sources) {
    this.sources = sources;
  }

  public List<String> getExpectedOutput() {
    return expectedOutput;
  }

  public void setExpectedOutput(List<String> expectedOutput) {
    this.expectedOutput = expectedOutput;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TaskType getType() {
    return type;
  }

  public void setType(TaskType type) {
    this.type = type;
  }

  public List<String> getAnswers() {
    return answers;
  }

  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
