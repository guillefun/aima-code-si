package aima.gui.demo.search;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;

public class EightPuzzleSamples {

	public static EightPuzzleBoard[] getPuzzleBoards() {

		EightPuzzleBoard m1 = new EightPuzzleBoard(new int[] { 1, 0, 3, 8, 2, 5, 7, 4, 6 });
		EightPuzzleBoard m2 = new EightPuzzleBoard(new int[] { 8, 2, 1, 7, 0, 3, 6, 5, 4 });
		EightPuzzleBoard m3 = new EightPuzzleBoard(new int[] { 4, 8, 2, 6, 3, 5, 1, 0, 7 });
		EightPuzzleBoard m4 = new EightPuzzleBoard(new int[] { 6, 2, 7, 4, 5, 1, 0, 8, 3 });
		EightPuzzleBoard m5 = new EightPuzzleBoard(new int[] { 6, 7, 4, 0, 5, 1, 3, 2, 8 });
		EightPuzzleBoard m6 = new EightPuzzleBoard(new int[] { 5, 6, 7, 2, 8, 4, 0, 3, 1 });

		EightPuzzleBoard[] initialStates = new EightPuzzleBoard[] { m1, m2, m3, m4, m5, m6 };

		return initialStates;

	}

}
