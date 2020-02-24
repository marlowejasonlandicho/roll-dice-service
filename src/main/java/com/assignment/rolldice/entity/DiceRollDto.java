package com.assignment.rolldice.entity;

/**
 * 
 * <p>
 * DTO class for Dice Roll result
 * </p>
 * 
 * @author marlowelandicho
 * 
 */
public class DiceRollDto {

	public DiceRollDto() {
	}

	public DiceRollDto(int rollSum, int rollSumCount) {
		super();
		this.rollSum = rollSum;
		this.rollSumCount = rollSumCount;
	}

	private int rollSum;

	private int rollSumCount;

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
}
