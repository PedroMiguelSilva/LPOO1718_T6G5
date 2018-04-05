package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.cli.Start;
import dkeep.logic.CustomLevel;
import dkeep.logic.Symbol;

public class TestCustomGame {

	@Test
	public void TestSizeOfDefaultConstructor() {
		CustomLevel lvl = new CustomLevel();
		assertEquals(7,lvl.getMap().length);
	}

	@Test
	public void TestCreationOfWalls() {
		CustomLevel lvl = new CustomLevel();
		assertEquals(Symbol.WALL,lvl.getMap()[0][0]);
		Start.printMap(lvl.getMap());
	}
}
