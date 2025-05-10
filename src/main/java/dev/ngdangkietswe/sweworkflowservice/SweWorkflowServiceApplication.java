package dev.ngdangkietswe.sweworkflowservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@Log4j2
public class SweWorkflowServiceApplication {

    public static void main(String[] args) {
        var applicationContext = SpringApplication.run(SweWorkflowServiceApplication.class, args);

        final var defaultHost = "localhost";
        final var applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");
        final var applicationPort = applicationContext.getEnvironment().getProperty("server.port");
        final var applicationHost = applicationContext.getEnvironment().getProperty("server.host", defaultHost);
        final var grpcPort = applicationContext.getEnvironment().getProperty("grpc.port");
        final var grpcHost = applicationContext.getEnvironment().getProperty("grpc.host", defaultHost);
        final var dbUrl = applicationContext.getEnvironment().getProperty("spring.datasource.url");

        String startupTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        log.info("""
                        \n-------------------------------
                        Application Started
                        -------------------------------
                        Time: {}
                        Name: {}
                        HTTP: http://{}:{}
                        gRPC: {}:{}
                        Database: {}
                        -------------------------------""",
                startupTime, applicationName, applicationHost, applicationPort, grpcHost, grpcPort, dbUrl);
    }
}
