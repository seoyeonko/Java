package L07;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JMyDialog extends JDialog {
	private JTextField text;
	private JButton btn;

	JMyDialog(JFrame f) {
		super(f, "MyDialog", true);

		buildGUI();

		setLocation(150, 200);
		pack(); // 현재 이 대화상자를 적당한 크기로 설정 (프레임도 가능)

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // 대화상자를 닫는 의미

	}

	private void buildGUI() {
		setLayout(new FlowLayout());

		text = new JTextField(10);
		btn = new JButton("확인");

		add(text);
		add(btn, BorderLayout.EAST);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // 대화상자 닫힘
			}

		});
	}

	String getInput() {
		String str = text.getText(); 
		if (!str.isEmpty()) // 입력된 값의 유무
			return str;
		else
			return null;
	}
}
