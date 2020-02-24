package com.assignment.rolldice.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * <p>
 * Database model of the Dice roll
 * </p>
 * 
 * @author marlowelandicho
 * 
 */

@Entity
@Table(name = "dice_roll", indexes = { 
		@Index(name = "num_of_dice_idx", columnList = "num_of_dice", unique = false),
		@Index(name = "num_of_side_of_dice_idx", columnList = "num_of_side_of_dice", unique = false),
		@Index(name = "num_of_dice_and_side_idx", columnList = "num_of_dice, num_of_side_of_dice", unique = false) })
public class DiceRoll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dice_roll_id")
	private Long diceRollId;

	@Column(name = "num_of_dice", nullable = false)
	private int numOfDice;

	@Column(name = "num_of_side_of_dice", nullable = false)
	private int numOfSideOfDice;

	@Column(name = "num_of_rolls", nullable = false)
	private int numOfRolls;

	@Column(name = "roll_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date rollDate = new Date();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "diceRoll", orphanRemoval = true)
	private List<DiceRollResult> diceRollResults = new ArrayList<>();

	public Long getDiceRollId() {
		return diceRollId;
	}

	public void setDiceRollId(Long diceRollId) {
		this.diceRollId = diceRollId;
	}

	public int getNumOfDice() {
		return numOfDice;
	}

	public void setNumOfDice(int numOfDice) {
		this.numOfDice = numOfDice;
	}

	public int getNumOfSideOfDice() {
		return numOfSideOfDice;
	}

	public void setNumOfSideOfDice(int numOfSideOfDice) {
		this.numOfSideOfDice = numOfSideOfDice;
	}

	public int getNumOfRolls() {
		return numOfRolls;
	}

	public void setNumOfRolls(int numOfRolls) {
		this.numOfRolls = numOfRolls;
	}

	public Date getRollDate() {
		return rollDate;
	}

	public void setDiceRollDate(Date rollDate) {
		this.rollDate = rollDate;
	}

	public List<DiceRollResult> getDiceRollResults() {
		return diceRollResults;
	}

	public void setDiceRollResults(List<DiceRollResult> diceRollResults) {
		this.diceRollResults = diceRollResults;
	}

}
