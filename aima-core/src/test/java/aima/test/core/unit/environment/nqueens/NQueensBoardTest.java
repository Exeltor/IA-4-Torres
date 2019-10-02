package aima.test.core.unit.environment.nqueens;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import aima.core.environment.nqueens.FourTowersBoard;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Ravi Mohan
 * 
 */
public class NQueensBoardTest {

	private FourTowersBoard board;

	@Before
	public void setUp() {

		board = new FourTowersBoard(8);
	}

	@Test
	public void testBasics() {
		Assert.assertEquals(0, board.getNumberOfQueensOnBoard());
		board.addTowerAt(new XYLocation(0, 0));
		Assert.assertEquals(1, board.getNumberOfQueensOnBoard());
		board.addTowerAt(new XYLocation(0, 0));
		Assert.assertEquals(1, board.getNumberOfQueensOnBoard());
		board.addTowerAt(new XYLocation(1, 1));
		Assert.assertEquals(2, board.getNumberOfQueensOnBoard());
		Assert.assertTrue(board.towerExistsAt(new XYLocation(1, 1)));
		Assert.assertTrue(board.towerExistsAt(new XYLocation(0, 0)));
		board.moveQueen(new XYLocation(1, 1), new XYLocation(3, 3));
		Assert.assertTrue(board.towerExistsAt(new XYLocation(3, 3)));
		Assert.assertTrue(!(board.towerExistsAt(new XYLocation(1, 1))));
		Assert.assertEquals(2, board.getNumberOfQueensOnBoard());
	}

