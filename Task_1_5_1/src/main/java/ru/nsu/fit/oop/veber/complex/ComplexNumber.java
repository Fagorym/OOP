package ru.nsu.fit.oop.veber.complex;

import org.apache.commons.numbers.complex.Complex;
import ru.nsu.fit.oop.veber.Number;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumber extends Number {

    private String key;
    private Complex value;

    @Override
    public Complex calculate(List<Number> operands) {
        return value;
    }

    @Override
    public Integer getArity() {
        return 0;
    }

    @Override
    public Boolean matches(String key) {
        Pattern originalPattern = Pattern.compile("(-??(\\d*([.]\\d*)??)[+])??(-??\\d*([.]\\d*)??)??i");
        Matcher matcher = originalPattern.matcher(key);
        if (matcher.matches()) {
            this.key = key;
            this.value = Complex.ofCartesian(Double.parseDouble(matcher.group(2)),
                    Double.parseDouble(matcher.group(4)));
            return true;
        }
        Pattern sidePattern = Pattern.compile("[(](-??\\d*([.]\\d*)??),(-??\\d*([.]\\d*)??)[)]");
        Matcher sideMatcher = sidePattern.matcher(key);
        if (sideMatcher.matches()) {
            this.key = key;
            this.value = Complex.ofCartesian(Double.parseDouble(sideMatcher.group(1)),
                    Double.parseDouble(sideMatcher.group(3)));
            return true;
        }
        return false;
    }

    @Override
    protected String getKey() {
        return key;
    }

    @Override
    public ComplexNumber clone() {
        ComplexNumber clone = (ComplexNumber) super.clone();
        clone.key = key;
        clone.value = value;
        return clone;
    }
}
