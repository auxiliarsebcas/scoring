package jobsity.test.scoring.domain;

import java.util.List;

public class ScoreResult {
	public final String gamerName;
	public final List<Frame> frames;

	public ScoreResult(String gamerName, List<Frame> frames){
		this.gamerName = gamerName;
		this.frames    = frames;
	}
}
