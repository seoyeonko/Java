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
		pack(); // ���� �� ��ȭ���ڸ� ������ ũ��� ���� (�����ӵ� ����)

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // ��ȭ���ڸ� �ݴ� �ǹ�

	}

	private void buildGUI() {
		setLayout(new FlowLayout());

		text = new JTextField(10);
		btn = new JButton("Ȯ��");

		add(text);
		add(btn, BorderLayout.EAST);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // ��ȭ���� ����
			}

		});
	}

	String getInput() {
		String str = text.getText(); 
		if (!str.isEmpty()) // �Էµ� ���� ����
			return str;
		else
			return null;
	}
}
