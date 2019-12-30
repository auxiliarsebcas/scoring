package jobsity.test.scoring.domain;

import java.util.List;

public class GameResult {
	public final String gameName;
	public final List<ScoreResult> scoreResults;

	public GameResult(String gameName, List<ScoreResult> scoreResults){
		this.gameName     = gameName;
		this.scoreResults = scoreResults;
	}
}
