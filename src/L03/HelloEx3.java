package L03;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HelloEx3 {
	private JFrame frame;
	private JTextField text;
	private JLabel label;

	public HelloEx3() {
		frame = new JFrame("HelloEx2");

		buildGUI();

		frame.setSize(400, 100);
		frame.setLocation(500, 300);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void buildGUI() {

		text = new JTextField(10);
		JButton button = new JButton("Ȯ��");

		label = new JLabel("Hello World!");

		button.addActionListener(new ActionEventHandler2()); // *2. �Ű����� ����� Ŭ���� �̸� 2�� ����

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(text);
		panel.add(button);

		panel.setBackground(Color.ORANGE);

		frame.add(panel, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
	}

	private class ActionEventHandler2 implements ActionListener { // ���� Ŭ���� ���� : �ܺ� Ŭ������ ������� �ڱ� Ŭ�������ó�� ��� ����, ����Ŭ������
																	// �ܺ�Ŭ������ ���� ���Ǿ���

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = text.getText(); // �ܺ�Ŭ���� ���� text, label ���� ���� ��������(����Ŭ�����̹Ƿ�)
			text.setText(""); // *1. ���ڿ� ����
			label.setText("Hello, " + name);

		}
	}

	public static void main(String[] args) {
		new HelloEx3();
	}
}

// ���3: ���� Ŭ���� ���
