package pl.kkn;

import pl.kkn.Case.Case;

import java.util.List;
import java.util.Set;

public class DataSet {

    private List<Case> cases;
    private int numberOfArguments;
    private double[] maxValuesOfArguments;
    private double[] minValuesOfArguments;
    private Set<String> results;


    DataSet(List<Case> cases, Set<String> results, int numberOfArguments, double[] minValuesOfArguments, double[] maxValuesOfArguments) {
        this.cases = cases;
        this.numberOfArguments = numberOfArguments;
        this.maxValuesOfArguments = maxValuesOfArguments;
        this.minValuesOfArguments = minValuesOfArguments;
        this.results = results;
    }


    public List<Case> getCases() {
        return cases;
    }


}
