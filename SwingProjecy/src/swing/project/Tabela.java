package swing.project;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class Tabela extends AbstractTableModel {
	private Object[][] data;
	private int column;


	public Tabela(Object[][] data, int column) throws Exception {
		try {

		} catch (IndexOutOfBoundsException e) {
			throw new Exception("Nieprawid³owa iloœæ kolumn");
		} catch (NullPointerException e) {
			throw new Exception("Brak danych");
		}
		this.data = data;
		this.column = column;
	}

	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return column;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	public void dodajKolumne(Object[] row) throws Exception {
		if (row.length != getColumnCount())
			throw new Exception("Niew³aœciwe dane");
		Object[][] newdata = new Object[data.length + 1][column];
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < column; j++)
				newdata[i][j] = data[i][j];
		for (int i = 0; i < column; i++)
			newdata[data.length][i] = row[i];
		this.data = newdata;
		fireTableDataChanged();
	}

	public void usunKolumne(int row) throws IndexOutOfBoundsException {
		if (data.length == 0)
			return;
		Object[][] newdata = new Object[data.length - 1][column];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++)
				newdata[i][j] = data[i][j];

		for (int i = row + 1; i < data.length; i++)
			for (int j = 0; j < column; j++)
				newdata[i - 1][j] = data[i][j];
		this.data = newdata;
		fireTableDataChanged();
	}
}
