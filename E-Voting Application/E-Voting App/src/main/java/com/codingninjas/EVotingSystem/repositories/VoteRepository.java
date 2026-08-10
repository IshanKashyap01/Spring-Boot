package com.codingninjas.EVotingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codingninjas.EVotingSystem.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>
{
    boolean existsByUserIdAndElectionIdAndElectionChoiceId(long userId, long electionId, long electionChoiceId);

    long countBy();

    long countByElectionName(String electionName);
}