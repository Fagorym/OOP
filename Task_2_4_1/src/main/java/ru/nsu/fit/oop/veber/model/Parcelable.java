package ru.nsu.fit.oop.veber.model;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public abstract class Parcelable {
    @SuppressWarnings("unchecked")
    public <T extends Parcelable> T parse() {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);

        try {
            File scriptFile = new File("Task_2_4_1/src/main/java/ru/nsu/fit/oop/veber/config.groovy");
            Object parsedConfig = shell.evaluate(scriptFile);
            if (this.getClass().isInstance(parsedConfig)) {
                return (T) parsedConfig;
            } else {
                throw new RuntimeException("Not instance of the needed class");
            }
        } catch (Exception e) {
            log.error("Exception while parsing class instance");
            e.printStackTrace();
        }
        return null;
    }
}
