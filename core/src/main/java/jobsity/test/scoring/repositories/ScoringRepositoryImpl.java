package jobsity.test.scoring.repositories;

import cyclops.control.Either;
import cyclops.data.NonEmptyList;
import cyclops.monads.AnyM;
import cyclops.monads.AnyMs;
import cyclops.monads.Witness;
import cyclops.monads.transformers.EitherT;
import jobsity.test.ServiceError;
import jobsity.test.infrastructure.adapters.cassandra.databases.ScoringDataBase;
import jobsity.test.infrastructure.adapters.cassandra.tranformers.RecordTransformer;
import jobsity.test.scoring.domain.ReleaseResult;
import jobsity.test.scoring.domain.errors.ExternalError;

public class ScoringRepositoryImpl  implements ScoringRepository {
    final ScoringDataBase scoringDataBase;

    public ScoringRepositoryImpl(ScoringDataBase dataBase) {
        this.scoringDataBase = dataBase;
    }
    @Override
    public EitherT<Witness.future, NonEmptyList<ServiceError>, ReleaseResult> saveProcessReleaseResult(ReleaseResult releaseResult) {
        RecordTransformer.releaseResultToRecord(releaseResult);

        return null;
    }

    @Override
    public EitherT<Witness.future, NonEmptyList<ServiceError>, ReleaseResult> getProcessReleaseResultByGameName(String gameName) {
        return
        EitherT.of(
            AnyM.fromFuture(
                 scoringDataBase.releaseResultByGameId.getRecordById(gameName)
                     .map( record ->
                             Either.<NonEmptyList<ServiceError>, ReleaseResult>right( RecordTransformer.recordToreleaseResult(record) )
                     )
                    .recover( error ->
                            Either.<NonEmptyList<ServiceError>, ReleaseResult>left( NonEmptyList.of( new ExternalError(error) ) )
                    )
            )
        );
    }
}
