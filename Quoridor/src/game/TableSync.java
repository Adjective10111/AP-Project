package game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TableSync {
	private IntegerProperty[][] table;

	public TableSync(int a, int b, int value) {
		this.table = new IntegerProperty[a][b];
		for (int i = 0; i < this.table.length; i++)
			for (int j = 0; j < this.table[i].length; j++)
				table[i][j] = new SimpleIntegerProperty(value);
	}
	public TableSync(int[][] table) {
		this.table = new IntegerProperty[table.length][table[0].length];
		for (int i = 0; i < this.table.length; i++)
			for (int j = 0; j < this.table[i].length; j++)
				this.table[i][j] = new SimpleIntegerProperty(table[i][j]);
	}
	public TableSync(int a, int b) { this(a, b, 0); }

	public void setTable(IntegerProperty[][] table) {
		this.table = table;
	}

	public void set(int[][] table) {
		for (int i = 0; i < this.table.length; i++)
			for (int j = 0; j < this.table[i].length; j++)
				this.table[i][j].set(table[i][j]);
	}
}
