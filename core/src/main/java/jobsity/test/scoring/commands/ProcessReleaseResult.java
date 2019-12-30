package jobsity.test.scoring.commands;

import com.google.protobuf.Timestamp;
import cyclops.control.Either;
import cyclops.control.Future;
import cyclops.control.Reader;
import cyclops.data.NonEmptyList;
import cyclops.monads.AnyM;
import cyclops.monads.Witness;
import cyclops.monads.transformers.EitherT;
import io.vavr.Tuple2;
import io.vavr.Tuple5;
import jobsity.test.Environment;
import jobsity.test.Event;
import jobsity.test.ServiceError;
import jobsity.test.command.Command;
import jobsity.test.scoring.ScoringEnvironment;
import jobsity.test.scoring.domain.ReleaseResult;
import jobsity.test.scoring.domain.errors.ApplicationError;
import jobsity.test.scoring.domain.events.ReleaseResultProcessed;
import jobsity.test.scoring.protocol.Scoring;
import jobsity.test.util.TimeConverter;
import jobsity.test.util.Validations;

import java.util.Arrays;
import java.util.List;

public class ProcessReleaseResult implements Command {

    public final Scoring.ProcessReleaseResultRequest request;

    public ProcessReleaseResult(Scoring.ProcessReleaseResultRequest request){
        this.request = request;
    }

    @Override
    public Reader<Tuple2<Environment,String>, EitherT<Witness.future, NonEmptyList<ServiceError>, List<Event>>> execute() {
        return
        Reader.of( e -> {
            ScoringEnvironment environment = (ScoringEnvironment) e._1;
            String correlationId           = e._2;

            return
            validateReleaseRequest(request)
            .flatMapT(values -> {
                ReleaseResult releaseResult =
                        new ReleaseResult(
                        	(String) values._1
	                        , (String) values._2
	                        , (Integer) values._3
	                        , (Integer) values._4
	                        , TimeConverter.timeStampToInstant((Timestamp)values._5)
                        );

                return
                environment.scoringRepository.saveProcessReleaseResult(releaseResult)
                .map( result -> Arrays.asList(new ReleaseResultProcessed("topic") ) );
            });
        });
    }

    public EitherT<Witness.future, NonEmptyList<ServiceError>, Tuple5> validateReleaseRequest(Scoring.ProcessReleaseResultRequest request) {
        return
       EitherT.<Witness.future, NonEmptyList<ServiceError>, Tuple5>of(
               AnyM.<Either<NonEmptyList<ServiceError>, Tuple5>>fromFuture(
            Future.<Either<NonEmptyList<ServiceError>, Tuple5>>ofResult(
            Validations.shouldExist(request.getGameName(), "gameName").toEither()
              .flatMap( gameName ->
                  Validations.shouldExist(request.getGamerName(), "gamerName").toEither()
                    .flatMap( gamerName ->
                       Validations.shouldExist(request.getReleaseNumber(), "releaseNumber").toEither()
                         .flatMap( releaseNumber ->
                            Validations.shouldExist(request.getScore(), "score").toEither()
	                            .flatMap( score ->
		                            Validations.shouldExist(request.getTimeStamp(), "timeStamp").toEither()
			                            .map(timeStamp -> new Tuple5(gameName,gamerName,releaseNumber,score,timeStamp) )
	                            )
                         )
                    )
              ).<NonEmptyList<ServiceError>>mapLeft(errors ->
                    errors.map( error -> new ApplicationError( new Exception(error) ) )
               )
            )
          )
       );
    }
}
