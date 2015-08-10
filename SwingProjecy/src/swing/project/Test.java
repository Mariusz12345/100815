package swing.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Test extends JFrame implements ActionListener {

	private static final long serialVersionUID = -646317234260742582L;
	private JTable table;
	private JTextField rowdata;
	private JButton dodaj;
	private JButton usun;
	private JButton zmien;

	@SuppressWarnings("deprecation")
	public Test() {
		setSize(600, 500);
		setResizable(false);
		setTitle("Klienci");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			initComponents();
			addNPlaceComponents();
		} catch (Exception e) {
			System.out.println("Blad ladowania komponentów:\n" + e.getMessage());
			System.exit(-1);
		}
		this.show();
	}

	private void initComponents() throws Exception {
		Object[][] example_data = {  {"Klient1"} ,  {"Klient2"} ,  {"Klient3"}  };
	
		table = new JTable(new Tabela(example_data, 1));

		rowdata = new JTextField("Klient");
		
		dodaj = new JButton("Dodaj");
		dodaj.setActionCommand("dodaj");
		dodaj.addActionListener(this);

		usun = new JButton("Usuñ");
		usun.setActionCommand("usun");
		usun.addActionListener(this);
		
		zmien = new JButton("Zmien");
		zmien.setActionCommand("change");
		zmien.addActionListener(this);
	}
	private void addNPlaceComponents() {
		JScrollPane sp = new JScrollPane(table);
		JLabel rowdata_lab = new JLabel("Dodaj klienta:");
		setLayout(null);

		add(sp);
		add(rowdata);
		add(dodaj);
		add(usun);
		add(zmien);
		add(rowdata_lab);

		sp.setBounds(5, 5, 380, 200);
		rowdata_lab.setBounds(5, 210, rowdata_lab.getPreferredSize().width, 20);
		rowdata.setBounds(10 + rowdata_lab.getPreferredSize().width, 210, 100, 20);

		dodaj.setBounds(5, 235, dodaj.getPreferredSize().width, 25);
		usun.setBounds(10 + dodaj.getPreferredSize().width, 235, usun.getPreferredSize().width, 25);
		zmien.setBounds(145, 235,100, 25);
	}

	public void actionPerformed(ActionEvent e) {
		try{
			if(e.getActionCommand().equalsIgnoreCase("change"));
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(this, "Nie mozna zmienic danych"+ex.getMessage());
		}
		
		
		// dodawanie
		if (e.getActionCommand().equalsIgnoreCase("dodaj")) {
			if (rowdata.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Wpisz jak¹œ dan¹ w polu 'wiersz'", "Brak danych",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {
				Tabela model = (Tabela) table.getModel();
				Object[] row = new Object[1];
				row[0] = rowdata.getText();

				model.addRow(row);
				rowdata.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Nie mo¿na dodaæ wiersza:\n" + ex.getMessage(), "B³¹d",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		// usuwanie
		else if (e.getActionCommand().equalsIgnoreCase("usun")) {
			int selected = table.getSelectedRow();
			if (selected == -1)
				return;
			try {
				Tabela model = (Tabela) table.getModel();
				model.delRow(selected);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Nie mo¿na usun¹æ wiersza:\n" + ex.getMessage(), "B³¹d",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}
}
