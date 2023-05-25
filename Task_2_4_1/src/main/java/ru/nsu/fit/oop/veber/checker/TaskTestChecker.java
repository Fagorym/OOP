package ru.nsu.fit.oop.veber.checker;

import ru.nsu.fit.oop.veber.model.Project;
import ru.nsu.fit.oop.veber.model.Report;
import ru.nsu.fit.oop.veber.model.Task;

import java.util.List;
import java.util.Map;

public class TaskTestChecker extends TaskExecutor {
    public static void checkTasks(
            List<Project> projects,
            Task task,
            Map<String, Map<String, Report>> reports) {
        projects.forEach(project -> {
            Report report = reports.get(task.getId()).get(project.getStudent().getNickname());
            makeTask(project, "test", task, report);
        });
    }
}
