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

public class HelloEx4 {
	private JFrame frame;
	private JTextField text;
	private JLabel label;

	public HelloEx4() {
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

		button.addActionListener(listener);

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(text);
		panel.add(button);

		panel.setBackground(Color.ORANGE);

		frame.add(panel, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
	}

//	ActionListener listener = new ActionListener(); // �������� : ������ ��ü �����ϱ� ���� ����
//	// ����?? ActionListener�� �������̽�(�߻�޼��尡 ����) -> �߻����� ����ȭ ��ü ���� �Ұ�

	ActionListener listener = new ActionListener() { // listener: ��������
		// �͸� ���� Ŭ���� : Ŭ���� �̸� ���� ��ü�� ������ ���

		@Override
		public void actionPerformed(ActionEvent e) { // �޼��� ����
			String name = text.getText();
			text.setText("");

			label.setText("Hello, " + name);

			System.out.println(e.getActionCommand()); // * ���α׷����� � action�� ����Ǿ����� ���
//			if (e.getSource == button) ..e. // * button ��������� ������, �̺�Ʈ�� ������? Ȯ�ι�ư�� �ƴѰ��? �̷��� �ڵ�� Ȯ����

			System.exit(0); // * �ý��� ��������
		}

	}; // ; : ���� �Ҵ��ϴ� ����(line54-64)�� �����ٴ� ǥ��

	public static void main(String[] args) {
		new HelloEx4();
	}
}

// *�߿�
// ���4: �͸� ���� Ŭ���� ���
// �͸�Ŭ���� : �̸��� ���� Ŭ���� -> Ŭ������ �����ϴ� ���ÿ� ��ü�� ��������
// ���� �̺�Ʈ �ҽ��� �����Ҷ� ����
//��)�ټ����� �̺�Ʈ�ҽ��� (����/������) ������ ���