package jobsity.test.scoring.domain;

import cyclops.control.Try;
import cyclops.data.NonEmptyList;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import jobsity.test.ServiceError;
import jobsity.test.scoring.domain.valueObjects.SpareBonus;
import jobsity.test.scoring.domain.valueObjects.StrikeBonus;

import java.util.*;
import java.util.stream.Collectors;

public class Rules {

	public  Try<GameResult,Throwable> calculateGameResult(List<ReleaseResult> results) {
		Map<String, List<ReleaseResult>> gamersMap =
		results.stream().collect(Collectors.groupingBy(releaseResult -> releaseResult.gameName) );

    return
		Try.withCatch( () ->
			gamersMap.keySet()
				.stream()
				.map(gamerName -> {
					List orderedReleseResult =
					gamersMap.get(gamerName).stream()
					.sorted((release1, release2) -> release1.instant.compareTo(release2.instant))
						.collect(Collectors.toList());

					List<Frame> frames =	calculateFramesResult( Collections.synchronizedList( orderedReleseResult ) );
					return
						new ScoreResult(
							  gamerName
							, frames
						);
				})
				.collect( Collectors.toList() )
		).<GameResult>map( scoreResults -> new GameResult(results.get(0).gameName, scoreResults) );
	}

	public Tuple3<Frame, List<ReleaseResult>, Integer> calculateFrameResult(final List<ReleaseResult> releaseResults, final int head , final int frameCount) {
		final int nextRelease = head+1;
		if(releaseResults.get(head).score==10){
			return new Tuple3<>(
				  new Frame(frameCount,new Tuple2<>("x",""), releaseResults.get(head).score+ StrikeBonus.bonus)
				, releaseResults
				, head+1
			);
		}
		else {
			if(releaseResults.get(head).score + releaseResults.get(nextRelease).score == 10 ){
				int score = releaseResults.get(head).score + releaseResults.get(nextRelease).score+ SpareBonus.bonus;
				return new Tuple3(
					  new Frame( frameCount,new Tuple2<>(Integer.toString(releaseResults.get(head).score),"/"), score )
					, releaseResults
					, nextRelease+1
				);
			}
			else {
				int score = releaseResults.get(head).score + releaseResults.get(nextRelease).score;
				return new Tuple3(
					  new Frame(frameCount,new Tuple2<>( Integer.toString(releaseResults.get(head).score), Integer.toString(releaseResults.get(nextRelease).score) ), score )
					, releaseResults
					, nextRelease+1
				);
			}
		}
	}

	public List<Frame> calculateFramesResult(final List<ReleaseResult> releaseResults) {
	  return calculateFramesResult(releaseResults, 0, 1);
	}

	public List<Frame> calculateFramesResult(List<ReleaseResult> releaseResults, final int head, final int frameCount){
		if(head<releaseResults.size()){
			Tuple3<Frame, List<ReleaseResult>, Integer> calculusResutl =
				calculateFrameResult(releaseResults, head, frameCount);

			//TODO it should be immutable, this is a wrong implementation
			List<Frame> l = (new ArrayList());
			l.add( calculusResutl._1 );
			l.addAll( calculateFramesResult(calculusResutl._2, calculusResutl._3, frameCount+1) );
			return l;
		}
		else return new ArrayList<>();
	}


}
