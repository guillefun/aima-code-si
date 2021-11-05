package aima.core.environment.nqueens;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;
import aima.core.util.datastructure.XYLocation;

/**
 * A class whose purpose is to provide static utility methods for solving the
 * n-queens problem with genetic algorithms. This includes fitness function,
 * goal test, random creation of individuals and convenience methods for
 * translating between between an NQueensBoard representation and the Integer list
 * representation used by the GeneticAlgorithm.
 * 
 * @author Ciaran O'Reilly
 * @author Ruediger Lunde
 * 
 */
public class NQueensGenAlgoUtil {

	public static FitnessFunction<Integer> getFitnessFunction() {
//		return new NQueensFitnessFunction();
		return new NQueensFitnessQueenFunction();
	}
	
	public static Predicate<Individual<Integer>> getGoalTest() {
		return new NQueensGenAlgoGoalTest();
	}
	

	public static Individual<Integer> generateRandomIndividual(int boardSize) {
		List<Integer> individualRepresentation = new ArrayList<>();
		for (int i = 0; i < boardSize; i++) {
			individualRepresentation.add(new Random().nextInt(boardSize));
			Collections.shuffle(individualRepresentation);
		}
		return new Individual<>(individualRepresentation);
	}

	public static Collection<Integer> getFiniteAlphabetForBoardOfSize(int size) {
		Collection<Integer> fab = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			fab.add(i);
		}

		return fab;
	}
	
	public static class NQueensFitnessFunction implements FitnessFunction<Integer> {

		public double apply(Individual<Integer> individual) {
			double fitness = 0;

			NQueensBoard board = getBoardForIndividual(individual);
			int boardSize = board.getSize();

			// Calculate the number of non-attacking pairs of queens (refer to
			// AIMA
			// page 117).
			List<XYLocation> qPositions = board.getQueenPositions();
			for (int fromX = 0; fromX < (boardSize - 1); fromX++) {
				for (int toX = fromX + 1; toX < boardSize; toX++) {
					int fromY = qPositions.get(fromX).getY();
					boolean nonAttackingPair = true;
					// Check right beside
					int toY = fromY;
					if (board.queenExistsAt(new XYLocation(toX, toY))) {
						nonAttackingPair = false;
					}
					// Check right and above
					toY = fromY - (toX - fromX);
					if (toY >= 0) {
						if (board.queenExistsAt(new XYLocation(toX, toY))) {
							nonAttackingPair = false;
						}
					}
					// Check right and below
					toY = fromY + (toX - fromX);
					if (toY < boardSize) {
						if (board.queenExistsAt(new XYLocation(toX, toY))) {
							nonAttackingPair = false;
						}
					}

					if (nonAttackingPair) {
						fitness += 1.0;
					}
				}
			}

			return fitness;
		}
	}

	// EJERCICIO 8 SESSION 4
	public static class NQueensFitnessQueenFunction implements FitnessFunction<Integer> {

		public double apply(Individual<Integer> individual) {
			double fitness = 0;

			NQueensBoard board = getBoardForIndividual(individual);
			int boardSize = board.getSize();

			List<XYLocation> qPositions = board.getQueenPositions();
			
			for (XYLocation l : qPositions) {
				int x = l.getX();
				int y = l.getY();
				
				int horizontal = 0;
				for (int col = 0; col < boardSize; col++) {
					if ((board.queenExistsAt(new XYLocation(col, y))))
						if (col != x)
							horizontal++;
				}
				
				int vertical = 0;
				for (int row = 0; row < boardSize; row++) {
					if ((board.queenExistsAt(new XYLocation(x, row))))
						if (row != y)
							vertical++;
				}
				
				int diagonal = 0;
				int col;
				int row;
				// forward up diagonal
				for (col = (x + 1), row = (y - 1); (col < boardSize && (row > -1)); col++, row--)
					if ((board.queenExistsAt(new XYLocation(col, row))))
						diagonal++;

				// forward down diagonal
				for (col = (x + 1), row = (y + 1); ((col < boardSize) && (row < boardSize)); col++, row++)
					if ((board.queenExistsAt(new XYLocation(col, row))))
						diagonal++;

				// backward up diagonal
				for (col = (x - 1), row = (y - 1); ((col > -1) && (row > -1)); col--, row--)
					if ((board.queenExistsAt(new XYLocation(col, row))))
						diagonal++;

				// backward down diagonal
				for (col = (x - 1), row = (y + 1); ((col > -1) && (row < boardSize)); col--, row++)
					if ((board.queenExistsAt(new XYLocation(col, row))))
						diagonal++;
				
				if((vertical+horizontal+diagonal)==0) {
					fitness += 1.0;
				}
			}

			return fitness;
		}
		
	
	}
	
	public static class NQueensGenAlgoGoalTest implements Predicate<Individual<Integer>> {
		private final Predicate<NQueensBoard> goalTest = NQueensFunctions::testGoal;

		@Override
		public boolean test(Individual<Integer> state) {
			return goalTest.test(getBoardForIndividual(state));
		}
	}

	public static NQueensBoard getBoardForIndividual(Individual<Integer> individual) {
		int boardSize = individual.length();
		NQueensBoard board = new NQueensBoard(boardSize);
		for (int i = 0; i < boardSize; i++) {
			int pos = individual.getRepresentation().get(i);
			board.addQueenAt(new XYLocation(i, pos));
		}
		return board;
	}
}