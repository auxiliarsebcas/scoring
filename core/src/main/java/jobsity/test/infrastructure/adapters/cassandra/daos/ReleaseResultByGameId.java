package jobsity.test.infrastructure.adapters.cassandra.daos;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import cyclops.control.Future;

import jobsity.test.infrastructure.adapters.cassandra.records.ReleaseResultRecord;
import jobsity.test.util.TimeConverter;
import jobsity.test.util.Validations;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.*;

public class ReleaseResultByGameId {
    public final CqlSession session;
    public final String tableName = "RELEASE_RESULT_BY_GAME_ID";

    public final String gameIdColum        = "game_id";
    public final String gamerIdColum       = "gamer_id";
    public final String releaseNumberColum = "release_number";
    public final String scoreColum         = "score";
    public final String dateTimeColum      = "date_time";

    public ReleaseResultByGameId(CqlSession session) {
        this.session = session;
    }

    public Future<ReleaseResultRecord> getRecordById(String recordId) {
        SimpleStatement statement =
                selectFrom(this.tableName)
                        .all()
                        .whereColumn("id").isEqualTo(bindMarker())
                        .build(recordId);

        return
        Future.empty().flatMapCf( r ->
                session.executeAsync(statement)
         )
        .flatMap( result -> {
            Row row = result.one();

            return
            Future.fromTry(
              Validations.shouldExist(row.get(this.gameIdColum, String.class), this.gameIdColum).toEither()
                  .flatMap(gameId ->
                    Validations.shouldExist(row.get(this.gamerIdColum, String.class), this.gamerIdColum).toEither()
                    .flatMap(gamerId ->
                      Validations.shouldExist(row.get(this.releaseNumberColum, Integer.class), this.releaseNumberColum).toEither()
                      .flatMap(releaseNumber ->
                        Validations.shouldExist(row.get(this.scoreColum, Integer.class), this.scoreColum).toEither()
                          .flatMap( score ->
                            Validations.shouldExist(row.get(this.dateTimeColum, String.class), this.dateTimeColum).toEither()
                              .map(dateTime ->
                                new ReleaseResultRecord(gameId,gamerId,releaseNumber, score, dateTime)
                              )
                          )
                      )
                    )
                  ).bimap( error-> new Exception(error.head()  ), r -> r ).toTry()
            );
        });
    }



}
