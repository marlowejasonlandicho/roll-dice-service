package com.assignment.rolldice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.rolldice.entity.DiceRollDto;
import com.assignment.rolldice.service.RollDiceService;

/**
 * REST Controller Roll Dice API.
 * 
 * @author marlowelandicho
 *
 */
@Validated
@RestController
@RequestMapping(path = "/api/dice/")
public class RollDiceController {

	@Autowired
	private RollDiceService rollDiceService;

	/**
	 * <p>
	 * Returns the result of rolling a dice given parameters
	 * </p>
	 * 
	 * @param numOfDice       specifies the number of dice to roll
	 * @param numOfSideOfDice specifies how many sides the dice have. Minimum of 4
	 * @param numOfRolls      the number of times each dice has to be rolled
	 * @return list of dice rolls executed with the sum for each roll in the dice
	 *         and the number of times given sum has occurred.
	 */
	@GetMapping(path = "/roll")
	public List<DiceRollDto> rollDice(@Min(1) @NotNull @RequestParam int numOfDice,
			@Min(4) @NotNull @RequestParam int numOfSideOfDice, @Min(1) @NotNull @RequestParam int numOfRolls) {
		return rollDiceService.rollDice(numOfDice, numOfSideOfDice, numOfRolls);
	}

	/**
	 * <p>
	 * Returns the the total number of simulations and total rolls made, per dice
	 * number–dice side combinations.
	 * </p>
	 * 
	 * @param numOfDice       specifies the number of dice to roll
	 * @param numOfSideOfDice specifies how many sides the dice have. Minimum of 4
	 * @return list of dice rolls executed with the sum for each roll in the dice
	 *         and the number of times given sum has occurred.
	 */
	@GetMapping(path = "/simulation/total")
	public Map<String, Object> getTotalSimulation(@Min(1) @NotNull @RequestParam int numOfDice,
			@Min(4) @NotNull @RequestParam int numOfSideOfDice) {
		return rollDiceService.getNumberOfRollSimulations(numOfDice, numOfSideOfDice);
	}

	/**
	 * <p>
	 * Returns the the relative distribution, compared to the total rolls, for all
	 * the simulations.
	 * </p>
	 * 
	 * @param numOfDice       specifies the number of dice to roll
	 * @param numOfSideOfDice specifies how many sides the dice have. Minimum of 4
	 * @return list of Dice Roll Result relative distribution per dice number–dice
	 *         side combinations.
	 */
	@GetMapping(path = "/relative/distribution")
	public List<Map<String, Object>> getRelativeDistribution(@Min(1) @NotNull @RequestParam int numOfDice,
			@Min(4) @NotNull @RequestParam int numOfSideOfDice) {
		return rollDiceService.getRelativeDistribution(numOfDice, numOfSideOfDice);
	}

}
