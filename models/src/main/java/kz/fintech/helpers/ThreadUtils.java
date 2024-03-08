package kz.fintech.helpers;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

public abstract class ThreadUtils {

    public static void run(Duration timeout, Runnable runnable) {
        Thread thread = new Thread(runnable);
        Timer timer = new Timer();
        timer.schedule(new TimeoutTask(thread, timer), timeout.toMillis());
        thread.start();
    }

    private static class TimeoutTask extends TimerTask {

        private Thread thread;
        private Timer timer;

        TimeoutTask(Thread thread, Timer timer) {
            this.thread = thread;
            this.timer = timer;
        }

        public void run() {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
                timer.cancel();
            }
        }
    }
}
