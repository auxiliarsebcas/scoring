
package jobsity.test.infrastructure.configuration;

import jobsity.test.App;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ScoringConfigurationTest {
    @Test
    public void testAppHasAGreeting() {
        ScoringConfiguration config = new ScoringConfiguration();

        assertEquals("configuration should read service name", "scoring", config.serviceName);
        assertEquals("configuration should read cassandra contact Points", "127.0.0.1:9043", config.cassandraConfiguration.contactPoints.get(0));
    }
}
