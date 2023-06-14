package ru.nsu.fit.oop.veber.checker;

import lombok.RequiredArgsConstructor;
import ru.nsu.fit.oop.veber.model.StudentResults;
import ru.nsu.fit.oop.veber.model.Task;
import ru.nsu.fit.oop.veber.provider.VersionControlProvider;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DeadlineChecker implements Executor {
    private final VersionControlProvider versionControlProvider;

    @Override
    public void execute(List<StudentResults> results, Task task) {
        for (StudentResults result : results) {
            Map<String, LocalDate> dates = versionControlProvider.getBorderCommitsDate(
                    task.getId().toLowerCase().replace("_", "-"),
                    result
            );
            var report = result.getTaskReports().get(task.getId());
            report.setWasSoftDeadline(dates.get("first").isBefore(task.getSoftDeadline()));
            report.setWasHardDeadline(dates.get("last").isBefore(task.getHardDeadline()));
            result.getTaskReports().put(task.getId(), report);
        }

    }

    @Override
    public String getName() {
        return "deadlineChecker";
    }
}
