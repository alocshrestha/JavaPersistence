package com.pluralsight.conference;

import com.pluralsight.conference.model.Speaker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest , commenting to simply use UNIT tests instead.
class Spring6jdbc3ApplicationTests {
    @Test
    public void testCreateSpeaker(){
        RestTemplate restTemplate = new RestTemplate();

        Speaker speaker = new Speaker();
        speaker.setName("Roberto Carlos");

        speaker = restTemplate.postForObject("http://localhost:8080/speaker", speaker, Speaker.class);
        System.out.println(speaker.getName());
    }

    @Test
    void testGetSpeakers() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Speaker>> speakersResponse = restTemplate.exchange(
                "http://localhost:8080/speaker", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Speaker>>() {
                });

        assertTrue(speakersResponse.getBody() != null, "Body is null");

        List<Speaker> speakers = speakersResponse.getBody();

        for (Speaker speaker : speakers) {
            System.out.println("Speaker name: " + speaker.getName() + " " + speaker.getSkill());
        }
    }

    @Test
    void testGetSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        Speaker speaker = restTemplate.getForObject("http://localhost:8080/speaker/{id}", Speaker.class, 17);

        System.out.println(speaker.getName());
    }

    @Test
    void testUpdateSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        Speaker speaker = restTemplate.getForObject("http://localhost:8080/speaker/{id}", Speaker.class, 17);
        speaker.setName(speaker.getName() + " Sr. ");
        restTemplate.put("http://localhost:8080/speaker", speaker);

        System.out.println(speaker.getName());
    }

    @Test
    public void testBatchUpdate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8080/speaker/batch", Object.class);
    }

    @Test
    void testDeleteSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete("http://localhost:8080/speaker/delete/{id}", 37);

    }

    @Test
    void testException() {
        RestTemplate restTemplate = new RestTemplate();
        //why return the speaker.class? We are not going to return a speaker out but to test our exception handling functionality.
        restTemplate.getForObject("http://localhost:8080/speaker/test", Speaker.class);

    }

}
