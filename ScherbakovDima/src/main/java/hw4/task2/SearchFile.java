package hw4.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SearchFile {

    private static boolean result = false;
    private static PrintWriter printWriter;

    public static boolean search(String name, File folder){
        File[] files = folder.listFiles();

        if (files != null) {
            for(File file : files){
                if(file.isDirectory()){
                    search(name, file);
                } else if(file.getName().equals(name)) {
                    result = true;
                    saveResult(file.getAbsolutePath());
                }
            }
        }
        return result;
    }

    private static void saveResult(String result){
        try {
            PrintWriter pw = new PrintWriter(new File("ScherbakovDima/src/main/java/task2/temp/result.txt"));
            pw.println(result);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}