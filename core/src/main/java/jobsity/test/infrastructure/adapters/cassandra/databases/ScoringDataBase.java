package jobsity.test.infrastructure.adapters.cassandra.databases;

import com.datastax.oss.driver.api.core.CqlSession;
import jobsity.test.infrastructure.adapters.cassandra.daos.ReleaseResultByGameId;

public class ScoringDataBase {
    public final CqlSession session;
    public final ReleaseResultByGameId releaseResultByGameId;

    public ScoringDataBase(CqlSession session){
        this.session = session;

        releaseResultByGameId = new ReleaseResultByGameId(this.session);
    }

}
