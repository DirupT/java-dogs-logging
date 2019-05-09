package com.lambdaschool.projectrestdogs.controller;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.model.Dog;
import com.lambdaschool.projectrestdogs.services.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/dogs")
public class DogController
{
    private static final Logger logger = LoggerFactory.getLogger(DogController.class);

    private MessageSender messageSender;

    public DogController(MessageSender messageSender)
    {
        this.messageSender = messageSender;
    }

    // localhost:8080/dogs/dogs
    @GetMapping(value = "/dogs")
    public ResponseEntity<?> getAllDogs()
    {
        String message = "/dogs/dogs accessed";
        messageSender.sendMessage(ProjectrestdogsApplication.DOGS_ENDPOINT, message);
        logger.info(message);

        return new ResponseEntity<>(ProjectrestdogsApplication.ourDogList.dogList, HttpStatus.OK);
    }

    // localhost:8080/dogs/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDogDetail(@PathVariable long id)
    {
        String message = "/dogs/{id} accessed";

        messageSender.sendMessage(ProjectrestdogsApplication.DOGS_ENDPOINT, message);
        logger.info(message);
        logger.debug("/dogs/{id} accessed with the id " + id);

        Dog rtnDog = ProjectrestdogsApplication.ourDogList.findDog(d -> (d.getId() == id));
        return new ResponseEntity<>(rtnDog, HttpStatus.OK);
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}")
    public ResponseEntity<?> getDogBreeds (@PathVariable String breed)
    {
    	String message = "/dogs/breeds/{breed} accessed";
    	messageSender.sendMessage(ProjectrestdogsApplication.DOGS_BREED_ENDPOINT, message);
//        logger.info(message);
//        logger.debug("/dogs/breeds/{breed} accessed with the breed " + breed);

        ArrayList<Dog> rtnDogs = ProjectrestdogsApplication.ourDogList.
                findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()));
        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);
    }
}
