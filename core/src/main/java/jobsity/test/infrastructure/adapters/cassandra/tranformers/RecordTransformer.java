package jobsity.test.infrastructure.adapters.cassandra.tranformers;

import jobsity.test.Transformable;
import jobsity.test.infrastructure.adapters.cassandra.records.ReleaseResultRecord;
import jobsity.test.scoring.domain.ReleaseResult;
import jobsity.test.util.TimeConverter;

public class RecordTransformer {

    private static Transformable<ReleaseResult, ReleaseResultRecord> releaseResultToRecordTransformable = releaseResult ->
        new ReleaseResultRecord(
             releaseResult.gameName
           , releaseResult.gamerId
           , releaseResult.releaseNumber
           , releaseResult.score
           , TimeConverter.instantToString(releaseResult.instant)
        );

     private static Transformable<ReleaseResultRecord,ReleaseResult> recordToreleaseResultTransformable = record ->
             new ReleaseResult(
               record.gameId
               , record.gamerId
               , record.releaseNumber
               , record.score
	             , TimeConverter.stringToInstant(record.dateTime)
             );

    public static ReleaseResultRecord releaseResultToRecord(ReleaseResult releaseResult) {
        return releaseResultToRecordTransformable.trasform(releaseResult);
    }

     public static ReleaseResult recordToreleaseResult(ReleaseResultRecord record) {
         return recordToreleaseResultTransformable.trasform(record);
     }
}
