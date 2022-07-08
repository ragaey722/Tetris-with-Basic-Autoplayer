package tetris.game.pieces;

public class Point {

	/**
	 * The row offset
	 */
	private final int row;

	/**
	 * The column offset
	 */
	private final int column;

	public Point(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * @return The row offset
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return The column offset
	 */
	public int getColumn() {
		return column;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (row != other.row)
			return false;
		if (column != other.column)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return row * 31 + column;
	}

	@Override
	public Point clone() {
		return new Point(row, column);
	}
}
