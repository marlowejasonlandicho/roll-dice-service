package com.assignment.rolldice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * <p>
 * Database model of the Dice Roll Result
 * </p>
 * 
 * @author marlowelandicho
 * 
 */

@Entity
@Table(name = "dice_roll_result", indexes = { 
		@Index(name = "roll_sum_idx", columnList = "roll_sum", unique = false),
		@Index(name = "roll_sum_count_idx", columnList = "roll_sum_count", unique = false),
		@Index(name = "roll_sum_roll_sum_count_idx", columnList = "roll_sum, roll_sum_count", unique = false) })
public class DiceRollResult {

	@Id
	@Column(name = "dice_roll_result_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long diceRollResultId;

	@Column(name = "roll_sum", nullable = false)
	private int rollSum;

	@Column(name = "roll_sum_count", nullable = false)
	private int rollSumCount;

	@Column(name = "roll_result_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date rollResultDate = new Date();

	@ManyToOne
	@JoinColumn(name = "dice_roll_id", nullable = false)
	private DiceRoll diceRoll;

	public Long getDiceRollResultId() {
		return diceRollResultId;
	}

	public void setDiceRollResultId(Long diceRollResultId) {
		this.diceRollResultId = diceRollResultId;
	}

	public int getRollSum() {
		return rollSum;
	}

	public void setRollSum(int rollSum) {
		this.rollSum = rollSum;
	}

	public int getRollSumCount() {
		return rollSumCount;
	}

	public void setRollSumCount(int rollSumCount) {
		this.rollSumCount = rollSumCount;
	}

	public Date getRollResultDate() {
		return rollResultDate;
	}

	public void setRollResultDate(Date rollResultDate) {
		this.rollResultDate = rollResultDate;
	}

	public DiceRoll getDiceRoll() {
		return diceRoll;
	}

	public void setDiceRoll(DiceRoll diceRoll) {
		this.diceRoll = diceRoll;
	}
}
