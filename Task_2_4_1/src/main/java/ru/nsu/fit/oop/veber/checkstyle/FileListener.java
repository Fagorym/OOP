package ru.nsu.fit.oop.veber.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class FileListener implements AuditListener {
    private final FileWriter writer;

    public FileListener(String nickname, String taskName) {
        try {
            File file = new File("result_" + nickname + "_" + taskName);
            if (!file.createNewFile()) {
                file.delete();
                file.createNewFile();
            }
            writer = new FileWriter(file.getPath());
        } catch (IOException ex) {
            throw new RuntimeException("Cannot create such file");
        }

    }

    @Override
    public void auditStarted(AuditEvent auditEvent) {
        log.debug("Audit started");
    }

    @Override
    public void auditFinished(AuditEvent auditEvent) {
        log.debug("Audit finished");
    }

    @Override
    public void fileStarted(AuditEvent auditEvent) {
        log.debug("File started: " + auditEvent.getFileName());
    }

    @Override
    public void fileFinished(AuditEvent auditEvent) {
        log.debug("File finished: " + auditEvent.getFileName());
    }

    @Override
    public void addError(AuditEvent auditEvent) {
        log.warn("line " + auditEvent.getLine() + ": " + auditEvent.getMessage());
        try {
            writer.write("line " + auditEvent.getLine() + ": " + auditEvent.getMessage() + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addException(AuditEvent auditEvent, Throwable throwable) {
        log.warn("Exception: " + throwable.getMessage());
        try {
            writer.write("line " + auditEvent.getLine() + ": " + auditEvent.getMessage() + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
