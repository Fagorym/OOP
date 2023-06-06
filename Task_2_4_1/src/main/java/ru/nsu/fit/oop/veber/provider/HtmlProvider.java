package ru.nsu.fit.oop.veber.provider;

import j2html.TagCreator;
import j2html.tags.ContainerTag;
import lombok.Data;
import ru.nsu.fit.oop.veber.model.StudentResults;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static j2html.TagCreator.*;

@Data
public class HtmlProvider implements ReportProvider {
    public void generateReport(List<StudentResults> results) {
        ContainerTag html = html(
                style("table {border-collapse: collapse; width: 100%;}" +
                        "th, td {border: 1px solid #dddddd; text-align: center; padding: 8px;}" +
                        "th {background-color: #f2f2f2;}" +
                        "tr:nth-child(even) {background-color: #f2f2f2;}"
                ),
                head(),
                body(
                        generateTablePerTask(results),
                        generateTotalScoreTable(results),
                        generateAttendanceTable(results),
                        generateTestResultsTable(results)
                )

        );


        try {
            File file = new File("./Task_2_4_1/build/students/report.html");
            if (file.createNewFile() || file.exists()) {
                FileWriter fileWriter = new FileWriter(file.getPath());
                fileWriter.write(html.renderFormatted());
                fileWriter.flush();
                fileWriter.close();
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("Problem with generating report.");
        }
    }

    private ContainerTag generateTestResultsTable(List<StudentResults> results) {
        Set<String> allTests = results.stream()
                .map(entry -> entry.getTestReports().keySet())
                .reduce(new HashSet<>(), (res, elem) -> {
                    res.addAll(elem);
                    return res;
                });

        return table(
                tr(
                        each(allTests, test -> {
                            if (results.stream().anyMatch(
                                    result -> result.getTestReports().containsKey(test)
                            )) {
                                return th("Student").with(th(test).with(each(results, result -> {
                                    if (result.getTestReports().containsKey(test)) {
                                        return tr(
                                                td(result.getStudent().getNickname())
                                                        .with(each(result.getTestReports().get(test),
                                                                task -> td(task.getKey() + "=" + task.getValue()))));
                                    } else {
                                        return emptyTag("tag");
                                    }
                                })));
                            }
                            return th();
                        })
                )
        );
    }

    private ContainerTag generateAttendanceTable(List<StudentResults> results) {
        return table(
                tbody(
                        tr(
                                th("Student"),
                                each(results.get(0).getDayReports().keySet(), TagCreator::th)),
                        each(results, result -> tr(
                                td(result.getStudent().getNickname()),
                                each(result.getDayReports().values(), dayValue -> td(dayValue ? "+" : "-"))
                        ))
                )
        );
    }

    private ContainerTag generateTablePerTask(List<StudentResults> results) {
        return table(
                tr(
                        th("Student").attr("rowspan", 2),
                        each(results.get(0).getTaskReports().keySet(), task ->
                                th(task).attr("colspan", 4)
                        )

                ),
                tr(
                        each(results.get(0).getTaskReports().keySet(), task ->
                                join(th("Build"), th("Test"), th("Javadoc"), th("Score")))
                ),
                each(results, result -> tr(td(result.getStudent().getNickname())).with(
                        each(result.getTaskReports().values(), value ->
                                join(
                                        td(value.isWasBuilt() ? "+" : "-"),
                                        td(value.isWasTested() ? "+" : "-"),
                                        td(value.isHasDocs() ? "+" : "-"),
                                        td(String.valueOf(value.getScore()))
                                )
                        )
                ))
        );
    }

    private ContainerTag generateTotalScoreTable(List<StudentResults> results) {
        return table(
                tbody(
                        tr(
                                th("Student"),
                                th("Total")
                        ),
                        each(results, result -> tr(
                                td(result.getStudent().getNickname()),
                                td(String.valueOf(result.getTotal()))
                        ))
                )
        );
    }
}
