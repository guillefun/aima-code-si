package aima.gui.demo.search;

import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensBoard.Config;
import aima.core.environment.nqueens.NQueensFunctions;
import aima.core.environment.nqueens.NQueensGenAlgoUtil;
import aima.core.environment.nqueens.QueenAction;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.GraphSearch4e;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.local.*;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import si.utils.AStarBoard;
import si.utils.GenericConverter;
import si.utils.GenericWriter;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

/**
 * Demonsrates how different search algorithms perform on the NQueens problem.
 * 
 * @author Ruediger Lunde
 * @author Ravi Mohan
 */

public class NQueensDemo {

	private static final int boardSize = 8;

	private static int[] boardSizes = new int[] {4,6,8,10,12,16,24,32}; //4,6,8,10,12,16,24,32

	private static int NUMBER_REPETITIONS = 5;

	public static void main(String[] args) {
		startNQueensDemo();
	}

	private static void startNQueensDemo() {

		// A-STAR SEARCH

		// solveNQueensWithAStarSearch();

		// h0 null heuristic

		// h1 getNumberOfAttackedQueens

		// h2 getNumberOfAttackingPairs

		// h3 getMaximumNumberOfQueensAlignedMinusOne

		// h4 getHeuristicProbabilisticEstimationOfSolution

		// COMPLETE STATE PROBLEM -> h0, h1, h2, h3  // h0,h1,h2,h3
		// INCREMENTAL STATE PROBLEM -> h0,h1, h2,h3,h4 //h0,h2,h3,h4

		int totalRows = 0;
		List<AStarBoard> boardsH0 = new ArrayList<AStarBoard>();

		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchIncrementalH0(i, b);
					boardsH0.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsH0.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsH0, "Incremental H0", totalRows);

		
		List<AStarBoard> boardsH1 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchIncrementalH1(i, b);
					boardsH1.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsH1.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsH1, "Incremental H1", totalRows);

		
		List<AStarBoard> boardsH2 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchIncrementalH2(i, b);
					boardsH2.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsH2.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsH2, "Incremental H2", totalRows);

		
		List<AStarBoard> boardsH3 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchIncrementalH3(i, b);
					boardsH3.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsH3.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsH3, "Incremental H3", totalRows);
		
		
		List<AStarBoard> boardsH4 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchIncrementalH4(i, b);
					boardsH4.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsH4.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsH4, "Incremental H4", totalRows);
		
		
		List<AStarBoard> boardsFirstH0 = new ArrayList<AStarBoard>();

		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteFirstRowH0(i, b);
					boardsFirstH0.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsFirstH0.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsFirstH0, "FirstRow H0", totalRows);

		
		List<AStarBoard> boardsFirstH1 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteFirstRowH1(i, b);
					boardsFirstH1.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsFirstH1.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsFirstH1, "FirstRow H1", totalRows);

		
		List<AStarBoard> boardsFirstH2 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteFirstRowH2(i, b);
					boardsFirstH2.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsFirstH2.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsFirstH2, "FirstRow H2", totalRows);

		
		List<AStarBoard> boardsFirstH3 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteFirstRowH3(i, b);
					boardsFirstH3.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsFirstH3.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsFirstH3, "FirstRow H3", totalRows);
		
		
		List<AStarBoard> boardsEveryColH0 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteEveryColH0(i, b);
					boardsEveryColH0.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsEveryColH0.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsEveryColH0, "EveryCol H0", totalRows);
		
		
		List<AStarBoard> boardsEveryColH1 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteEveryColH1(i, b);
					boardsEveryColH1.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsEveryColH1.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsEveryColH1, "EveryCol H1", totalRows);
		
		
		List<AStarBoard> boardsEveryColH2 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteEveryColH2(i, b);
					boardsEveryColH2.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsEveryColH2.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsEveryColH2, "EveryCol H2", totalRows);
		
		
		List<AStarBoard> boardsEveryColH3 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteEveryColH3(i, b);
					boardsEveryColH3.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsEveryColH3.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsEveryColH3, "EveryCol H3", totalRows);
		
		
		List<AStarBoard> boardsEveryColEveryRowH0 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteEveryColH0(i, b);
					boardsEveryColEveryRowH0.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsEveryColEveryRowH0.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsEveryColEveryRowH0, "EveryCol EveryRow H0", totalRows);
		
		
		List<AStarBoard> boardsEveryColEveryRowH1 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteEveryColH1(i, b);
					boardsEveryColEveryRowH1.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsEveryColEveryRowH1.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsEveryColH1, "EveryCol EveryRow H1", totalRows);
		
		
		List<AStarBoard> boardsEveryColEveryRowH2 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteEveryColH2(i, b);
					boardsEveryColEveryRowH2.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsEveryColEveryRowH2.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsEveryColEveryRowH2, "EveryCol EveryRow H2", totalRows);
		
		
		List<AStarBoard> boardsEveryColEveryRowH3 = new ArrayList<AStarBoard>();
		for (int b : boardSizes) {
			for (int i = 0; i < NUMBER_REPETITIONS; i++) {
				try {
					AStarBoard board = solveNQueensWithAStarSearchCompleteEveryColH3(i, b);
					boardsEveryColEveryRowH3.add(board);
				} catch (OutOfMemoryError e) {
					AStarBoard board = new AStarBoard();
					board.setOom(true);
					boardsEveryColEveryRowH3.add(board);
				}

			}
		}
		totalRows+= GenericWriter.getInstance().writeAStarBoard(boardsEveryColEveryRowH3, "EveryCol Every Row H3", totalRows);
		
		
		
		
		
		
		
		GenericWriter.getInstance().saveSheet();

		// GENETIC ALGORITHMS
		// solveNQueensWithGeneticAlgorithmSearch();

	}

	private static void solveNQueensWithDepthFirstSearch() {
		System.out.println("\n--- NQueensDemo DFS ---");

		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createIncrementalFormulationProblem(boardSize);
		SearchForActions<NQueensBoard, QueenAction> search = new DepthFirstSearch<>(new TreeSearch<>());
		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
	}

	private static void solveNQueensWithBreadthFirstSearch() {
		System.out.println("\n--- NQueensDemo BFS ---");

		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createIncrementalFormulationProblem(boardSize);
		SearchForActions<NQueensBoard, QueenAction> search = new BreadthFirstSearch<>(new GraphSearch<>());
		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
	}

	private static void solveNQueensWithAStarSearch() {
		System.out.println("\n--- NQueensDemo A* (complete state formulation, graph search 3e) ---");

		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(8,
				Config.QUEENS_IN_FIRST_ROW);
//				Config.QUEEN_IN_EVERY_COL);
//				Config.QUEEN_IN_EVERY_COL_AND_EVERY_ROW);

//		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createIncrementalFormulationProblem(8);	

		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::nullHeuristic); // H0
