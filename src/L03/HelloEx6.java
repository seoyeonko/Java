// 191024 windowEvent ó�� �߰�
package L03;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HelloEx6 {
	private JFrame frame;
	// buildGUI()�ܿ� ������ �����Ƿ� ��������ν��� ����� ��ȿ�� -> *1 ���������� ����
//	private JTextField text;
//	private JLabel label;

	public HelloEx6() {
		frame = new JFrame("HelloEx2");

		buildGUI();

		frame.setSize(400, 100);
		frame.setLocation(500, 300);

//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(wlistener);
		frame.setVisible(true);
	}

	private void buildGUI() {

		final JTextField text = new JTextField(10); // *1 *2
		JButton button = new JButton("Ȯ��");

		final JLabel label = new JLabel("Hello World!"); // *1 *2
		button.addActionListener(new ActionListener() {
			// �������̽�(ActopmListener)�̹Ƿ� ��ü�� �������� �� �ִ� ������ ����
			@Override
			public void actionPerformed(ActionEvent e) {// +) ȣ��Ǵ� ����: buildGUI()�� ������? ����Ǵ�? ����??? ��) ��ư�� ������ ����!!!!
				// buildGUI�� ������ �������� ����ȭ �Ǿ����� �������� ��ư�� ������ ȣ��Ǵ� ����
				// *2 ���������� �Լ��� ȣ��Ǽ� ������ ���� ����� �Լ��� ������ ������� Ư������ �̸� �����ϱ� ���� final Ű���� �ο�
				// => ���α׷��� �������� �Լ��� ���� ������� �ʰ� ��������
				String name = text.getText();
				text.setText("");

				label.setText("Hello, " + name);
			}

		});

//		button.addActionListener(new ActionListener() { // �͸� ���� Ŭ���� : Ŭ���� �̸� ���� ��ü�������� ���
//
//			@Override
//			public void actionPerformed(ActionEvent e) { // �޼��� ����
//				String name = text.getText();
//				text.setText("");
//
//				label.setText("Hello, " + name);
//			}
//
//		});

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(text);
		panel.add(button);

		panel.setBackground(Color.ORANGE);

		frame.add(panel, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
	}

	// ������ �����
	WindowListener wlistener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {

			System.out.println("���α׷��� �����մϴ�");
			System.exit(0);
		}
		// �������̽��� �ƴ� Ŭ����!!

	};

	// ������ ������
	/*
	 * // + �߰� �ڵ� WindowListener wlistener = new WindowListener() {
	 * 
	 * @Override public void windowActivated(WindowEvent arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void windowClosed(WindowEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void windowClosing(WindowEvent e) { // �ݱ� ��ư�� ������ ���� // TODO
	 * Auto-generated method stub System.out.println("���α׷��� �����մϴ�.");
	 * System.exit(0); // ���α׷� ����Ǵ� ���� }
	 * 
	 * @Override public void windowDeactivated(WindowEvent e) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void windowDeiconified(WindowEvent e) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void windowIconified(WindowEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void windowOpened(WindowEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * };
	 */

	public static void main(String[] args) {
		new HelloEx6();
	}
}

// *�߿�
// ���5: �͸���Ŭ���� �ӽ� ��ü ���
// ������ ��ü�� �� �ѹ��� ���Ǵ� ��쿡 ����
//��) �ټ����� �̺�Ʈ�ҽ��� ���� �ٸ� ���� �ϴ� ���
