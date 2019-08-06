package com.riguz.regex.online;

import com.riguz.regex.online.model.MatchRequest;
import com.riguz.regex.online.model.MatchResult;
import com.riguz.regex.online.model.Task;
import com.riguz.regex.online.service.TaskService;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegexController {

  private final TaskService taskService;

  public RegexController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/tasks")
  List<Task> hello() throws IOException {
    return taskService.getTasks();
  }

  @PostMapping("/match")
  List<MatchResult> match(@RequestBody MatchRequest request) {
    return taskService.match(request);
  }
}
