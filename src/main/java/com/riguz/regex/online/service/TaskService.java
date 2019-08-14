package com.riguz.regex.online.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riguz.regex.online.model.MatchRequest;
import com.riguz.regex.online.model.MatchResult;
import com.riguz.regex.online.model.Task;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  public List<Task> getTasks() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("tasks.json"),
        new TypeReference<List<Task>>() {
        });
  }

  public List<MatchResult> match(MatchRequest request) {
    return request.getSources()
        .stream()
        .map((source) -> match(source, request.getRegex()))
        .collect(Collectors.toList());
  }

  private MatchResult match(String source, String regex) {
    Instant start = Instant.now();
    boolean matches = source.matches(regex);
    Instant finish = Instant.now();
    return new MatchResult(source, matches, Duration.between(start, finish).toNanos());
  }

  public List<MatchResult> find(MatchRequest request) {
    Pattern pattern = Pattern.compile(request.getRegex());
    return request.getSources()
        .stream()
        .map((source) -> find(source, pattern))
        .collect(Collectors.toList());
  }

  private MatchResult find(String source, Pattern regex) {
    Matcher matcher = regex.matcher(source);
    final List<String> groupMarks = new ArrayList<>();
    while (matcher.find()) {
      final List<GroupPosition> groups = new ArrayList<>();
      for (int i = 0; i <= matcher.groupCount(); i++) {
        int begin = matcher.start(i);
        int end = matcher.end(i);
        GroupPosition position = new GroupPosition(i, begin, end);
        groups.add(position);
      }
      String result = "";
      for (int i = 0; i < source.length(); i++) {
        String ch = String.valueOf(source.charAt(i));
        result += formatCharacter(ch, i, groups);
      }
      groupMarks.add(result);
    }
    return new MatchResult(source, groupMarks);
  }

  public List<MatchResult> replace(MatchRequest request) {
    return request.getSources()
        .stream()
        .map((source) -> replace(source, request.getRegex(), request.getReplaceTo()))
        .collect(Collectors.toList());
  }

  private MatchResult replace(String source, String regex, String replaceTo) {
    return new MatchResult(source, source.replaceAll(regex, replaceTo));
  }

  static class GroupPosition {

    final int level;
    final int begin;
    final int end;


    GroupPosition(int level, int begin, int end) {
      this.level = level;
      this.begin = begin;
      this.end = end;
    }

    boolean isInBound(int position) {
      return position >= begin && position < end;
    }
  }

  static String formatCharacter(String ch, int p, List<GroupPosition> groups) {
    return groups.stream()
        .filter((groupPosition -> groupPosition.isInBound(p)))
        .map(groupPosition -> buildInlineStyle(groupPosition.level))
        .reduce(ch, (last, style) -> String.format("<span style=\"%s\">%s</span>", style, last));
  }

  static String buildInlineStyle(int level) {
    int base = level + 1;
    return String.format("padding-bottom: %dpx;border-bottom: %dpx solid red;",
        base * 5 + level, base);
  }
}
