package pl.kkn;

import pl.kkn.exception.CustomException;

import java.io.File;

public class DatasetFile {
    private File file ;

    public DatasetFile(String path ) {
        file = new File(path);
        validateFile();
    }

    private void validateFile(){
        if  (!file.exists()  || file.isDirectory() ) {
            throw new CustomException("Provide correct path for the set");
        }
    }

    public File getFile() {
        return file;
    }
}
