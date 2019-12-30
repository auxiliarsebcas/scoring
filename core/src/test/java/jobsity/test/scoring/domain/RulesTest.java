
package jobsity.test.scoring.domain;

import cyclops.control.Option;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RulesTest {
	List<ReleaseResult> resutls = Arrays.asList(
		  new Tuple2("Jeff",	"10")
		, new Tuple2("Jeff",	"7")
		, new Tuple2("Jeff",	"3")
		, new Tuple2("Jeff",	"9")
		, new Tuple2("Jeff",	"0")
		, new Tuple2("Jeff",	"10")
		, new Tuple2("Jeff",	"0")
		, new Tuple2("Jeff",	"8")
		, new Tuple2("Jeff",	"8")
		, new Tuple2("Jeff",	"2")
		, new Tuple2("Jeff",	"0")
		, new Tuple2("Jeff",	"6")
		, new Tuple2("Jeff",	"10")
		, new Tuple2("Jeff",	"10")
		, new Tuple2("Jeff",	"10")
	).stream()
		.map(t -> new ReleaseResult("gameTest", (String) t._1, 1, Integer.valueOf((String)  t._2), Instant.now() ) )
		.collect(Collectors.toList());

    @Test
    public void testCalculateGameResult() {
	     (new Rules()).calculateGameResult(resutls)
		    .get()
		    .forEach( game -> {
			    System.out.println("\n\n resultado -> \n\n");
			    game.scoreResults.forEach(r -> {
			    	r.frames.forEach(f -> {
					    System.out.println("\n\n resultado -> score "+f.score+ " pinfalls "+ f.pinFalls._1 +" " + f.pinFalls._2 +" frameNumber "+f.frameNumber);
				    });
			    });

		    });

	    assert true;

    }
}
