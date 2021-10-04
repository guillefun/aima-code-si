package aima.gui.demo.search;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.*;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

import java.util.List;
import java.util.Properties;

/**
 * @author Ravi Mohan
 * @author Ruediger Lunde
 * 
 */

public class EightPuzzleDemo {
	private static EightPuzzleBoard boardWithThreeMoveSolution = new EightPuzzleBoard(
			new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8 });

	private static EightPuzzleBoard random1 = new EightPuzzleBoard(new int[] { 1, 4, 2, 7, 5, 8, 3, 0, 6 });

	private static EightPuzzleBoard ex1 = new EightPuzzleBoard(new int[] { 1, 2, 3, 8, 0, 4, 7, 6, 5 });

	private static EightPuzzleBoard exTest = new EightPuzzleBoard(new int[] { 8, 1, 0, 2, 5, 3, 7, 4, 6 });

	private static EightPuzzleBoard[] modelStates = EightPuzzleSamples.getPuzzleBoards();

	public static EightPuzzleBoard goal = new EightPuzzleBoard(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });

//	private static EightPuzzleBoard extreme =
//			new EightPuzzleBoard(new int[] { 0, 8, 7, 6, 5, 4, 3, 2, 1 });

	public static void main(String[] args) {
		System.out.println(
				"___________.__    ____    __   .__         __________                        .__            \n"
						+ "\\_   _____/|__|  / ___\\ _/  |_ |  |__      \\______   \\ __ __ ________________|  |    ____   \n"
						+ " |    __)_ |  | / /_/  >\\   __\\|  |  \\      |     ___/|  |  \\\\___   /\\___   /|  |  _/ __ \\  \n"
						+ " |        \\|  | \\___  /  |  |  |   Y  \\     |    |    |  |  / /    /  /    / |  |__\\  ___/  \n"
						+ "/_______  /|__|/_____/   |__|  |___|  /     |____|    |____/ /_____ \\/_____ \\|____/ \\___  > \n"
						+ "        \\/                          \\/                             \\/      \\/           \\/  ");
		eightPuzzleAStarNullHeuristicDemo();
//		eightPuzzleAStarWeightedMisplacedDemo();
//		eightPuzzleAStarWeightedManhattanDemo();
//		eightPuzzleAStarWeightedNonConsistentHeuristicDemo();
//		eightPuzzleAStarEpsilonWeightedManhattanDemo();
		
		//eightPuzzleAStarDemo();
	
	}

	
	//HEURISTIC H(OBJETIVO) = 0 G(INICIAL)=0 F(OBJETIVO)=C*
	//H MONOTONO, AL EXPANDIR N ---> G(N) = G*(N) ---+ F*<=C*
	//C* = F*(INICIAL)=H*(INICIAL)
	
	
	private static void eightPuzzleAStarEpsilonWeightedManhattanDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
		System.out.println("Initial State:\n" + exTest);
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(modelStates[2]);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeigthedManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Goal State:\n" + goal);

	}

	private static void eightPuzzleAStarWeightedNonConsistentHeuristicDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
		System.out.println("Initial State:\n" + exTest);
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(modelStates[2]);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeigthedManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Goal State:\n" + goal);

	}

	private static void eightPuzzleAStarWeightedManhattanDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
		System.out.println("Initial State:\n" + exTest);
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(modelStates[2]);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeigthedManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Goal State:\n" + goal);

	}

	private static void eightPuzzleAStarWeightedMisplacedDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
		System.out.println("Initial State:\n" + exTest);
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(modelStates[2]);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeigthedNumberOfMisplacedTiles);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Goal State:\n" + goal);

	}

	private static void eightPuzzleAStarNullHeuristicDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
		System.out.println("Initial State:\n" + exTest);
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(modelStates[2]);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getNullHeuristic);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Goal State:\n" + goal);

	}

	private static void eightPuzzleAStarDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
		System.out.println("Initial State:\n" + exTest);
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(modelStates[2]);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeigthedManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Goal State:\n" + goal);
	}

	private static void eightPuzzleDLSDemo() {
		System.out.println("\nEightPuzzleDemo recursive DLS (9)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new DepthLimitedSearch<>(9);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleIDLSDemo() {
		System.out.println("\nEightPuzzleDemo Iterative DLS");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new IterativeDeepeningSearch<>();
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleGreedyBestFirstDemo() {
		System.out.println("\nEightPuzzleDemo Greedy Best First Search (MisplacedTileHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getNumberOfMisplacedTiles);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleGreedyBestFirstManhattanDemo() {
		System.out.println("\nEightPuzzleDemo Greedy Best First Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nEightPuzzleDemo Simulated Annealing Search");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SimulatedAnnealingSearch<EightPuzzleBoard, Action> search = new SimulatedAnnealingSearch<>(
					EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			System.out.println("Final State:\n" + search.getLastState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarManhattanDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printInstrumentation(Properties properties) {
		properties.keySet().stream().map(key -> key + "=" + properties.get(key)).forEach(System.out::println);
	}

	private static void printActions(List<Action> actions) {
		actions.forEach(System.out::println);
		System.out.println("- Number of action: " + actions.size() + " -");

	}
}