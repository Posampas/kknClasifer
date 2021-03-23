package pl.kkn.Case;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Case {
    private double[] properties;
    private String result;

    public Case(double[] properties, String result) {
        this.properties = properties;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Case)) return false;
        Case aCase = (Case) o;
        return Arrays.equals(properties, aCase.properties);
    }

    @Override
    public int hashCode() {
        int result1 = Objects.hash(result);
        result1 = 31 * result1 + Arrays.hashCode(properties);
        return result1;
    }


    public double[] getProperties() {
        return properties;
    }

    public String getResult() {
        return result;
    }


    @Override
    public String toString() {
        return Arrays.toString(properties) + " " + result;
    }
}
