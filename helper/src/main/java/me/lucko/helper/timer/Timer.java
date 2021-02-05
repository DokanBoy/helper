package me.lucko.helper.timer;

import me.lucko.helper.Schedulers;
import me.lucko.helper.scheduler.Task;
import me.lucko.helper.terminable.Terminable;

public abstract class Timer implements Terminable, Runnable {

    protected Task timerTask;
    protected final long delayTicks, intervalTicks;

    protected Timer() {
        this(20L, 20L);
    }

    protected Timer(long delayTicks, long intervalTicks) {
        this.delayTicks = delayTicks;
        this.intervalTicks = intervalTicks;
    }

    protected abstract void onTick() throws Exception;

    public Task start() {
        return (timerTask = Schedulers.sync().runRepeating(this, delayTicks, intervalTicks));
    }

    @Override
    public void run() {
        try {
            onTick();
        } catch (Exception e) {
            onException(e);
        }
    }

    @Override
    public void close() throws IllegalStateException {
        timerTask.close();
    }

    protected void onException(Exception e) {
        e.printStackTrace();
    }

}