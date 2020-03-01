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
		JButton button = new JButton("확인");

		label = new JLabel("Hello World!");

		button.addActionListener(this); // actionPerformed를 가지고 있는 객체는 HelloEx1임 -> 헬로우는 메인메서드에서 만들어짐 , 빌드지유아이도 헬로우의 멤버
		// this?? => 내가 actionPerformed를 가지고 있으니까 나한테 보내!라는 의미

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
		String name = text.getText(); // 외부클래스 없이 text, label 직접 접근 가능해짐(내부클래스이므로)
		text.setText("");
		label.setText("Hello, " + name);

	}
}

// 방법1: 현재 클래스 사용