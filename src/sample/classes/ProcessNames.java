package sample.classes;

import java.util.ArrayList;
import java.util.Random;

public class ProcessNames {
    private ArrayList<String> processNames = new ArrayList<>();
    private Random random = new Random();

    public ProcessNames(){
        processNames.add("Process1.exe");
        processNames.add("Process2.exe");
        processNames.add("Process3.exe");
        processNames.add("Process4.exe");
        processNames.add("Process5.exe");
        processNames.add("Process6.exe");
        processNames.add("Process7.exe");
        processNames.add("Process8.exe");
        processNames.add("Process9.exe");

    }

    public String getRandomProcessName(){
        int index = random.nextInt(processNames.size()-1);
        return this.processNames.get(index);
    }
}
