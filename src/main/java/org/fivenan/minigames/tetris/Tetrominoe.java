package org.fivenan.minigames.tetris;

import java.awt.Color;

public enum Tetrominoe {
	NO_SHAPE(0, 0, 0), //
	Z_SHAPE(204, 102, 102), //
	S_SHAPE(102, 204, 102), //
	LINE_SHAPE(102, 102, 204), //
	T_SHAPE(204, 204, 102), //
	SQUARE_SHAPE(204, 102, 204), //
	L_SHAPE(102, 204, 204), //
	MIRRORED_L_SHAPE(218, 170, 0);

	private int red;
	private int green;
	private int blue;

	private Tetrominoe(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color getColor() {
		return new Color(red, green, blue);
	}
}
