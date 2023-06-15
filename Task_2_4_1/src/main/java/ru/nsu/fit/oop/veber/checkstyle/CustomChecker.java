package ru.nsu.fit.oop.veber.checkstyle;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class CustomChecker {
    private static final String DEFAULT_CODESTYLE_PATH = "Task_2_4_1/src/main/resources/google_checks.xml";
    private final Checker codeStyleChecker;

    public CustomChecker(String studentName, String taskName) {
        codeStyleChecker = new Checker();
        try {
            Configuration config = ConfigurationLoader.loadConfiguration(
                    DEFAULT_CODESTYLE_PATH,
                    new PropertiesExpander(new Properties())
            );
            codeStyleChecker.setLocaleLanguage("en");
            codeStyleChecker.setModuleClassLoader(JavaParser.class.getClassLoader());
            codeStyleChecker.configure(config);
            codeStyleChecker.addListener(new FileListener(studentName, taskName));
        } catch (CheckstyleException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkCodestyle(List<File> files) {
        try {
            codeStyleChecker.process(files);
        } catch (CheckstyleException e) {
            throw new RuntimeException(e);
        }
    }
}
