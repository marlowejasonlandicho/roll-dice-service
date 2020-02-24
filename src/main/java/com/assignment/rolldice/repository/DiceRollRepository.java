package com.assignment.rolldice.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.assignment.rolldice.entity.DiceRoll;

/**
 *
 * JPA Repository interface for CRUD operations on DiceRoll database instances
 * 
 * @author marlowelandicho
 * 
 */

public interface DiceRollRepository extends CrudRepository<DiceRoll, Long> {

	/**
	 * <p>
	 * Counts total number of simulations and total rolls made, per dice number–dice
	 * side combinations.
	 * </p>
	 * 
	 * @param numOfDice       specifies the number of dice to roll
	 * @param numOfSideOfDice specifies how many sides the dice have
	 * 
	 * @return a map containing the numOfSimulations and totalRolls
	 */
	@Query("SELECT COUNT(*) AS numOfSimulations, SUM(d.numOfRolls) AS totalRolls " + 
			"FROM DiceRoll d "+ 
			"WHERE d.numOfDice = :numOfDice AND d.numOfSideOfDice = :numOfSideOfDice ")
	Map<String, Object> countSimulationAndRoll(@Param("numOfDice") int numOfDice,
			@Param("numOfSideOfDice") int numOfSideOfDice);

}
