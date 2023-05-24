package ru.nsu.fit.oop.veber.model;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public abstract class Parcelable {
    @SuppressWarnings("unchecked")
    public <T extends Parcelable> T parse(String path) {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);

        try {
            File scriptFile = new File(path);
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
