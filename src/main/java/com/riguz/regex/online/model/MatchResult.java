package com.riguz.regex.online.model;

public class MatchResult {

  private String source;
  private boolean matched;

  public MatchResult() {
  }

  public MatchResult(String source, boolean matched) {
    this.source = source;
    this.matched = matched;
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
}
