package L08.hw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Circle extends JFrame {

	public Circle() {
		super("Paint Ex");

		buildGUI();

		setLocation(200, 200);
		setSize(500, 500);
		setContentPane(new PaintCanvas());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void buildGUI() {
		JPanel p = new JPanel();
		PaintCanvas paint = new PaintCanvas();
		p.add(paint);
		add(p);
	}

	// ���� Ŭ������ �ذ�
	public class PaintCanvas extends JComponent {

		Point p = null;

		static final int width = 50;
		static final int height = 50;

		ArrayList<Point> pointList = new ArrayList<Point>();
		int dragId = -1;

		PaintCanvas() {
			super();
			drawingCircleListener();
		}

		// ���� �׸��� ������
		private void drawingCircleListener() {

			addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) { // ���콺 Ŭ���� ��
					if (pointList.size() < 100) {
						p = e.getPoint();
						pointList.add(p);
					} else {
						return;
					}
					repaint();
				}

				@Override
				public void mousePressed(MouseEvent e) { // ���콺 ��ư�� ������ ��
					// if(���� Ŭ���� ���)
					// ���� ���� �����ǰ� ���� �巡�� �ǵ���
					// else (����� Ŭ���� ���)
					int x = e.getX();
					int y = e.getY();

					if (((x - 20 <= x) && (x <= x + 20)) && ((y - 20 <= y) && (y <= y + 20))) {
						pointList.remove(p);
					} else {
						p = e.getPoint();
						pointList.add(p);
					}
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) { // ���콺 ��ư�� ������ ��

					p = e.getPoint();
					pointList.add(p);

					repaint();
				}
			});

			// ���� �巡���ϴ� ������
			addMouseMotionListener(new MouseMotionAdapter() {

				@Override
				public void mouseDragged(MouseEvent e) { // ���콺�� ������ ������Ʈ ������ �巡���� ��
					pointList.remove(p);
					p = e.getPoint();
					pointList.add(p);

					repaint();
				}

			});
		}

		// ���� �׸��� paint �޼���
		public void paint(Graphics g) {
			g.setColor(Color.BLUE);
			for (int i = 0; i < pointList.size(); i++) {
				g.fillOval(pointList.get(i).x - 20, pointList.get(i).y - 20, 40, 40); // get(i).x�� get(i).y�� ���� �߽�
			}
		}
	}

	public static void main(String[] args) {
		new Circle();
	}

}
