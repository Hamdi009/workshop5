

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileLineCounterRunnable implements Runnable {
    private final Counter counter;
    String filePath;
    int lineCount;

    public int getLineCount() {
        return lineCount;
    }

    public FileLineCounterRunnable(Counter counter, String filePath) {
        this.counter = counter;
        this.filePath = filePath;
    }

    public void run() {
        lineCount = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while (bufferedReader.readLine() != null) {
                lineCount++;
            }
            counter.increment(lineCount);
            bufferedReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 

    }


}