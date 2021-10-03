package aima.core.environment.eightpuzzle;

import aima.core.agent.Action;
import aima.core.search.framework.Node;
import aima.core.util.datastructure.XYLocation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Useful functions for solving EightPuzzle problems.
 * 
 * @author Ruediger Lunde
 */
public class EightPuzzleFunctions {

	public static final EightPuzzleBoard GOAL_STATE = new EightPuzzleBoard(new int[] { 1, 2, 3, 8, 0, 4, 7, 6, 5 });

	public static List<Action> getActions(EightPuzzleBoard state) {
		return Stream.of(EightPuzzleBoard.UP, EightPuzzleBoard.DOWN, EightPuzzleBoard.LEFT, EightPuzzleBoard.RIGHT)
				.filter(state::canMoveGap).collect(Collectors.toList());
	}

	public static EightPuzzleBoard getResult(EightPuzzleBoard state, Action action) {
		EightPuzzleBoard result = state.clone();

		if (result.canMoveGap(action)) {
			if (action == EightPuzzleBoard.UP)
				result.moveGapUp();
			else if (action == EightPuzzleBoard.DOWN)
				result.moveGapDown();
			else if (action == EightPuzzleBoard.LEFT)
				result.moveGapLeft();
			else if (action == EightPuzzleBoard.RIGHT)
				result.moveGapRight();
		}
		return result;
	}

	// h2
	public static double getManhattanDistance(Node<EightPuzzleBoard, Action> node) {
		EightPuzzleBoard currState = node.getState();
		int result = 0;
		for (int val = 1; val <= 8; val++) {
			XYLocation locCurr = currState.getLocationOf(val);
			XYLocation locGoal = GOAL_STATE.getLocationOf(val);
			result += Math.abs(locGoal.getX() - locCurr.getX());
			result += Math.abs(locGoal.getY() - locCurr.getY());
		}
		return result;
	}

	public static int getNumberOfMisplacedTiles(Node<EightPuzzleBoard, Action> node) {
		EightPuzzleBoard currState = node.getState();
		int result = 0;
		for (int val = 1; val <= 8; val++)
			if (!(currState.getLocationOf(val).equals(GOAL_STATE.getLocationOf(val))))
				result++;
		return result;
	}

	// h2 ejercicio 7
	public static double getWeigthedManhattanDistance(Node<EightPuzzleBoard, Action> node) {

		EightPuzzleBoard currState = node.getState();
		int result = 0;
		for (int val = 1; val <= 8; val++) {

			XYLocation locCurr = currState.getLocationOf(val);
			XYLocation locGoal = GOAL_STATE.getLocationOf(val);

			result += (Math.abs(locGoal.getX() - locCurr.getX())) * Math.pow(2, val);
			result += (Math.abs(locGoal.getY() - locCurr.getY())) * Math.pow(2, val);

		}
		return result;

	}

	// h1 ejercicio 7
	public static int getWeigthedNumberOfMisplacedTiles(Node<EightPuzzleBoard, Action> node) {

		EightPuzzleBoard currState = node.getState();
		int result = 0;

		for (int val = 1; val <= 8; val++)
			if (!(currState.getLocationOf(val).equals(GOAL_STATE.getLocationOf(val)))) {
				result += Math.pow(2, val);
			}

		return result;

	}

	public static double getNullHeuristic(Node<EightPuzzleBoard, Action> node) {
		return 0;
	}

	public static double getNonWeightedConsistentHeuristic(Node<EightPuzzleBoard, Action> node) {
			
		EightPuzzleBoard currState = node.getState();
		int result = 0;
		for (int val = 1; val <= 8; val++) {

			XYLocation locCurr = currState.getLocationOf(val);
			XYLocation locGoal = GOAL_STATE.getLocationOf(val);
			
		
			result += (Math.abs(locGoal.getX() - locCurr.getX())) ;
			result += (Math.abs(locGoal.getY() - locCurr.getY()));
			
			if(result==2) result+=  result* Math.pow(2, val);

		}
		return 2*result;
	}
	
	public static double getEpsilonWeightedHeuristic(Node<EightPuzzleBoard, Action> node) {
		
		return getWeigthedManhattanDistance(node)*(1+Double.MIN_VALUE);
	}

	public static double stepCostFunction(EightPuzzleBoard state, Action action, EightPuzzleBoard sucState) {

		int val = 0;
		int i = 0;
		while (i < 8 && sucState.getState()[i] != 0)
			i++;
		val = state.getState()[i];
		return (long) Math.pow(2, val);

	}

	// double cost = 0;
	/*
	 * XYLocation location = state.getLocationOf(0);
	 * 
	 * if (action == EightPuzzleBoard.UP) cost = state.getValueAt(new
	 * XYLocation(location.getX(), location.getY() - 1));
	 * 
	 * else if (action == EightPuzzleBoard.DOWN) cost = state.getValueAt(new
	 * XYLocation(location.getX(), location.getY() + 1));
	 * 
	 * else if (action == EightPuzzleBoard.LEFT) cost = state.getValueAt(new
	 * XYLocation(location.getX() - 1, location.getY()));
	 * 
	 * else if (action == EightPuzzleBoard.RIGHT) cost = state.getValueAt(new
	 * XYLocation(location.getX() + 1, location.getY()));
	 * 
	 * return Math.pow(2, cost);
	 */
}