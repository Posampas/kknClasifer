package pl.kkn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

public class DataLoader {

    private File file;
    private List<String> lines = new LinkedList<>();

    public DataLoader(File file) throws FileNotFoundException {
        this.file = file;
        loadDataToTheMemory();
    }

    private void loadDataToTheMemory() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        scanner.close();

    }

    public List<String> getRawData() {
        return lines;
    }
}
