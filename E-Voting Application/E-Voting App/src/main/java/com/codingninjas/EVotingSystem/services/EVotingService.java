package com.codingninjas.EVotingSystem.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codingninjas.EVotingSystem.entities.*;
import com.codingninjas.EVotingSystem.exception.VoteAlreadyCastedException;
import com.codingninjas.EVotingSystem.repositories.*;
import jakarta.transaction.Transactional;

@Service
public class EVotingService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    ElectionChoiceRepository electionChoiceRepository;

    public List<Election> getAllElections()
    {
        return electionRepository.findAll();
    }

    public void addElection(Election election)
    {
        electionRepository.save(election);        
    }

    @Transactional
    public void addElectionChoice(ElectionChoice electionChoice)
    {
        long electionId = electionChoice.getElection().getId();
        Election election = electionRepository.findById(electionId).get();
        electionChoice.setElection(election);
        electionChoiceRepository.save(electionChoice);
    }

    public List<ElectionChoice> getAllElectionChoices()
    {
        return electionChoiceRepository.findAll();
    }

    public long choicesByElection(Long electionId)
    {
        return electionChoiceRepository.countElectionChoices(electionId);
    }

    public void addUser(User user)
    {
        userRepository.save(user);
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Transactional
    public void addVote(Long userId, Long electionId, Long electionChoiceId)
    {
        if(voteRepository.existsByUserIdAndElectionIdAndElectionChoiceId(userId, electionId, electionChoiceId))
        {
            throw new VoteAlreadyCastedException("You have already given your vote");
        }
        Vote vote = new Vote();
        vote.setUser(userRepository.findById(userId).get());
        vote.setElection(electionRepository.findById(electionId).get());
        vote.setElectionChoice(electionChoiceRepository.findById(electionChoiceId).get());
        voteRepository.save(vote);
    }

    public List<Vote> getAllVotes()
    {
        return voteRepository.findAll();  
    }

    public long countTotalVotes()
    {
        return voteRepository.countBy();       
    }

    public long countVotesByElectionName(String electionName)
    {
        return voteRepository.countByElectionName(electionName);
    }

    @Transactional
	public ElectionChoice findElectionWinner(String electionName)
    {
        Election election = electionRepository.findByName(electionName);
        return electionChoiceRepository.findElectionChoiceWithMaxVotes(election.getId());
	}
}
