package jobsity.test.infrastructure.adapters.cassandra.configuration;

import java.util.List;

public class CassandraConfiguration {
    public final List<String> contactPoints;

    public CassandraConfiguration(List<String> contactPoints) {
        this.contactPoints = contactPoints;
    }
}
