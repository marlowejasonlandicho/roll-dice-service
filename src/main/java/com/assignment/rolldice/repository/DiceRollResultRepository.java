package com.assignment.rolldice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.assignment.rolldice.entity.DiceRollResult;

/**
 *
 * JPA Repository interface for CRUD operations on DiceRollResult database
 * instances
 * 
 * @author marlowelandicho
 * 
 */

public interface DiceRollResultRepository extends CrudRepository<DiceRollResult, Long> {

	/**
	 * <p>
	 * Finds all instance of DiceRollResult per dice number–dice side combinations.
	 * </p>
	 * 
	 * @param numOfDice       specifies the number of dice to roll
	 * @param numOfSideOfDice specifies how many sides the dice have
	 * 
	 * @return a List of DiceRollResult
	 */
	@Query("SELECT d.rollSum AS rollSum, SUM(d.rollSumCount) AS rollSumCount " + 
			"FROM DiceRollResult d " + 
			"WHERE d.diceRoll.numOfDice = :numOfDice AND d.diceRoll.numOfSideOfDice = :numOfSideOfDice " +
			"GROUP BY d.rollSum ")
	 List<Map<String, Object>> getRollSumAndRollSumCount(@Param("numOfDice") int numOfDice,
			@Param("numOfSideOfDice") int numOfSideOfDice);
	
}
