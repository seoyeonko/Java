package L03;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HelloEx2 {
	private JFrame frame;
	// *1. buildGUI() �Լ����� ���� ������ �Լ��� ����� ������ ������� ���ϹǷ� ���������� ������
	private JTextField text;
	private JLabel label;

	public HelloEx2() {
		frame = new JFrame("HelloEx2");

		buildGUI();

		frame.setSize(400, 100);
		frame.setLocation(500, 300);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void buildGUI() {
		text = new JTextField(10);
		JButton button = new JButton("Ȯ��"); // *2. �ۿ��� ������ ���� �����Ƿ� �������� ���� x

		// ActionEventHandler.java���� label��ü�� getText()�� null ���� �ƴ� ���¿��� �����ϱ� ����
		// �Ʒ� �ڵ��� �ϴ��� �ƴ� ��ܺκп� ��ġ! nor ������
		label = new JLabel("Hello World!");

		// �̺�Ʈ�ҽ��� �̺�Ʈ �������� ���� => ���ο� ��ü�� �����Ͽ� �̺�Ʈ�� ����
		button.addActionListener(new ActionEventHandler(text, label)); // ActionEventHandler.java

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(text);
		panel.add(button);

		panel.setBackground(Color.ORANGE);

		frame.add(panel, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new HelloEx2();
	}
}

// ���2: �Ϲ� Ŭ���� ���