//				(new GraphSearch<>(), NQueensFunctions::getNumberOfAttackingPairs); //H1
//				(new GraphSearch<>(), NQueensFunctions::getProbabilisticEstimationOf); //H3
//				(new GraphSearch<>(), NQueensFunctions::getQueensRemain); //H2 año pasao
//				(new GraphSearch<>(), NQueensFunctions::getMaximumNumberOfQueensAlignedMinusOne); //H2

		System.out.println(problem.getInitialState());

		/*
		 * h1 con queen in first row -> Solución Determinista h1 con queen in every col
		 * -> Solución Estocástica
		 * 
		 * 
		 * HEURISTICOS PARA CADA PROBLEMA:
		 * 
		 * h0, h1, h2, h3, h4 para incremental state problem // h0, h2, h3, h4 -->
		 * seguramente
		 * 
		 * h0, h1 ,h2, h3 para complete state problem // h0, h1 --> seguramente
		 * 
		 */

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		System.out.println(search.getMetrics());
	}

	private static AStarBoard solveNQueensWithAStarSearchIncrementalH0(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createIncrementalFormulationProblem(board_size);

		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::nullHeuristic); // H0
//				(new GraphSearch<>(), NQueensFunctions::getNumberOfAttackingPairs); //H1
//				(new GraphSearch<>(), NQueensFunctions::getProbabilisticEstimationOf); //H3
//				(new GraphSearch<>(), NQueensFunctions::getQueensRemain); //H2 año pasao
//				(new GraphSearch<>(), NQueensFunctions::getMaximumNumberOfQueensAlignedMinusOne); //H2

		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchIncrementalH1(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createIncrementalFormulationProblem(board_size);

		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getNumberOfAttackingPairs); // H1

		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchIncrementalH2(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createIncrementalFormulationProblem(board_size);

		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getNumberOfAttackedQueens); // H2

		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchIncrementalH3(int id, int board_size) {

		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createIncrementalFormulationProblem(board_size);

		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getNumberOfAttackedQueens); // H3

		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchIncrementalH4(int id, int board_size) {

		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createIncrementalFormulationProblem(board_size);

		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getProbabilisticEstimationOf); // H3

		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchCompleteFirstRowH0(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEENS_IN_FIRST_ROW);
//				Config.QUEEN_IN_EVERY_COL);
//				Config.QUEEN_IN_EVERY_COL_AND_EVERY_ROW);

		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::nullHeuristic); // H0
