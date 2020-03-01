package L08;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class BasicDrawing extends JFrame {
	public BasicDrawing() {
		super("BaxicFrawing");

		buildGUI();

		setLocation(500, 200);
		setSize(400, 300);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void buildGUI() {
		BasicCanvas drawingCanvaas = new BasicCanvas();
		add(drawingCanvaas);
	}

	// ���� Ŭ������ ����
	class BasicCanvas extends JComponent {

		static final int x = 150;
		static final int y = 100;

		@Override
		public void paint(Graphics g) {
			// super.paint(g);

			// ���� 1 : �׷��� ��ǥ��
//			g.drawLine(0, 0, 100, 50); 

			// ���� 2 : String ���
//			g.drawString("Hello, World", x, y);
//			g.drawLine(x - 3, y + 10, x + 70, y + 10);

			// ���� 3 : �ڵ��� �׸���
//			g.setColor(Color.RED);
//			g.drawRect(150, 50, 80, 30);
//			g.drawRect(100, 80, 180, 30);
//			g.setColor(Color.BLUE);
//			g.drawOval(125, 110, 30, 30);
//			g.drawOval(225, 110, 30, 30);
//			g.setColor(Color.BLACK);
//			g.drawLine(70, 140, 310, 140);

			// ���� 4: �ڵ��� ���� ĥ�ϱ�
			Color c = new Color(255, 0, 0, 128); // ���� 50
			g.setColor(c);
			g.fillRect(150, 50, 80, 30);
			g.fillRect(100, 80, 180, 30);
			g.setColor(Color.BLACK);
			g.fillOval(125, 110, 30, 30);
			g.fillOval(225, 110, 30, 30);
			g.setColor(Color.GREEN);
			g.drawLine(70, 140, 310, 140);

		}
	}

	public static void main(String[] args) {
		new BasicDrawing();
	}
}
