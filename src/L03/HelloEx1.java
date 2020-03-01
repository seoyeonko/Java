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

public class HelloEx1 implements ActionListener {
	private JFrame frame;
	private JTextField text;
	private JLabel label;

	public HelloEx1() {
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

		button.addActionListener(this); // actionPerformed�� ������ �ִ� ��ü�� HelloEx1�� -> ��ο�� ���θ޼��忡�� ������� , �����������̵� ��ο��� ���
		// this?? => ���� actionPerformed�� ������ �����ϱ� ������ ����!��� �ǹ�

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(text);
		panel.add(button);

		panel.setBackground(Color.ORANGE);

		frame.add(panel, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new HelloEx1();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = text.getText(); // �ܺ�Ŭ���� ���� text, label ���� ���� ��������(����Ŭ�����̹Ƿ�)
		text.setText("");
		label.setText("Hello, " + name);

	}
}

// ���1: ���� Ŭ���� ���