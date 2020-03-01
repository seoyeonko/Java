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
	// *1. buildGUI() 함수안의 지역 변수는 함수의 사용이 끝나면 사용하지 못하므로 전역변수로 선언함
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
		JButton button = new JButton("확인"); // *2. 밖에서 접근할 일이 없으므로 전역변수 선언 x

		// ActionEventHandler.java에서 label객체를 getText()에 null 값이 아닌 상태에서 전달하기 위해
		// 아래 코드의 하단이 아닌 상단부분에 배치! nor 오류남
		label = new JLabel("Hello World!");

		// 이벤트소스와 이벤트 리스너의 연결 => 새로운 객체를 생성하여 이벤트를 연결
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

// 방법2: 일반 클래스 사용