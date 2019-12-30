package jobsity.test.scoring.domain.errors;

import jobsity.test.ServiceError;

public class ApplicationError extends ServiceError {
    public ApplicationError(Throwable error) {
        super("00002", "we have an internal error. error: "+ error.getMessage() );
    }
}
