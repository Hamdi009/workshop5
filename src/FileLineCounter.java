import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileLineCounter extends Thread{
    public static void main (String[] args) {
        Counter counter = new Counter();

    ArrayList<FileLineCounterRunnable> runnables = new ArrayList<>();
    
    ArrayList<String> files = new ArrayList<>();
    
    files.add("src/file1.txt");
    files.add("src/file2.txt");
    files.add("src/file3.txt");

    for (String filePath : files){
        runnables.add(new FileLineCounterRunnable(counter, filePath));
    }

    ArrayList<Thread> threads = new ArrayList<>();
    for(FileLineCounterRunnable runnable : runnables){
        threads.add(new Thread(runnable));
    }

    for (Thread thread : threads){
        thread.start();
    }

    for (Thread thread : threads){
        try {
            thread.join();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("error: "+e.getMessage());
        }
    }
    try{
        FileOutputStream fileOutputStream = new FileOutputStream("src/output.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        for (FileLineCounterRunnable runnable : runnables){
            writer.write(runnable.filePath + ": " + runnable.getLineCount()+" lines");
            writer.newLine();

        }
        writer.write("Total lines: "+ counter.getCount());

        writer.close();
        fileOutputStream.close();
    } catch (IOException e) {
        // TODO: handle exception
        System.err.println("error writing to output file");
    }

    }
}