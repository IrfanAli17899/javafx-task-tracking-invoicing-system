package app.utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimerApp {
    long sec = 0;
    Boolean running = false;
    Timer timer = new Timer();
    TimerTask task = new TimerTask(){
        @Override
        public void run(){
            String time = UtilsClass.getTime(sec);
            LocalStorage.getInstance().setTime(time);
            System.out.println(time);
        sec++;
     }
    };

    public long getSec() {
        return sec;
    }

    public void setSec(long sec) {
        this.sec = sec;
    }

    public void start(){
        running = true;
        timer.schedule(task, 0, 1000);
    }

    public void stop(){
        if (running){
            timer.cancel();
            running = false;
        }
    }


}
