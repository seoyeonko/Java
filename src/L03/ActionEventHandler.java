package L03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ActionEventHandler implements ActionListener {

	private JTextField text;
	private JLabel label;

	ActionEventHandler(JTextField text, JLabel label) {
		this.text = text;
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent e) { // 추상 메서드의 몸체를 재정의
//		System.out.println("버튼이 눌림!");
//		label.setText("hi");
		String name = text.getText();
		
		label.setText("Hello, " + name);
	}

}
