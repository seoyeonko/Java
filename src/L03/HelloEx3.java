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
		JButton button = new JButton("확인");

		label = new JLabel("Hello World!");

		button.addActionListener(new ActionEventHandler2()); // *2. 매개변수 지우고 클래스 이름 2로 변경

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(text);
		panel.add(button);

		panel.setBackground(Color.ORANGE);

		frame.add(panel, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
	}

	private class ActionEventHandler2 implements ActionListener { // 내부 클래스 정의 : 외부 클래스의 멤버들을 자기 클래스멤버처럼 사용 가능, 내부클래스는
																	// 외부클래스를 통해 사용되야함

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = text.getText(); // 외부클래스 없이 text, label 직접 접근 가능해짐(내부클래스이므로)
			text.setText(""); // *1. 빈문자열 설정
			label.setText("Hello, " + name);

		}
	}

	public static void main(String[] args) {
		new HelloEx3();
	}
}

// 방법3: 내부 클래스 사용
