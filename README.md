# Transformers Service
## Introduction

Using this service, you can create, update and delete a transformer. There is a simple API to make them battle wherein you give the IDs as an HTTP param. As of now this service uses in memory hashmap, but the DAO can be switched to one using cloud data stores like Dynamo DB.

## Run Service

Build the jar using: `mvn clean package`

Run using:

`java -jar target/transformers-service-1.0.jar`

The service will run on port 8080 and uses Spring Boot with Netty.

## API Usage

On a very basic level you can create a transformer with just team name. In that case all properties or powers will be set to 1 (default minimum) and name is optional. E.g.

`curl -X POST -H 'Content-Type: application/json' localhost:8080/transformer -d '{"team":"A"}'`

Simple curl command to create transformer with complete payload:

`curl -v  -X POST -H "Content-Type:application/json" localhost:8080/transformer -d '{`
`"team":"A",`
`"name":"Autobot-42",`
`"strength":7,`
`"intelligence":1,`
`"speed":1, `
`"endurance":1,`
`"rank":10, `
`"courage":7, `
`"firepower":1, `
`"skill":7}'`

Other options on the `/transformer` path are using PUT (update but requires the whole payload), GET (to get the current json payload for transformer) and DELETE.

Using the following command you can list added transformers:

`curl -v localhost:8080/transformers | json_pp`

Finally to engage a subset in a battle, you should use:

`curl localhost:8080/battle?ids="9f855705-78b5-4d2b-afba-38fb3833317c","e9f71ca6-6ba5-42b5-8c12-3f117bda15d0"`

This will give output like (both died in this single battle):

`{
"winningTeam":null,
"winnerTransformers":[],
"survivorTransformers":[],
"numOfBattles":1
}`

To have a battle with all transformers, run without optional ids `curl localhost:8080/battle`.

## Assumptions

If no team wins, then winning team list as well as losing survivors will be empty (though there will be survivors).

## Future

It would be interesting to have dead transformers marked as killed in DB and have more join in for next set of battles. It would need code changes if more teams need to be added. We would need to add to team enum and ask input in REST call which team vs team battle you wish to see.

