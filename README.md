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



To find all pet types



To add a new pet



To find a pet from Id

    

To update a pet

    curl -H 'Content-Type: application/json' -d '{"petId":1, "petType":"dog", "petName":"shedy", "petAge":2}' -X PUT http://localhost:8080/v1/pets/updatePet

To delete a pet

    curl -X DELETE http://localhost:8080/v1/pets/deletePet/1
    
    
