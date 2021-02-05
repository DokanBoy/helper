package me.lucko.helper.timer;

import me.lucko.helper.Schedulers;

public abstract class DefinedTimer extends Timer {

    protected final long timesRunGoal;

    protected DefinedTimer(long timesRunGoal) {
        super(20L, 20L);

        this.timesRunGoal = timesRunGoal;
    }

    protected DefinedTimer(long delayTicks, long intervalTicks, long timesRunGoal) {
        super(delayTicks, intervalTicks);

        this.timesRunGoal = timesRunGoal;
    }

    protected void onLastTick() throws Exception {

    }

    @Override
    public void run() {
        super.run();

        if (timerTask.getTimesRan() >= timesRunGoal) {
            try {
                onLastTick();
            } catch (Exception e) {
                onException(e);
            }
            Schedulers.sync().run(() -> timerTask.close());
        }
    }

}
