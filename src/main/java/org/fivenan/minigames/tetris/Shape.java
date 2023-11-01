package org.fivenan.minigames.tetris;

import java.util.Random;

public class Shape {

	protected enum Tetrominoe {
		NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape;
	}

	private Tetrominoe pieceShape;
	private int coords[][];
	private int[][][] coordsTable;

	public Shape() {
		initShape();
	}

	private void initShape() {
		coords = new int[4][2];
		coordsTable = new int[][][] { { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },
				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } },
				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } },
				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } },
				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } } };
		setShape(Tetrominoe.NoShape);

	}

	void setShape(Tetrominoe shape) {
		int[][] shapeCoords = coordsTable[shape.ordinal()];

		for (int i = 0; i < 4; i++) {
			System.arraycopy(shapeCoords[i], 0, coords[i], 0, 2);
		}
	}

	public void setRandomShape() {
		Random random = new Random();
		int randomIndex = random.nextInt(Tetrominoe.values().length);

		Tetrominoe randomShape = Tetrominoe.values()[randomIndex];
		setShape(randomShape);
	}

	private void setX(int index, int x) {
		coords[index][0] = x;
	}

	private void setY(int index, int y) {
		coords[index][1] = y;
	}

	public int x(int index) {
		return coords[index][0];
	}

	public int y(int index) {
		return coords[index][1];
	}

	public Tetrominoe getShape() {
		return pieceShape;
	}

	public int minX() {

		int m = coords[0][0];

		for (int i = 0; i < 4; i++) {

			m = Math.min(m, coords[i][0]);
		}

		return m;
	}

	public int minY() {

		int m = coords[0][1];

		for (int i = 0; i < 4; i++) {

			m = Math.min(m, coords[i][1]);
		}

		return m;
	}

	public Shape rotateLeft() {

		if (pieceShape == Tetrominoe.SquareShape) {
			return this;
		}

		var result = new Shape();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {

			result.setX(i, y(i));
			result.setY(i, -x(i));
		}

		return result;

	}

	public Shape rotateRight() {

		if (pieceShape == Tetrominoe.SquareShape) {

			return this;
		}

		var result = new Shape();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {

			result.setX(i, -y(i));
			result.setY(i, x(i));
		}

		return result;
	}
}