	@Test
	public void testCornerQueenAttack1() {

		board.addTowerAt(new XYLocation(0, 0));
		Assert.assertFalse(board.isSquareUnderAttack(new XYLocation(0, 0)));
		// queen on square not included
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(1, 0)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(7, 0)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(0, 7)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(1, 1)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(2, 2)));
		Assert.assertFalse(board.isSquareUnderAttack(new XYLocation(2, 1)));
		Assert.assertFalse(board.isSquareUnderAttack(new XYLocation(1, 2)));
	}

	@Test
	public void testCornerQueenAttack2() {

		board.addTowerAt(new XYLocation(7, 7));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(0, 0)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(7, 0)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(0, 7)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(7, 0)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(6, 6)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(5, 5)));
		Assert.assertFalse(board.isSquareUnderAttack(new XYLocation(6, 5)));
		Assert.assertFalse(board.isSquareUnderAttack(new XYLocation(5, 6)));
	}

	@Test
	public void testEdgeQueenAttack() {

		board.addTowerAt(new XYLocation(0, 3));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(0, 0)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(0, 7)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(7, 3)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(3, 0)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(4, 7)));
	}

	@Test
	public void testAttack2() {

		board.addTowerAt(new XYLocation(7, 0));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(6, 1)));
	}

	@Test
	public void testAttack3() {

		board.addTowerAt(new XYLocation(0, 0));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(0, 1)));
	}

	@Test
	public void testAttack4() {

		board.addTowerAt(new XYLocation(0, 2));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(1, 1)));
	}

	@Test
	public void testMidBoardDiagonalAttack() {

		board.addTowerAt(new XYLocation(3, 3));
		// forwardDiagonal from the queen
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(4, 2)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(4, 4)));
		// backwardDiagonal from the queen
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(2, 2)));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(2, 4)));
	}

	@Test
	public void testCornerDiagonalAttack() {

		board.addTowerAt(new XYLocation(0, 0));
		// forwardDiagonal from the queen
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(1, 1)));
		board.clear();

		board.addTowerAt(new XYLocation(7, 7));
		// backwardDiagonal from the queen
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(6, 6)));

		// assertTrue(board.isSquareUnderAttack(new XYLocation(2, 2)));
		// assertTrue(board.isSquareUnderAttack(new XYLocation(2, 4)));
	}

	@Test
	public void testAttack6() {

		board.addTowerAt(new XYLocation(1, 6));
		Assert.assertTrue(board.isSquareUnderAttack(new XYLocation(0, 7)));
	}

	@Test
	public void testRemoveQueen() {

		board.addTowerAt(new XYLocation(0, 0));
		Assert.assertEquals(1, board.getNumberOfQueensOnBoard());
		board.removeQueenFrom(new XYLocation(0, 0));
		Assert.assertEquals(0, board.getNumberOfQueensOnBoard());
	}

	@Test
	public void testMoveQueen() {

		XYLocation from = new XYLocation(0, 0);
		XYLocation to = new XYLocation(1, 1);

		board.addTowerAt(from);
		Assert.assertEquals(1, board.getNumberOfQueensOnBoard());
		Assert.assertTrue(board.towerExistsAt(from));
		Assert.assertFalse(board.towerExistsAt(to));

		board.moveQueen(from, to);
		Assert.assertEquals(1, board.getNumberOfQueensOnBoard());
		Assert.assertFalse(board.towerExistsAt(from));
		Assert.assertTrue(board.towerExistsAt(to));
	}

	@Test
	public void testMoveNonExistentQueen() {

		XYLocation from = new XYLocation(0, 0);
		XYLocation to = new XYLocation(1, 1);
		board.moveQueen(from, to);

		Assert.assertEquals(0, board.getNumberOfQueensOnBoard());
	}

	@Test
	public void testRemoveNonExistentQueen() {
		board.removeQueenFrom(new XYLocation(0, 0));
		Assert.assertEquals(0, board.getNumberOfQueensOnBoard());
	}

	@Test
	public void testEquality() {

		board.addTowerAt(new XYLocation(0, 0));
		FourTowersBoard board2 = new FourTowersBoard(8);
		board2.addTowerAt(new XYLocation(0, 0));
		Assert.assertEquals(board, board2);
		FourTowersBoard board3 = new FourTowersBoard(8);
		board3.addTowerAt(new XYLocation(0, 1));
		Assert.assertNotEquals(board, board3);
	}

	@Test
	public void testDontPlaceTwoQueensOnOneSquare() {

		board.addTowerAt(new XYLocation(0, 0));
		board.addTowerAt(new XYLocation(0, 0));
		Assert.assertEquals(1, board.getNumberOfQueensOnBoard());
	}

	@Test
	public void testSimpleHorizontalAttack() {
		XYLocation loc = new XYLocation(0, 0);
		board.addTowerAt(loc);
		Assert.assertEquals(0, board.getNumberOfAttacksOn(loc));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(new XYLocation(1, 0)));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(loc.right()));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(new XYLocation(7, 0)));
	}

	@Test
	public void testSimpleVerticalAttack() {
		XYLocation loc = new XYLocation(0, 0);
		board.addTowerAt(loc);
		Assert.assertEquals(0, board.getNumberOfAttacksOn(loc));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(loc.down()));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(new XYLocation(0, 7)));
	}

	@Test
	public void testSimpleDiagonalAttack() {
		XYLocation loc = new XYLocation(3, 3);
		board.addTowerAt(loc);
		Assert.assertEquals(0, board.getNumberOfAttacksOn(loc));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(loc.down().right()));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(loc.down().left()));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(loc.up().left()));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(loc.up().right()));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(new XYLocation(7, 7)));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(new XYLocation(0, 0)));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(new XYLocation(6, 0)));
		Assert.assertEquals(1, board.getNumberOfAttacksOn(new XYLocation(0, 6)));
	}

	@Test
	public void testMultipleQueens() {
		XYLocation loc1 = new XYLocation(3, 3);
		board.addTowerAt(loc1);
		Assert.assertEquals(1, board.getNumberOfAttacksOn(loc1.right()));

		board.addTowerAt(loc1.right().right());
		Assert.assertEquals(1, board.getNumberOfAttacksOn(loc1));
		Assert.assertEquals(2, board.getNumberOfAttacksOn(loc1.right()));

		board.addTowerAt(loc1.right().down());
		Assert.assertEquals(2, board.getNumberOfAttacksOn(loc1));
		Assert.assertEquals(3, board.getNumberOfAttacksOn(loc1.right()));
		Assert.assertEquals(2, board.getNumberOfAttacksOn(loc1.right().right()));
	}

	@Test
	public void testBoardDisplay() {
		board.addTowerAt(new XYLocation(0, 5));
		board.addTowerAt(new XYLocation(1, 6));
		board.addTowerAt(new XYLocation(2, 1));
		board.addTowerAt(new XYLocation(3, 3));
		board.addTowerAt(new XYLocation(4, 6));
		board.addTowerAt(new XYLocation(5, 4));
		board.addTowerAt(new XYLocation(6, 7));
		board.addTowerAt(new XYLocation(7, 7));
		Assert.assertEquals("--------\n" + "--Q-----\n" + "--------\n"
				+ "---Q----\n" + "-----Q--\n" + "Q-------\n" + "-Q--Q---\n"
				+ "------QQ\n", board.toString());
	}
}
