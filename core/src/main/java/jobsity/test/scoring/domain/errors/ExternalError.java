package jobsity.test.scoring.domain.errors;

import jobsity.test.ServiceError;

public class ExternalError extends ServiceError {
    public ExternalError(Throwable error) {
        super("00001", "we have an external error");
    }
}
