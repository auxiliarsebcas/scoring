package jobsity.test.scoring.repositories;

import cyclops.data.NonEmptyList;
import cyclops.monads.Witness;
import cyclops.monads.transformers.EitherT;
import jobsity.test.ServiceError;
import jobsity.test.scoring.domain.ReleaseResult;


public interface ScoringRepository {
    public EitherT<Witness.future, NonEmptyList<ServiceError>,ReleaseResult> saveProcessReleaseResult(ReleaseResult releaseResult);

    public EitherT<Witness.future, NonEmptyList<ServiceError>,ReleaseResult> getProcessReleaseResultByGameName(String gameName);
}
