import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import java.awt.Font;

public class Option1 extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3367842595486426446L;
	
	//declaring data members
	private JTextField textField;
	private JButton btnOk;
	private JButton btnCancel;
	
	public Option2 result;
	public ErrorWindow ew;
	
	private String studentNum = null;
	
	//constructor
	public Option1() {
		setBounds(200, 100, 700,300);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel cancelPanel = new JPanel();
		getContentPane().add(cancelPanel, BorderLayout.SOUTH);
		cancelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Hannotate SC", Font.PLAIN, 14));
		cancelPanel.add(btnCancel);
		btnCancel.addActionListener(this);

		btnOk = new JButton("OK");
		btnOk.setFont(new Font("Hannotate SC", Font.PLAIN, 14));
		cancelPanel.add(btnOk);
		btnOk.addActionListener(this);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblTitle = new JLabel("Please type in the student's admission number below");
		lblTitle.setFont(new Font("Myanmar MN", Font.PLAIN, 22));
		panel.add(lblTitle);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		ew = new ErrorWindow();
		result = new Option2();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnOk) {
			studentNum = textField.getText();
			if (Data.searchStudent(studentNum) != null) {
					try {
						result.setTitle("Information About the Student");
						result.clearText();
						String text = Data.option1(studentNum);
						result.appendString(text);
						result.setVisible(true);
						dispose();
					} catch (IOException | InterruptedException | BadLocationException e1) {
						e1.printStackTrace();
					}
			}
			else {
				ew.setVisible(true);
				dispose();
			}
		}

		else if (e.getSource() == btnCancel) {
			dispose();
		}

	}

}
