package sample.classes;

import java.util.Date;
import java.util.Timer;

public class ClassExecutingTask {

    public ClassExecutingTask(){

    }

    public void Generate(Processes processes) {
        Timer timer = new Timer("Generate process");
        TimerTaskProcess timerTaskProcess = new TimerTaskProcess(processes);
        Date date = new Date();
        long delay = 5 * 1000;
        timer.scheduleAtFixedRate(timerTaskProcess, date, delay);
    }
}
