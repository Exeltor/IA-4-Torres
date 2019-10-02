package aima.test.core.unit.environment.nqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import aima.core.environment.nqueens.FourTowersFunctions;
import aima.core.environment.nqueens.QueenAction;
import aima.core.util.datastructure.XYLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import aima.core.environment.nqueens.FourTowersBoard;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * @author Ruediger Lunde
 */
public class NQueensFunctionsTest {
	private Function<FourTowersBoard, List<QueenAction>> actionsFn;
	private BiFunction<FourTowersBoard, QueenAction, FourTowersBoard> resultFn;
	private Predicate<FourTowersBoard> goalTest;

	private FourTowersBoard oneBoard;
	private FourTowersBoard eightBoard;
	private FourTowersBoard board;


	@Before
	public void setUp() {
		oneBoard = new FourTowersBoard(1);
		eightBoard = new FourTowersBoard(8);
		board = new FourTowersBoard(8);

		actionsFn = FourTowersFunctions::getIFActions;
		resultFn = FourTowersFunctions::getResult;
		goalTest = FourTowersFunctions::testGoal;
	}

	@Test
	public void testSimpleBoardSuccessorGenerator() {
		List<QueenAction> actions = new ArrayList<>(actionsFn.apply(oneBoard));
		Assert.assertEquals(1, actions.size());
		FourTowersBoard next = resultFn.apply(oneBoard, actions.get(0));
		Assert.assertEquals(1, next.getNumberOfQueensOnBoard());
	}

	@Test
	public void testComplexBoardSuccessorGenerator() {
		List<QueenAction> actions = new ArrayList<>(actionsFn.apply(eightBoard));
		Assert.assertEquals(8, actions.size());
		FourTowersBoard next = resultFn.apply(eightBoard, actions.get(0));
		Assert.assertEquals(1, next.getNumberOfQueensOnBoard());

		actions = new ArrayList<>(actionsFn.apply(next));
		Assert.assertEquals(6, actions.size());
	}


	@Test
	public void testEmptyBoard() {
		Assert.assertFalse(goalTest.test(board));
	}

	@Test
	public void testSingleSquareBoard() {
		board = new FourTowersBoard(1);
		Assert.assertFalse(goalTest.test(board));
		board.addTowerAt(new XYLocation(0, 0));
		Assert.assertTrue(goalTest.test(board));
	}

	@Test
	public void testInCorrectPlacement() {
		Assert.assertFalse(goalTest.test(board));
		// This is the configuration of Figure 3.5 (b) in AIMA 2nd Edition
		board.addTowerAt(new XYLocation(0, 0));
		board.addTowerAt(new XYLocation(1, 2));
		board.addTowerAt(new XYLocation(2, 4));
		board.addTowerAt(new XYLocation(3, 6));
		board.addTowerAt(new XYLocation(4, 1));
		board.addTowerAt(new XYLocation(5, 3));
		board.addTowerAt(new XYLocation(6, 5));
		board.addTowerAt(new XYLocation(7, 7));
		Assert.assertFalse(goalTest.test(board));
	}

	@Test
	public void testCorrectPlacement() {

		Assert.assertFalse(goalTest.test(board));
		// This is the configuration of Figure 5.9 (c) in AIMA 2nd Edition
		board.addTowerAt(new XYLocation(0, 1));
		board.addTowerAt(new XYLocation(1, 4));
		board.addTowerAt(new XYLocation(2, 6));
		board.addTowerAt(new XYLocation(3, 3));
		board.addTowerAt(new XYLocation(4, 0));
		board.addTowerAt(new XYLocation(5, 7));
		board.addTowerAt(new XYLocation(6, 5));
		board.addTowerAt(new XYLocation(7, 2));

		Assert.assertTrue(goalTest.test(board));
	}
}