//				(new GraphSearch<>(), NQueensFunctions::getNumberOfAttackingPairs); //H1
//				(new GraphSearch<>(), NQueensFunctions::getProbabilisticEstimationOf); //H3
//				(new GraphSearch<>(), NQueensFunctions::getQueensRemain); //H2 año pasao
//				(new GraphSearch<>(), NQueensFunctions::getMaximumNumberOfQueensAlignedMinusOne); //H2

		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchCompleteFirstRowH1(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEENS_IN_FIRST_ROW);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getNumberOfAttackingPairs); //H1


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchCompleteFirstRowH2(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEENS_IN_FIRST_ROW);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getNumberOfAttackedQueens); //H2


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchCompleteFirstRowH3(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEENS_IN_FIRST_ROW);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getMaximumNumberOfQueensAlignedMinusOne); //H2


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}
	
	private static AStarBoard solveNQueensWithAStarSearchCompleteEveryColH0(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEEN_IN_EVERY_COL);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::nullHeuristic); //H0


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchCompleteEveryColH1(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEEN_IN_EVERY_COL);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getNumberOfAttackingPairs); //H1


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchCompleteEveryColH2(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEEN_IN_EVERY_COL);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getNumberOfAttackedQueens); //H2


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}
	
	private static AStarBoard solveNQueensWithAStarSearchCompleteEveryColH3(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEEN_IN_EVERY_COL);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getMaximumNumberOfQueensAlignedMinusOne); //H3


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchCompleteEveryRowColH0(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEEN_IN_EVERY_COL_AND_EVERY_ROW);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::nullHeuristic); //H0


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;

	}

	private static AStarBoard solveNQueensWithAStarSearchCompleteEveryRowColH1(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEEN_IN_EVERY_COL_AND_EVERY_ROW);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getNumberOfAttackingPairs); //H1


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;
	}

	private static AStarBoard solveNQueensWithAStarSearchCompleteEveryRowColH2(int id,int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEEN_IN_EVERY_COL_AND_EVERY_ROW);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getNumberOfAttackedQueens); //H2


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;
	}
	
	private static AStarBoard solveNQueensWithAStarSearchCompleteEveryRowColH3(int id, int board_size) {
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createCompleteStateFormulationProblem(board_size,
				Config.QUEEN_IN_EVERY_COL_AND_EVERY_ROW);


		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>(new GraphSearch<>(),
				NQueensFunctions::getMaximumNumberOfQueensAlignedMinusOne); //H3


		System.out.println(problem.getInitialState());

		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));

		Hashtable<String, String> values = search.getMetrics().getAll();

		GenericConverter<AStarBoard> converter = new GenericConverter<AStarBoard>();

		AStarBoard board = converter.createBoard(new AStarBoard(), values);

		board.setBoard_size(board_size);
		board.setId(id);

		// System.out.println(search.getMetrics());

		return board;
	}



	private static void solveNQueensWithGeneticAlgorithmSearch() {
		System.out.println("\n--- NQueensDemo GeneticAlgorithm ---");

		final int popSize = 100;
		final double mutationProbability = 0.15;
		final double crossoverProbability = 0.9;
		final int numberOfGenerations = 100;

		FitnessFunction<Integer> fitnessFunction = NQueensGenAlgoUtil.getFitnessFunction();
		Predicate<Individual<Integer>> goalTest = NQueensGenAlgoUtil.getGoalTest();
		// Generate an initial population
		Set<Individual<Integer>> population = new HashSet<>();
		for (int i = 0; i < popSize; i++)
			population.add(NQueensGenAlgoUtil.generateRandomIndividual(boardSize));

		GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<>(boardSize,
				NQueensGenAlgoUtil.getFiniteAlphabetForBoardOfSize(boardSize), mutationProbability,
				crossoverProbability);

		// Run for a set amount of generations
		Individual<Integer> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, numberOfGenerations);
		System.out.println("Number of generations:" + numberOfGenerations + ", Best Individual:\n"
				+ NQueensGenAlgoUtil.getBoardForIndividual(bestIndividual));
		System.out.println("Board Size      = " + boardSize);
		System.out.println("# Board Layouts = " + (new BigDecimal(boardSize)).pow(boardSize));
		System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
		System.out.println("Is Goal         = " + goalTest.test(bestIndividual));
		System.out.println("Population Size = " + ga.getPopulationSize());
		System.out.println("Iterations      = " + ga.getIterations());
		System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

		// Run till goal is achieved
		bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);
		System.out.println("");
		System.out.println(
				"Max time unlimited, Best Individual:\n" + NQueensGenAlgoUtil.getBoardForIndividual(bestIndividual));
		System.out.println("Board Size      = " + boardSize);
		System.out.println("# Board Layouts = " + (new BigDecimal(boardSize)).pow(boardSize));
		System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
		System.out.println("Is Goal         = " + goalTest.test(bestIndividual));
		System.out.println("Population Size = " + ga.getPopulationSize());
		System.out.println("Itertions       = " + ga.getIterations());
		System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
	}

	// Here, this trivial algorithm outperforms the genetic search approach as
	// described in the textbook!
	private static void solveNQueensWithRandomWalk() {
		System.out.println("\n--- NQueensDemo RandomWalk ---");
		NQueensBoard board;
		int i = 0;
		long startTime = System.currentTimeMillis();
		do {
			i++;
			board = new NQueensBoard(boardSize, Config.QUEEN_IN_EVERY_COL);
		} while (board.getNumberOfAttackingPairs() > 0);
		long stopTime = System.currentTimeMillis();
		System.out.println(
				"Solution found after generating " + i + " random configurations (" + (stopTime - startTime) + " ms).");
	}
}
