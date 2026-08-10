package com.codingninjas.EVotingSystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vote")
public class Vote
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "election_id")
	private Election election;

	@ManyToOne
	@JoinColumn(name = "election_choice_id")
	private ElectionChoice electionChoice;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Election getElection()
	{
		return election;
	}

	public void setElection(Election election)
	{
		this.election = election;
	}

	public ElectionChoice getElectionChoice()
	{
		return electionChoice;
	}

	public void setElectionChoice(ElectionChoice electionChoice)
	{
		this.electionChoice = electionChoice;
	}
}