package com.riguz.regex.online.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riguz.regex.online.model.MatchRequest;
import com.riguz.regex.online.model.MatchResult;
import com.riguz.regex.online.model.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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
    return new MatchResult(source, source.matches(regex));
  }
}
