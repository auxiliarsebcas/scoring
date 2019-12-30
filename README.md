SCOREING-SERVICE 
===========

based on this first approach 
https://github.com/auxiliarsebcas/simpleTest/pull/3
i have splited it into 3 services:
- ingest-score: ingest the score result for each release and send it asynchronously by kafka to scoring-service
- reports: request the result of process the scores for a game, and query by this using grpc to scoring-service
- scoring-service: is the core of the application. this service process the score send for each release by game
and save it in cassandra db. 

# sub projects
this repo has two subprocjects, the firts one is the core, and second one is the protocol.
the protocol project has the message definitions, and it is done using protobuf,
the proto si published and shared with the other services.

to publish the protocol (in local):

```bash
   ./gradlew protocol:publishToMavenLocal
```