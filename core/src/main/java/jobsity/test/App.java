
package jobsity.test;

import com.datastax.oss.driver.api.core.CqlSession;
import cyclops.control.Try;
import io.vavr.Lazy;
import jobsity.test.infrastructure.adapters.cassandra.databases.ScoringDataBase;
import jobsity.test.infrastructure.configuration.ScoringConfiguration;
import jobsity.test.scoring.ScoringEnvironment;
import jobsity.test.scoring.repositories.ScoringRepositoryImpl;

public class App {

    public static void main(String[] args) {
        ScoringConfiguration config = new ScoringConfiguration();

        final CqlSession cassandraSession =
                Try.withCatch( () -> CqlSession.builder().build(), Exception.class )
                        .onFail( error -> {
                            System.out.println("error");
                            System.exit(0);
                        })
                        .get()
                        .fold( session -> session, () -> CqlSession.builder().build() );

        ScoringDataBase scoringDataBase = new ScoringDataBase(cassandraSession);

        ScoringRepositoryImpl scoringRepository = new ScoringRepositoryImpl(scoringDataBase);


        ScoringEnvironment environment = new ScoringEnvironment(config, scoringRepository);
    }
}
