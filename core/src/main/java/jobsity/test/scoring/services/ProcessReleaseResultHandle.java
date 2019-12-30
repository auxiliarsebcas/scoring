package jobsity.test.scoring.services;


import cyclops.control.Future;
import jobsity.test.infrastructure.kafka.KafkaHandle;

public class ProcessReleaseResultHandle implements KafkaHandle {
	@Override
	public Future onMessage(Object o, long l) {
		return null;
	}

	@Override
	public String getTopic() {
		return null;
	}
}
