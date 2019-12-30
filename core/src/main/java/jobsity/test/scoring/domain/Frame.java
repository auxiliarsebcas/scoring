package jobsity.test.scoring.domain;

import io.vavr.Tuple2;

public class Frame {
	public final int frameNumber;
	public final Tuple2<String,String> pinFalls;
	public final int score;

	public Frame(int frameNumber, Tuple2<String,String> pinFalls, int score){
		this.frameNumber = frameNumber;
		this.pinFalls    = pinFalls;
		this.score       = score;
	}
}
