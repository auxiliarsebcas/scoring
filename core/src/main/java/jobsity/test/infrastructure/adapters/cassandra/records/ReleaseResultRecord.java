package jobsity.test.infrastructure.adapters.cassandra.records;

public class ReleaseResultRecord {
    public final String gameId;
    public final String gamerId;
    public final int    releaseNumber;
    public final int    score;
    public final String dateTime;

    public ReleaseResultRecord(String gameId, String gamerId, int releaseNumber, int score, String dateTime) {
        this.gameId        = gameId;
        this.gamerId       = gamerId;
        this.releaseNumber = releaseNumber;
        this.score         = score;
        this.dateTime      = dateTime;
    }
}
