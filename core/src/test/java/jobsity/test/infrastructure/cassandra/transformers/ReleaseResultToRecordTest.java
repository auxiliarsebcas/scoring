
package jobsity.test.infrastructure.cassandra.transformers;

import jobsity.test.infrastructure.adapters.cassandra.records.ReleaseResultRecord;
import jobsity.test.infrastructure.adapters.cassandra.tranformers.RecordTransformer;
import jobsity.test.scoring.domain.ReleaseResult;
import org.junit.Assert;
import org.junit.Test;
/*

public class ReleaseResultToRecordTest {
    @Test
    public void testTransformer() {
        ReleaseResultToRecord releaseResultToRecord = new ReleaseResultToRecord();

        ReleaseResult releaseResult = new ReleaseResult("gameName","gamerId",0, 0);

        Assert.assertThat("ReleaseResultToRecord should to transfor a releaseResult to a record",
        new ReleaseResultRecord("gameName","gamerId",0, 0),
        sameBeanAs(releaseResultToRecord.trasform(releaseResult))
        );

    }
}
*/