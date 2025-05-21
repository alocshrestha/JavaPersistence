package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();

    Speaker create(Speaker speaker);

    /*
        getSpeaker - return the user which got created from the create method
         */
    Speaker getSpeaker(int id);

    Speaker update(Speaker speaker);

    void update(List<Object[]> pairs);

    void delete(int id);
}
