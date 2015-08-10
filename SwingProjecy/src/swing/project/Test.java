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

	private static final String USUN2 = "usun";

	private static final long serialVersionUID = -646317234260742582L;
	
	private JTable table;
	
	private JTextField rowdata;
	private JTextField rowdata2;
	
	private JButton dodaj;
	private JButton usun;
	private JButton zmien;
	private JButton odczytaj;

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
		Object[][] example_data = { { "Klient1", "dane1" }, { "Klient2", "dane1" }, { "Klient3", "dane1" } };
		String nazwyKolumn[] = { "Nazwa", "Dane" };
		table = new JTable(new Tabela(example_data, nazwyKolumn));
		this.add(new JScrollPane(table));
		
		rowdata = new JTextField("Klient");
		rowdata2 = new JTextField("dane1");

		dodaj = new JButton("Dodaj");
		dodaj.setActionCommand("dodaj");
		dodaj.addActionListener(this);

		usun = new JButton("Usuñ");
		usun.setActionCommand(USUN2);
		usun.addActionListener(this);

		zmien = new JButton("Zmien");
		zmien.setActionCommand("zmien");
		zmien.addActionListener(this);
		
		odczytaj = new JButton("Odczytaj");
		odczytaj.setActionCommand("czytaj");
		odczytaj.addActionListener(this);

	}

	private void addNPlaceComponents() {
		

		JScrollPane sp = new JScrollPane(table);
		JLabel rowdata_lab = new JLabel("Dodaj klienta:");
		JLabel rowdata_lab2 = new JLabel("Podaj dane:");

		setLayout(null);

		add(sp);
		add(rowdata);
		add(rowdata2);
		add(dodaj);
		add(usun);
		add(zmien);
		add(odczytaj);
		add(rowdata_lab);
		add(rowdata_lab2);

		sp.setBounds(5, 5, 380, 200);
		rowdata_lab.setBounds(5, 210, rowdata_lab.getPreferredSize().width, 20);
		rowdata_lab2.setBounds(200, 220, rowdata_lab2.getPreferredSize().width, 12);

		rowdata.setBounds(10 + rowdata_lab.getPreferredSize().width, 210, 100, 20);
		rowdata2.setBounds(230 + rowdata_lab2.getPreferredSize().width, 210, 100, 20);

		dodaj.setBounds(5, 235, 100, 25);
		
		usun.setBounds(110, 235, 100, 25);

		zmien.setBounds(215, 235, 100, 25);
		
		odczytaj.setBounds(320,235,100,25);

	}

	public void actionPerformed(ActionEvent e) {
	
		// dodawanie
		if (e.getActionCommand().equalsIgnoreCase("dodaj")) {
			if (rowdata.getText().equals("") && rowdata2.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Wpisz jak¹œ dan¹ w polu 'wiersz'", "Brak danych",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {

				Tabela model = (Tabela) table.getModel();
				Object[] row = new Object[2];
				row[0] = rowdata.getText();
				row[1] = rowdata2.getText();

				model.dodajKolumne(row);
				rowdata.setText("");
				rowdata2.setText("");

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Nie mo¿na dodaæ wiersza:\n" + ex.getMessage(), "B³¹d",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		// usuwanie
		else if (e.getActionCommand().equalsIgnoreCase(USUN2)) {
				
			int selected = table.getSelectedRow();
			if (selected == -1)
				return;
			try {
				Tabela model = (Tabela) table.getModel();
				model.usunKolumne(selected);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Nie mo¿na usun¹æ wiersza:\n" + ex.getMessage(), "B³¹d",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		// zmien
		//else if()
//		else if(e.getActionCommand().equalsIgnoreCase("zmien")){
//			try{
//				JOptionPane.showMessageDialog(this, "nie mozna zmienic");
//				
//			}
//			catch(Exception ex){
//				JOptionPane.showMessageDialog(this, "nie mozna zmienic"+ex.getMessage());
//				return;
//			}
//			
//		}

	}
}
