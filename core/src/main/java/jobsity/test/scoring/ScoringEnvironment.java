package jobsity.test.scoring;

import jobsity.test.Environment;
import jobsity.test.infrastructure.configuration.ScoringConfiguration;
import jobsity.test.scoring.repositories.ScoringRepository;

public class ScoringEnvironment implements Environment {
    public final ScoringConfiguration config;
    public final ScoringRepository scoringRepository;

    public ScoringEnvironment(ScoringConfiguration config, ScoringRepository scoringRepository){
        this.config            = config;
        this.scoringRepository = scoringRepository;
    }
}
