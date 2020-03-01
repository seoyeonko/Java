package L08.hw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Graph extends JFrame {

	int kor;
	int eng;
	int math;

	public Graph() {
		super("���� ����ǥ");

		buildGUI();

		setLocation(500, 200);
		setSize(450, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void buildGUI() {
		JPanel p = new JPanel(new BorderLayout());
		JPanel pButtom = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// ��� �׷��� �׸���
		Drawing drawing = new Drawing();

		// �ϴ� ���� �Է�ĭ
		JTextField t1 = new JTextField(3);
		JTextField t2 = new JTextField(3);
		JTextField t3 = new JTextField(3);
		JButton b = new JButton("�׷��� �׸���");
		b.addActionListener(new ActionListener() { // ��ư �̺�Ʈ ó��

			@Override
			public void actionPerformed(ActionEvent e) {
				kor = Integer.parseInt(t1.getText());
				eng = Integer.parseInt(t2.getText());
				math = Integer.parseInt(t3.getText());

				drawing.repaint();
			}

		});

		pButtom.add(new JLabel("����"));
		pButtom.add(t1);
		pButtom.add(new JLabel("����"));
		pButtom.add(t2);
		pButtom.add(new JLabel("����"));
		pButtom.add(t3);
		pButtom.add(b);

		// ��ġ ����
		p.add(drawing, BorderLayout.CENTER);
		p.add(pButtom, BorderLayout.SOUTH);

		add(p);
	}

	// ���� Ŭ������ �ذ�
	class Drawing extends JComponent {

		final static int x = 50;
		final static int y = 50;

		@Override
		public void paint(Graphics g) {
			for (int i = 0; i < 10; i++) {
				g.drawLine(x, y + 20 * i, x + 350, y + 20 * i);
				g.drawString(10 * (10 - i) + "", x - 25, y + 20 * i);
			}
			g.drawLine(x, y + 200, x + 350, y + 200);
			g.drawLine(x, y - 30, x, y + 200);
			g.drawString("����", x + 65, y + 220);
			g.drawString("����", x + 165, y + 220);
			g.drawString("����", x + 270, y + 220);

			g.setColor(Color.RED);
			g.fillRect(x + 65, y + 2 * (100 - kor), 10, kor * 2);
			g.fillRect(x + 165, y + 2 * (100 - eng), 10, eng * 2);
			g.fillRect(x + 270, y + 2 * (100 - math), 10, math * 2);

		}
	}

	public static void main(String args[]) {
		new Graph();
	}
}
