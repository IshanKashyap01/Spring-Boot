package com.codingninjas.EVotingSystem.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.codingninjas.EVotingSystem.entities.Election;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;
import com.codingninjas.EVotingSystem.entities.User;
import com.codingninjas.EVotingSystem.entities.Vote;
import com.codingninjas.EVotingSystem.services.EVotingService;

@RestController
public class EVotingController
{
	private final EVotingService service;

	public EVotingController(EVotingService eVotingService)
	{
		this.service = eVotingService;
	}

	// Election

	@PostMapping("/add/election")
	public void addElection(@RequestBody Election election)
	{
		service.addElection(election);
	}

	@GetMapping("/get/elections")
	public List<Election> getAllElections()
	{
		return service.getAllElections();
	}

	// ElectionChoice

	@PostMapping("/add/electionChoice")
	public void addElectionChoice(@RequestBody ElectionChoice electionChoice)
	{
		service.addElectionChoice(electionChoice);
	}

	@GetMapping("/get/electionChoices")
	public List<ElectionChoice> getAllElectionChoices()
	{
		return service.getAllElectionChoices();		
	}

	@GetMapping("/count/{electionId}")
	public long getCountByElectionId(@PathVariable Long electionId)
	{
		return service.choicesByElection(electionId);
	}

	// User

	@PostMapping("/add/user")
	public void addUser(@RequestBody User user)
	{
		service.addUser(user);	
	}

	@GetMapping("/get/users")
	public List<User> getAllUsers()
	{
		return service.getAllUsers();
	}

	// Vote

	@PostMapping("/add/vote")
	public void addVote(@RequestParam Long userId, @RequestParam Long electionId, @RequestParam Long electionChoiceId)
	{
		service.addVote(userId, electionId, electionChoiceId);
	}

	@GetMapping("/get/votes")
	public List<Vote> getAllVotes()
	{
		return service.getAllVotes();
	}

	@GetMapping("/count/votes")
	public long countAllVotes()
	{
		return service.countTotalVotes();
	}

	@GetMapping("/count/votes/{electionName}")
	public long countVotesByElectionName(@PathVariable String electionName)
	{
		return service.countVotesByElectionName(electionName);
	}
	
	// Result

	@GetMapping("/winner/election/{electionName}")
	public ElectionChoice getElectionWinner(@PathVariable String electionName)
	{
		return service.findElectionWinner(electionName);
	}
}