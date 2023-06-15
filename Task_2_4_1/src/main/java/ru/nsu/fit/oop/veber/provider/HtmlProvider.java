package ru.nsu.fit.oop.veber.provider;

import j2html.TagCreator;
import j2html.tags.ContainerTag;
import lombok.Data;
import ru.nsu.fit.oop.veber.model.CheckStyleReport;
import ru.nsu.fit.oop.veber.model.Report;
import ru.nsu.fit.oop.veber.model.StudentResults;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;

import static j2html.TagCreator.*;

@Data
public class HtmlProvider implements ReportProvider {
    public void generateReport(List<StudentResults> results) {
        ContainerTag html = html(
                style("table {border-collapse: collapse; width: 100%; border-radius: 10px; overflow: hidden; margin: 20px auto; max-width: 1200px; box-shadow: 0 4px 14px rgba(0, 0, 0, 0.2);}" +
                        "th, td {border: none; border-bottom: 1px solid #dddddd; text-align: center; padding: 8px; min-height: 40px;}" +
                        "td:last-child, th:last-child {border-right: none;}" +
                        "th {background-color: #9370db; color: #ffffff; font-weight: bold; font-size: 16px;}" +
                        "tr:nth-child(even) {background-color: #f2f2f2;}" +
                        "tr:nth-child(odd) {background-color: #ffffff;}" +
                        "tr:not(:first-child):hover {background-color: #f5f5f5; cursor: pointer;}" +
                        "tr {vertical-align: middle;} tr:last-child {border-bottom: none;}" +
                        "td {color: #333333;}" +
                        ".test-success {color: green;}" +
                        ".test-failure {color: red;}"
                ),
                head(),
                body(
                        generateTablePerTask(results),
                        generateTotalScoreTable(results),
                        generateAttendanceTable(results)
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
                                th(task).attr("colspan", 8)
                        )

                ),
                tr(
                        each(results.get(0).getTaskReports().keySet(), task ->
                                join(
                                        th("Build"),
                                        th("Test"),
                                        th("Javadoc"),
                                        th("Style"),
                                        th("Coverage"),
                                        th("Soft deadline"),
                                        th("Hard deadline"),
                                        th("Score")
                                ))
                ),
                each(results, result -> tr(td(result.getStudent().getNickname())).with(
                        each(result.getTaskReports().values(), value ->
                                join(
                                        td(value.isWasBuilt() ? "+" : "-"),
                                        td(value.isWasTested() ? "+" : "-"),
                                        td(value.isHasDocs() ? "+" : "-"),
                                        getStyleResult(result, value),
                                        getTestCoverage(result, value),
                                        td(value.isWasSoftDeadline() ? "0.5" : "0"),
                                        td(value.isWasHardDeadline() ? "0.5" : "0"),
                                        td(String.valueOf(value.getScore()))
                                )
                        )
                ))
        );
    }

    private ContainerTag getStyleResult(StudentResults result, Report report) {
        CheckStyleReport checkStyleReport = result.getCheckStyleReport().get(report.getTaskId());
        if (checkStyleReport.getWarningCount() == null) {
            return td("No information about checkstyle");
        }
        return td(a(String.valueOf(checkStyleReport.getWarningCount()))
                .withHref("../../../" + checkStyleReport.getFilePath()));
    }

    private ContainerTag getTestCoverage(StudentResults result, Report report) {
        String coverage = result.getTestCoverage().get(report.getTaskId());

        if (Objects.isNull(coverage) || "null".equals(coverage)) {
            return td("Coverage not found").withClass("test-failure");
        } else {
            return td(result.getTestCoverage().get(report.getTaskId())).withClass("test-success");

        }
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
