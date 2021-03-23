package pl.kkn;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

    private Scanner scanner;
    private int parameterK;

    public UserInput() {

        scanner = new Scanner(System.in);
        parameterK = Integer.parseInt(scanner.next());
        scanner.close();
    }

    public int getParameterK() {
        return parameterK;
    }
}
