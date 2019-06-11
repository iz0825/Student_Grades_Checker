import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;

public class Window extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 707984200571994097L;
	
	//declaring data members
	private JButton btnCancel;
	private JButton btnOption1;
	private JButton btnOption2;
	private JButton btnOption3;

	private Option1 opt1Window;
	private Option2 opt2Window;
	private Option2 opt3Window;
	
	//constructor
	public Window() {
		setBounds(200, 100, 900,500);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel cancelPanel = new JPanel();
		getContentPane().add(cancelPanel, BorderLayout.SOUTH);
		cancelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Hannotate SC", Font.PLAIN, 14));
		cancelPanel.add(btnCancel);
		btnCancel.addActionListener(this);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTitle = new JLabel("Please choose what information you wish to view");
		lblTitle.setFont(new Font("Myanmar MN", Font.PLAIN, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTitle);
		
		JPanel optionsPanel = new JPanel();
		panel.add(optionsPanel);
		optionsPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		btnOption1 = new JButton("1 - Specific Student's Information");
		btnOption1.setFont(new Font("Hannotate SC", Font.PLAIN, 17));
		optionsPanel.add(btnOption1);
		btnOption1.addActionListener(this);
		
		btnOption2 = new JButton("2 - Students' Ranking - Total Grades");
		btnOption2.setFont(new Font("Hannotate SC", Font.PLAIN, 17));
		optionsPanel.add(btnOption2);
		btnOption2.addActionListener(this);
		
		btnOption3 = new JButton("3 - Students' Ranking - Progress");
		btnOption3.setFont(new Font("Hannotate SC", Font.PLAIN, 17));
		optionsPanel.add(btnOption3);
		btnOption3.addActionListener(this);
		
		opt1Window = new Option1();
		opt2Window = new Option2();
		opt3Window = new Option2();
				
	}

	//accessors
	public Option2 getOpt2() {
		return opt2Window;
	}
	public Option2 getOpt3() {
		return opt3Window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnCancel) {
			System.exit(0);
		}
		
		else if (e.getSource() == btnOption1) {
			opt1Window.setVisible(true);
		}
		
		else if (e.getSource() == btnOption2) {
			opt2Window.setTitle("Ranking based on total grades");
			try {
				opt2Window.appendString("===First Report of the year: ===" + "\n");
				Data.option2();
			} catch (BadLocationException | IOException e1) {
				e1.printStackTrace();	
			}
			opt2Window.setVisible(true);
		}
		
		else if (e.getSource() == btnOption3) {
			opt3Window.setTitle("Ranking based on progress");
			try {
				Data.option3();
			} catch (IOException | BadLocationException e1) {
				e1.printStackTrace();
			}
			opt3Window.setVisible(true);
		}
	}

}
