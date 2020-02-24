package com.assignment.rolldice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.rolldice.entity.DiceRoll;
import com.assignment.rolldice.entity.DiceRollDto;
import com.assignment.rolldice.entity.DiceRollResult;
import com.assignment.rolldice.repository.DiceRollRepository;
import com.assignment.rolldice.repository.DiceRollResultRepository;

/**
 * Service for rolling dice.
 * 
 * @author marlowelandicho
 *
 */
@Service
@Transactional
public class RollDiceService {

	/**
	 * 
	 * Value equals 1
	 * 
	 */

	private static final int ONE_VALUE = 1;

	/**
	 * 
	 * Logging component
	 * 
	 */
	protected final Log LOG = LogFactory.getLog(getClass());

	@Autowired
	private DiceRollRepository diceRollRepository;

	@Autowired
	private DiceRollResultRepository diceRollResultRepository;

	public List<DiceRollDto> rollDice(int numOfDice, int numOfSideOfDice, int numOfRolls) {

		List<DiceRollDto> diceRollDtos = new ArrayList<>();

		for (int diceRollCtr = 1; diceRollCtr <= numOfRolls; diceRollCtr++) {
			int rollSumResult = 0;

			for (int diceCtr = 1; diceCtr <= numOfDice; diceCtr++) {
				final int diceValue = getBoundedRandomNumber(ONE_VALUE, numOfSideOfDice);
				LOG.info("Dice #" + diceCtr + " value: " + diceValue);
				rollSumResult += diceValue;
			}
			LOG.info("Roll Sum Result: " + rollSumResult);

			final DiceRollDto diceRoll = new DiceRollDto(rollSumResult, 1);
			final int diceRollSum = diceRoll.getRollSum();
			final DiceRollDto diceRollFromList = diceRollDtos.stream()
					.filter(diceRollElem -> diceRollElem.getRollSum() == diceRollSum).findFirst().orElse(null);

			if (Objects.nonNull(diceRollFromList)) {
				diceRollFromList.setRollSumCount(diceRollFromList.getRollSumCount() + 1);
			} else {
				diceRollDtos.add(diceRoll);
			}
		}

		final DiceRoll diceRoll = new DiceRoll();
		diceRoll.setNumOfDice(numOfDice);
		diceRoll.setNumOfSideOfDice(numOfSideOfDice);
		diceRoll.setNumOfRolls(numOfRolls);

		for (DiceRollDto diceRollDto : diceRollDtos) {
			final DiceRollResult diceRollResult = new DiceRollResult();
			diceRollResult.setRollSum(diceRollDto.getRollSum());
			diceRollResult.setRollSumCount(diceRollDto.getRollSumCount());
			diceRollResult.setDiceRoll(diceRoll);

			diceRoll.getDiceRollResults().add(diceRollResult);
		}

		diceRollRepository.save(diceRoll);

		return diceRollDtos;
	}

	public Map<String, Object> getNumberOfRollSimulations(int numOfDice, int numOfSideOfDice) {
		final Map<String, Object> resultMap = new HashMap<>();
		final Map<String, Object> resultMapFromDB = diceRollRepository.countSimulationAndRoll(numOfDice,
				numOfSideOfDice);

		if (Objects.nonNull(resultMapFromDB)) {
			final Object totalRolls = resultMapFromDB.get("totalRolls");
			final Object simulations = resultMapFromDB.get("numOfSimulations");

			if (Objects.isNull(totalRolls)) {
				resultMap.put("totalRolls", 0L);
			} else {
				resultMap.put("totalRolls", totalRolls);
			}
			if (Objects.isNull(simulations)) {
				resultMap.put("numOfSimulations", 0L);
			} else {
				resultMap.put("numOfSimulations", simulations);
			}
		}

		return resultMap;
	}

	public List<Map<String, Object>> getRelativeDistribution(int numOfDice, int numOfSideOfDice) {
		List<Map<String, Object>> resultList = new ArrayList<>();

		final Map<String, Object> simulationAndTotalMap = getNumberOfRollSimulations(numOfDice, numOfSideOfDice);
		final Long totalRolls = (Long) simulationAndTotalMap.get("totalRolls");

		List<Map<String, Object>> diceRollResults = diceRollResultRepository.getRollSumAndRollSumCount(numOfDice,
				numOfSideOfDice);

		for (Map<String, Object> diceRollResult : diceRollResults) {
			final Integer rollSum = (Integer) diceRollResult.get("rollSum");
			final Long rollSumCount = (Long) diceRollResult.get("rollSumCount");
			final float relativeDistribution = (rollSumCount.floatValue() / totalRolls.floatValue()) * 100;

			final Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("rollSum", rollSum);
			resultMap.put("rollSumCount", rollSumCount);
			resultMap.put("relativeDistribution", relativeDistribution + "%");
			resultList.add(resultMap);
		}

		return resultList;
	}

	/**
	 * 
	 * <p>
	 * Utility function for retrieving a random number
	 * </p>
	 * 
	 * @param min minimum value for random number
	 * @param max maximum value for random number
	 * @return a number between min and max param
	 */
	private int getBoundedRandomNumber(int min, int max) {

		Random r = new Random();
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();

	}
}
