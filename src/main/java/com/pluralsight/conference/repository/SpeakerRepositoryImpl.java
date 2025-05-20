package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Speaker;
import com.pluralsight.conference.repository.util.SpeakerRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {


    //to inject or autowire a jdbc template
    private JdbcTemplate jdbcTemplate;
    //to autowire - setup param constructor
    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<Speaker> findAll() {
        List<Speaker> speakers = jdbcTemplate.query("select * from speaker", new SpeakerRowMapper());
        return speakers;
    }

    @Override
    public Speaker create(Speaker speaker) {
        //jdbcTemplate.update("INSERT INTO speaker (name) VALUES (?)", speaker.getName());
        //using SimpleJdbcInsert
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.setTableName("speaker"); //table we want to be working with

        List<String> columns = new ArrayList<>();
        columns.add("name");

        Map<String, Object> data = new HashMap<>();
        data.put("name", speaker.getName()); //this is the data we want to insert

        insert.setGeneratedKeyName("id");
        Number key = insert.executeAndReturnKey(data);
        System.out.println(key);
        return getSpeaker(key.intValue());
    }

    /*
    getSpeaker - return the user which got created from the create method
     */
    private Speaker getSpeaker(int id){
        return jdbcTemplate.queryForObject("select * from speaker where id = ?", new SpeakerRowMapper(), id);
    }
}
