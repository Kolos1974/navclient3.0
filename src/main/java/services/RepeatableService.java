package services;

import config.Config;

import java.util.Timer;
import java.util.TimerTask;

public abstract class RepeatableService {

    private static final int INTERVAL_SEC = Config.intervalTime; // seconds

    private Timer timer;
    private Timer countdownTimer;
    private boolean isRunning = false;
    StateChangeCallback changeCallback;

    public void start(StateChangeCallback changeCallback) {
        this.changeCallback = changeCallback;
        utils.Logger.logMessage(getTag(), "Starting service...");
        startTimerTask();
        startCountDownTimer();
        isRunning = true;
    }

    public void stop() {
        utils.Logger.logMessage(getTag(), "Stopping service...");
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (countdownTimer != null) {
            countdownTimer.cancel();
            countdownTimer = null;
        }
        changeCallback = null;
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    protected abstract String getTag();

    protected abstract TimerTask getTimerTask();

    private void startTimerTask() {
        timer = new Timer(true);
        timer.schedule(getTimerTask(), INTERVAL_SEC * 1000, INTERVAL_SEC * 1000);
    }

    private void startCountDownTimer() {
        if (!(changeCallback instanceof StatusAndRemainingCallback)) return;
        StatusAndRemainingCallback remainingCallback = (StatusAndRemainingCallback) changeCallback;
        countdownTimer = new Timer(true);
        countdownTimer.schedule(new TimerTask() {

            private long secs = INTERVAL_SEC;

            @Override
            public void run() {
                secs--;
                if (secs < 0) resetTime();
                remainingCallback.remainingTime(secs);
            }

            private void resetTime() {
                secs = INTERVAL_SEC;
            }
        }, 0, 1000);
    }

}
