package pl.kkn;

import pl.kkn.Case.Case;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private Classifier classifier;
    private DataSet testSet;
    private DataSet trainSet;
    private DatasetFile testDataFile;
    private DatasetFile trainDataFile;

    public Main(String trainSetPath, String testSetPath) {
        testDataFile = new DatasetFile(testSetPath);
        trainDataFile = new DatasetFile(trainSetPath);
        programLoop();

    }


    private void programLoop() {
        loadDataSets();
        classifier = new Classifier(trainSet);
        getParameterKFromUser();
        testClassifier();
        Scanner sc = new Scanner(System.in);
        System.out.println("!! - Provide custom data to classify \n"
                + "!! - in format  2.5 2.7 0.1 5.3 "
                + "!! - type \"exit\" to close Program ");
        while (true) {
            if (acceptUserInput(sc)) {
                return;
            }
        }

    }

    private boolean acceptUserInput(Scanner scanner) {
        while (true) {

            if (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.equals("exit")) {
                    return true;
                }
                String[] lines = line.split(" ");

                if (lines.length != testSet.getCases().get(0).getProperties().length) {
                    System.out.println("Provide correct number of arguments");
                    return false;
                } else {
                    double[] arguments = new double[lines.length];

                    for (int i = 0; i < arguments.length; i++) {
                        try {
                            arguments[i] = Double.parseDouble(lines[i]);
                        } catch (NumberFormatException e) {
                            System.out.println("Provide numerical type data");
                            return false;
                        }
                    }

                    Case c = new Case(arguments, null);
                    System.out.println(classifier.classify(c));
                    break;

                }

            }
        }


        return false;
    }

    private void testClassifier() {
        int successCount = 0;
        for (Case c : testSet.getCases()) {
            String result = classifier.classify(c);
            if (result.equals(c.getResult())) {
                successCount++;
            }
        }
        System.out.println(successCount);
        System.out.println("Success rate " + (((double) successCount / (double) testSet.getCases().size()) * 100) + " % ");
    }

    private void getParameterKFromUser() {
        System.out.println("Provide parameter K");
        int paramK;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                paramK = Integer.parseInt(scanner.next());

                if (classifier.setParameterK(paramK)) {
                    break;
                } else {
                    System.out.println("Parameter K should be integer in range from  1 to size of train data set");
                }

            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("Parameter K should be integer in range from  1 to size of train data set");
            }
        }

    }

    private void loadDataSets() {
        try {

            testSet = new DataSetParser(new DataLoader(testDataFile.getFile()).getRawData()).buildDataSet();
            trainSet = new DataSetParser(new DataLoader(trainDataFile.getFile()).getRawData()).buildDataSet();


        } catch (FileNotFoundException e) {

        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Provide paths to train and test data");
            return;
        }
        new Main(args[0], args[1]);
    }


}
