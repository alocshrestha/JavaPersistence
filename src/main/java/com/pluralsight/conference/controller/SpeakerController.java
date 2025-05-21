package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.Speaker;
import com.pluralsight.conference.service.SpeakerService;
import com.pluralsight.conference.util.ServiceError;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpeakerController {

    private SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @PostMapping("/speaker")
    public Speaker createSpeaker(@RequestBody Speaker speaker) {
        System.out.println("Name: " + speaker.getName());
        return speakerService.create(speaker);
    }

    @GetMapping("/speaker")
    public List<Speaker> getSpeakers() {
        return speakerService.findAll();
    }

    /*
    method to implement the get http request for the url defined below. /speaker/{id}
    Grab that parameter off the path url and stored that into an id int
    and retrieve it using the getSpeaker() from our service tier.
     */
    @GetMapping("/speaker/{id}")
    public Speaker getSpeaker(@PathVariable(value = "id") int id) {
        return speakerService.getSpeaker(id);
    }


    @PutMapping("/speaker")
    public Speaker updateSpeaker(@RequestBody Speaker speaker) {
        System.out.println("Name: " + speaker.getName());
        return speakerService.update(speaker);
    }

    @GetMapping("/speaker/batch")
    public Object batch() {
        speakerService.batch();
        return null;
    }

    @DeleteMapping("/speaker/delete/{id}")
    public Object deleteSpeaker(@PathVariable(value = "id") int id) {
        speakerService.delete(id);
        return null;
    }

    @GetMapping("/speaker/test")
    public Object testException() {
        throw new DataAccessException("Testing exception thrown") {};
    }


    /*
    handle - exception handler to catch for all RuntimeException Classes.
    for the body is a response entity typed as our ServiceError that handles any RuntimeException ex.
    Contain any error as ServiceError gracefully.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceError> handle (RuntimeException ex){
        ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK); //will catch the exception being thrown from above and will generate valid resp back to our service
    }


}
