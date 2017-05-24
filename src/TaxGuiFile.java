import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TaxGuiFile extends JFrame {
	JLabel lblGrIncome;
	JTextField txtGrossIncome = new JTextField(15);
	JLabel lblDependents = new JLabel("Number of Dependents");
	JTextField txtDependents = new JTextField(2);
	JLabel lblState = new JLabel("State");
	
	//Define a data model for the ComboBox chState
	Vector<String> states = new Vector<>(50);
	
	//Create a combobox to get data from the model 
	JComboBox chState = new JComboBox<>(states);
	
	JLabel lblTax = new JLabel("State Tax: ");
	JTextField txtStateTax = new JTextField(10);
	JButton bGo = new JButton("Go");
	JButton bReset = new JButton("Reset");
	
	public TaxGuiFile() {
		lblGrIncome = new JLabel("Gross Income: ");
		GridLayout gr = new GridLayout(5, 2, 1, 1);
		setLayout(gr);
		
		
		add(lblGrIncome);
		add(txtGrossIncome);
		add(lblDependents);
		add(txtDependents);
		add(lblState);
		add(chState);
		add(lblTax);
		add(txtStateTax);
		add(bGo);
		add(bReset);
		
		
		//Populate states from a file
		populateStates();
		
		chState.setSelectedIndex(0);
		txtStateTax.setEditable(false);
		
		//The Button Go processing using lambda expression
		bGo.addActionListener(evt -> {
			try{
				int grossInc = Integer.parseInt(txtGrossIncome.getText());
				int dependents = Integer.parseInt(txtDependents.getText());
				String state = (String) chState.getSelectedItem();
				
				Tax tax = new Tax(grossInc, state, dependents);
				String sTax = Double.toString(tax.calcTax());
				txtStateTax.setText(sTax);
				}catch (Exception e) {
					txtStateTax.setText(e.getMessage());
				}
		});
		
		//The Button Reset processing using lambda expression
		bReset.addActionListener(evt ->{
			txtGrossIncome.setText("");
			txtDependents.setText("");
			chState.setSelectedIndex(0);
			txtStateTax.setText("");
		});
		
		//Define, instantiate and register a WindowAdapter to process 
		//windowClosing Event of this frame
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
		//The code below will read the file states.txt and 
		//populate the drop-down chStates
	
	private void populateStates(){
		states.add("Select States");
		
		try (FileInputStream myFile = new FileInputStream("/Users/YOO/Documents/workspace/TaxCalculatorSwing/state.txt");
				InputStreamReader inputStreamReader = new InputStreamReader(myFile, "UTF8");
				BufferedReader reader = new BufferedReader(inputStreamReader);) {
			
			String stateName;
			while ((stateName = reader.readLine()) != null) {
				states.add(stateName);	
			}
		} catch (IOException ice) {
			txtStateTax.setText("Can't read states.txt " + ice.getMessage());
		}
	}
	
	
	public static void main(String[] args){
		TaxGuiFile taxFrame = new TaxGuiFile();
		taxFrame.setSize(450, 200);
		taxFrame.setVisible(true);
	}
	
	
}
