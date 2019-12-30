package jobsity.test.scoring.domain;


import java.time.Instant;

public class ReleaseResult {
    public final String gameName;
    public final String gamerId;
    public final int    releaseNumber;
    public final int    score;
    public final Instant instant;

    public ReleaseResult(String gameName, String gamerId, int releaseNumber, int score, Instant instant) {
        this.gameName      = gameName;
        this.gamerId       = gamerId;
        this.releaseNumber = releaseNumber;
        this.score         = score;
        this.instant       = instant;
    }

}
