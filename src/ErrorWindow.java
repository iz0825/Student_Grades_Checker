import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;

public class ErrorWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4677961831576823012L;
	
	//declaring data member
	private JButton btnNo;
	
	//constructor
	public ErrorWindow() {
		setBounds(500, 150, 522,191);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnNo = new JButton("Ok");
		btnNo.setFont(new Font("Hannotate SC", Font.PLAIN, 14));
		btnNo.setForeground(Color.BLACK);
		panel.add(btnNo);
		btnNo.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lbl = new JLabel("Invalid information entered.");
		lbl.setForeground(Color.RED);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Myanmar MN", Font.PLAIN, 17));
		panel_1.add(lbl, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnNo) {
			dispose();
		}
		
	}

}
