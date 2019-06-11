import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

//inheriting common methods and data members from JFrame class
public class Start extends JFrame implements ActionListener{
	//declaring the data members
	private static final long serialVersionUID = 877079241136465150L;
	
	private JButton btnCancel;
	private JButton btnOk;
	private JPanel panel_1;
	private JLabel lbl2;

	private Window window;
	private ErrorWindow error;
	
	private String outFile1N;
	private String outFile2N;
	private String outFile3N;
	private JPanel panel_2;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	//constructor
	public Start() {
		//setting the window to fixed size, appearing at certain position on the screen
		setBounds(300, 100, 687,342);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Hannotate SC", Font.PLAIN, 14));
		panel.add(btnCancel);
		btnCancel.addActionListener(this);
		
		btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Hannotate SC", Font.PLAIN, 14));
		panel.add(btnOk);
		btnOk.addActionListener(this);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		lbl2 = new JLabel("Please type in the names of the files you wish to store the results in: (FileName.csv)");
		lbl2.setFont(new Font("Myanmar MN", Font.PLAIN, 17));
		panel_1.add(lbl2);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(230, 230, 250));
		panel_1.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[][][grow]", "[][][]"));
		
		label1 = new JLabel("Student's information:");
		label1.setFont(new Font("Myanmar MN", Font.PLAIN, 16));
		panel_2.add(label1, "cell 0 0");
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_2.add(textField_1, "cell 2 0,growx");
		textField_1.setColumns(10);
		
		label2 = new JLabel("Ranking based on total grades:");
		label2.setFont(new Font("Myanmar MN", Font.PLAIN, 16));
		panel_2.add(label2, "cell 0 1,alignx left");
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_2.add(textField_2, "cell 2 1,growx");
		textField_2.setColumns(10);
		
		label3 = new JLabel("Ranking based on progress:");
		label3.setFont(new Font("Myanmar MN", Font.PLAIN, 16));
		panel_2.add(label3, "cell 0 2");
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_2.add(textField_3, "cell 2 2,growx");
		textField_3.setColumns(10);
	
		window = new Window();
		error = new ErrorWindow();
		
		outFile1N = null;
		outFile2N = null;
		outFile3N = null;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	//accessor that allows other class to call the option window created as private data member in this class
	public Window getWindow() {
		return window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if loop that determines which button is clicked - different procedures carried out afterwards
		if (e.getSource() == btnCancel) {
			//exiting the system if "Cancel" button is clicked
			System.exit(0);
		}
		
		else if (e.getSource() == btnOk) {
			outFile1N = textField_1.getText();
			outFile2N = textField_2.getText();
			outFile3N = textField_3.getText();		
			if (outFile1N.equals("") || outFile2N.equals("") || outFile3N.equals("")) {
				error.setVisible(true);
			}
			else {
				Data.createFiles(outFile1N, outFile2N, outFile3N);
				window.setVisible(true);
			}
		}
		
	}

}
