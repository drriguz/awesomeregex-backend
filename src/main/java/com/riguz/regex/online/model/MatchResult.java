package com.riguz.regex.online.model;

import java.util.List;

public class MatchResult {

  private String source;
  private boolean matched;
  private List<String> groups;
  private String replaced;

  public MatchResult() {
  }

  public MatchResult(String source, boolean matched) {
    this.source = source;
    this.matched = matched;
  }

  public MatchResult(String source, String replaced) {
    this.source = source;
    this.replaced = replaced;
  }

  public MatchResult(String source, List<String> groups) {
    this.source = source;
    this.groups = groups;
    this.matched = !groups.isEmpty();
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public boolean isMatched() {
    return matched;
  }

  public void setMatched(boolean matched) {
    this.matched = matched;
  }

  public List<String> getGroups() {
    return groups;
  }

  public void setGroups(List<String> groups) {
    this.groups = groups;
  }

  public String getReplaced() {
    return replaced;
  }

  public void setReplaced(String replaced) {
    this.replaced = replaced;
  }
}
