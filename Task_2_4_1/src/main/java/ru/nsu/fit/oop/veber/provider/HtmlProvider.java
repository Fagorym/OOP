package ru.nsu.fit.oop.veber.provider;

import j2html.TagCreator;
import lombok.Data;
import ru.nsu.fit.oop.veber.model.Report;
import ru.nsu.fit.oop.veber.model.StudentResults;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Set;

import static j2html.TagCreator.*;

@Data
public class HtmlProvider implements ReportProvider {
    public void generateReport(List<StudentResults> results) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        String styles = "<style>" +
                "table {border-collapse: collapse; width: 100%;}" +
                "th, td {border: 1px solid #dddddd; text-align: center; padding: 8px;}" +
                "th {background-color: #f2f2f2;}" +
                "tr:nth-child(even) {background-color: #f2f2f2;}" +
                "</style>";
        sb.append(styles);
        sb.append("<head>");
        sb.append("</head>");
        generateTablePerTask(sb, results);
        generateTotalScoreTable(sb, results);
        generateAttendanceTable(sb, results);
        generateTestResultsTable(sb, results);
        sb.append("</body>");
        sb.append("</html>");
        try {
            File file = new File("./Task_2_4_1/build/students/report.html");
            if (file.createNewFile() || file.exists()) {
                FileWriter fileWriter = new FileWriter(file.getPath());
                fileWriter.write(sb.toString());
                fileWriter.flush();
                fileWriter.close();
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("Problem with generating report.");
        }
    }

    private void generateTestResultsTable(StringBuilder sb, List<StudentResults> results) {
        sb.append("<table>");
        sb.append("<tr>\n");
        Set<String> taskSet = results.get(0).getTaskReports().keySet();
        for (String taskName : taskSet) {
            sb.append("<th>Student</th>");
            sb.append("<th>").append(taskName).append("</th>\n");
            for (StudentResults result : results) {
                if (result.getTestReports().containsKey(taskName)) {
                    sb.append("<tr rowspan=\"").append(result.getTestReports().get(taskName).size()).append("\">\n");
                    sb.append("<td>").append(result.getStudent().getNickname()).append("</td>\n");
                    for (var entry : result.getTestReports().get(taskName).entrySet()) {
                        sb.append("<td>").append(entry.getKey()).append("=").append(entry.getValue()).append("</td>");
                    }

                }
                sb.append("</tr>\n");
            }
        }
        sb.append("</tr>\n");
        sb.append("</table>");


    }

    private void generateAttendanceTable(StringBuilder sb, List<StudentResults> results) {
        String table = table(
                tbody(
                        tr(
                                th("Student"),
                                each(results.get(0).getDayReports().keySet(), TagCreator::th)),
                        each(results, result -> tr(
                                td(result.getStudent().getNickname()),
                                each(result.getDayReports().values(), dayValue -> td(dayValue ? "+" : "-"))
                        ))
                )
        ).renderFormatted();

        sb.append(table);
    }

    private void generateTablePerTask(StringBuilder sb, List<StudentResults> results) {

        /*String table = table(
                tr(
                        th("Student").attr("colspan", 2),
                        each(results.get(0).getTaskReports().keySet(), key -> th(key).attr("rowspan", 4))
                ),
                tr(
                        each(results.get(0).getTaskReports().values(), value -> th("Build").with(th("Test")).with(th("Javadoc")).with(th("Score")))
                )
        ).renderFormatted();

        sb.append(table);

         */
        sb.append("<table border=\"1\">\n");
        sb.append("<tr>\n");
        sb.append("<th rowspan=\"2\">Student</th>\n");

        int numberOfTasks = results.get(0).getTaskReports().size();
        for (var taskEntry : results.get(0).getTaskReports().entrySet()) {
            sb.append("<th colspan=\"4\">").append(taskEntry.getKey()).append("</th>\n");
        }
        sb.append("</tr>\n");

        sb.append("<tr>\n");
        for (int i = 1; i <= numberOfTasks; i++) {
            sb.append("<th>Build</th>\n");
            sb.append("<th>Test</th>\n");
            sb.append("<th>Javadoc</th>\n");
            sb.append("<th>Score</th>\n");
        }
        sb.append("</tr>\n");
        for (StudentResults result : results) {
            sb.append("<tr>\n");
            sb.append("<td>").append(result.getStudent().getNickname()).append("</td>\n");

            for (Report report : result.getTaskReports().values()) {
                sb.append("<td>").append(report.isWasBuilt() ? "+" : "-").append("</td>\n");
                sb.append("<td>").append(report.isWasTested() ? "+" : "-").append("</td>\n");
                sb.append("<td>").append(report.isHasDocs() ? "+" : "-").append("</td>\n");
                sb.append("<td>").append(report.getScore()).append("</td>\n");
            }

            sb.append("</tr>\n");
        }
        sb.append("</table>");

    }

    private void generateTotalScoreTable(StringBuilder sb, List<StudentResults> results) {
        String table = table(
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
        ).renderFormatted();
        sb.append(table);
    }
}
