package pl.kkn;

import pl.kkn.Case.Case;

import java.util.*;

public class DataSetParser {
    // On będzie brał liste linii i tworzył z nich macierz
    private List<String> lines;
    private List<Case> cases = new LinkedList<>();
    private static final String DELIMITER = "\t";

    private boolean isDataPropertiesKnown = false;
    private int numberOfArguments;
    private double[] maxValuesOfArguments;
    private double[] minValuesOfArguments;
    private Set<String> results = new HashSet<>();

    public DataSetParser(List<String> list) {
        this.lines = list;
        createDataSet();
    }

    private void createDataSet() {

        for (String line : lines) {
            cases.add(createCase(line));
        }
    }

    private void determineDataProperties(String[] l) {
        numberOfArguments = l.length - 1;

        maxValuesOfArguments = new double[numberOfArguments];
        minValuesOfArguments = new double[numberOfArguments];

        for (int i = 0; i < numberOfArguments; i++) {
            minValuesOfArguments[i] = maxValuesOfArguments[i] = Double.parseDouble(l[i]);
        }

    }

    private Case createCase(String line) {
        line = line.replaceAll("," , ".");
        String[] l = line.split(DELIMITER);

        if (!isDataPropertiesKnown){
            determineDataProperties(l);
            isDataPropertiesKnown = true;
        }

        double[] arguments = new double[numberOfArguments];
        for (int i = 0; i < numberOfArguments; i++) {
            arguments[i] = Double.parseDouble(l[i]);
            compareWithMaxValue(arguments[i], i);
            compareWithMinValue(arguments[i],i);
            addArgumentToResultSet(l[l.length-1]);

        }
        return new Case(arguments, l[l.length - 1].trim());
    }

    private void addArgumentToResultSet(String result) {
        results.add(result);
    }

    private void compareWithMinValue(double argVal, int columnIndex) {
        if ( minValuesOfArguments[columnIndex] > argVal ){
            minValuesOfArguments[columnIndex] = argVal;
        }
    }


    private void compareWithMaxValue(double argVal, int columnIndex){
        if ( maxValuesOfArguments[columnIndex] < argVal ){
           maxValuesOfArguments[columnIndex] = argVal;
        }
    }

    public DataSet buildDataSet(){
        return  new DataSet(cases,results, numberOfArguments, minValuesOfArguments,maxValuesOfArguments);
    }


}
