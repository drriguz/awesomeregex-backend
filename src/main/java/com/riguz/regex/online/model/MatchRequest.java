package com.riguz.regex.online.model;

import java.util.List;

public class MatchRequest {

  private List<String> sources;
  private String regex;
  private String replaceTo;

  public MatchRequest() {
  }

  public List<String> getSources() {
    return sources;
  }

  public void setSources(List<String> sources) {
    this.sources = sources;
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  public String getReplaceTo() {
    return replaceTo;
  }

  public void setReplaceTo(String replaceTo) {
    this.replaceTo = replaceTo;
  }
}
