package jobsity.test.infrastructure.configuration;


import com.datastax.oss.driver.api.core.CqlSession;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import jobsity.test.infrastructure.adapters.cassandra.configuration.CassandraConfiguration;
import jobsity.test.infrastructure.kafka.KafkaConfig;

import java.util.List;


public class ScoringConfiguration {
    public final String serviceName;
    public final CassandraConfiguration cassandraConfiguration;
    public final KafkaConfig kafkaConfig;


    public ScoringConfiguration() {
        Config conf = ConfigFactory.load();

        this.serviceName = conf.getString("serviceName");
        this.cassandraConfiguration = new CassandraConfiguration(
                conf.getStringList("datastax-java-driver.basic.contact-points")
        );

        List<String> kafkaHost = conf.getStringList("kafka.hosts");
        String kafkaPort = conf.getString("kafka.port");
        String kafkaGroupId = conf.getString("kafka.group_id");

        this.kafkaConfig = new KafkaConfig(kafkaGroupId,kafkaHost,kafkaPort);
    }
}
