package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Speaker;
import org.springframework.stereotype.Repository;
//import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {

    /*
    //to inject or autowire a jdbc template
    private JdbcTemplate jdbcTemplate;
    //to autowire - setup param constructor
    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
     */


    public List<Speaker> findAll() {
        Speaker speaker = new Speaker();
        speaker.setName("Aloc SH");
        speaker.setSkill("Java");
        List<Speaker> speakers = new ArrayList<>();
        speakers.add(speaker);
        return speakers;
    }

    @Override
    public Speaker create(Speaker speaker) {
        //jdbcTemplate.update("INSERT INTO speaker (name) VALUES (?)", speaker.getName());
        return null;
    }
}
