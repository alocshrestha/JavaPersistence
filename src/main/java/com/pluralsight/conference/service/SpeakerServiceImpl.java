package com.pluralsight.conference.service;

import com.pluralsight.conference.model.Speaker;
import com.pluralsight.conference.repository.SpeakerRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository speakerRepository;

    public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public Speaker create(Speaker speaker) {
        return speakerRepository.create(speaker);
    }

    @Override
    public Speaker getSpeaker(int id) {
        return speakerRepository.getSpeaker(id);
    }

    @Override
    public Speaker update(Speaker speaker) {
        return speakerRepository.update(speaker);
    }

    @Override
    @Transactional
    public void batch() {
        List<Speaker> speakers = speakerRepository.findAll(); //get all of those objects back
        //we want to create the syntax, use in how we interact with it for the batch update
        List<Object[]> pairs = new ArrayList<>();

        //each one of the speakers, we can now take and create a pair for that skill coupled with the id
        for (Speaker speaker: speakers){
            Object [] tmp = {"Java", speaker.getId()};
            pairs.add(tmp);
        }

        speakerRepository.update(pairs); //will take that arraylist of pairs

        //tranactions example, throwing new exception here will cause this transactional function to rollback
        throw new DataAccessException("Error in batch!") {};
    }

    @Override
    public void delete(int id) {
        speakerRepository.delete(id);
    }
}
