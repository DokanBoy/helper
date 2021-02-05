package me.lucko.helper.timer;

import java.util.function.IntConsumer;

public final class TimeableTimer extends DefinedTimer {

    private final IntConsumer onSecondConsumer;
    private final Runnable onStopTimer;

    private TimeableTimer(long targetTime, IntConsumer onSecondConsumer, Runnable onStopTimer) {
        super(20L, 20L, targetTime);

        this.onSecondConsumer = onSecondConsumer;
        this.onStopTimer = onStopTimer;
    }


    @Override
    protected void onLastTick() {
        onStopTimer.run();
    }

    @Override
    public void onTick() {
        onSecondConsumer.accept(timerTask.getTimesRan());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private IntConsumer onSecondConsumer;
        private Runnable onStopTimer;
        private long targetTime;

        public Builder targetTime(long targetTime) {
            this.targetTime = targetTime;

            return this;
        }

        public Builder onTick(IntConsumer onTickConsumer) {
            this.onSecondConsumer = onTickConsumer;

            return this;
        }

        public Builder onStop(Runnable onStopRunnable) {
            this.onStopTimer = onStopRunnable;

            return this;
        }

        public TimeableTimer build() {
            return new TimeableTimer(targetTime, onSecondConsumer, onStopTimer);
        }

    }

}
