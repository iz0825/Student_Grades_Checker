import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class Option2 extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5993496479890245719L;
	//declaring data members
	private JPanel panel;
	private JLabel lbl;
	private JPanel panel_1;
	private JButton btnOk;
	private JScrollPane scrollPane;
	private JTextPane textPane;
	
	//constructor
	public Option2() {
		setBounds(400, 130, 806,521);

		panel = new JPanel();
		panel.setBackground(new Color(144, 238, 144));
		getContentPane().add(panel, BorderLayout.NORTH);

		lbl = new JLabel("");
		lbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel.add(lbl);

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(144, 238, 144));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnOk = new JButton("Ok");
		panel_1.add(btnOk);
		btnOk.addActionListener(this);

	}
	
	//clearing all the text displayed on the TextPane
	public void clearText() {
		textPane.setText(null);
	}
	
	//adding String variable to TextPane with default style
	public void appendString(String str) throws BadLocationException {
	     StyledDocument document = (StyledDocument)textPane.getDocument();
	     document.insertString(document.getLength(), str, null);
	 }
	
	//add String variable to TextPane with highlighting
	public void appendString2(String str) throws BadLocationException {
		StyledDocument document = (StyledDocument)textPane.getDocument();
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setBackground(attributes, Color.yellow);
		StyleConstants.setForeground(attributes, Color.red);
		document.insertString(document.getLength(), str, attributes);
	}
	
	//mutator that allow two option windows to have different title 
	public void setTitle(String title) {
		lbl.setText(title);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			dispose();
		}
	}

}
