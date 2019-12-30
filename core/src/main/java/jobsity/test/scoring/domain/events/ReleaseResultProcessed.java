package jobsity.test.scoring.domain.events;

import jobsity.test.Event;

public class ReleaseResultProcessed extends Event {
    public ReleaseResultProcessed(String topic) {
        super(topic);
    }
}
