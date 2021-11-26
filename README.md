# PetStore API

## Introduction

This is a PetStore API which can be consumed to develop a PetStore web application.

## Packaging and running the application

To build the application:

    ./gradlew build 
    
To run the application:

    java -jar build/petstore-runner.jar

To run the test suite

    ./gradlew test

## To run a CURL/WGET commands

To find all pets

    curl -H 'Accept: application/json' http://localhost:8080/v1/pets

To find all pet types

    curl -H 'Accept: application/json' http://localhost:8080/v1/pets/types

To add a new pet

    curl -X POST -H 'Content-Type: application/json' -d '{"petType":"cat", "petName":"shedy", "petAge":2}' http://localhost:8080/v1/pets/addPet

To find a pet from Id

    curl -H 'Accept: application/json' http://localhost:8080/v1/pets/1

To update a pet

    curl -X PUT -H 'Content-Type: application/json' -d '{"petId":1, "petType":"dog", "petName":"shedy", "petAge":2}' http://localhost:8080/v1/pets/updatePet

To delete a pet

    curl -X DELETE http://localhost:8080/v1/pets/deletePet/1
    
    
