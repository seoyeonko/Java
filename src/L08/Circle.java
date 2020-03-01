package L08;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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

			addMouseListener(new MouseListener() {

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
				public void mouseEntered(MouseEvent e) { // ���콺�� ������ ������Ʈ ���� �ö�� ��
				}

				@Override
				public void mouseExited(MouseEvent e) { // ������Ʈ�� �ö�� ���콺�� ������Ʈ�� ��� ��

				}

				@Override
				public void mousePressed(MouseEvent e) { // ���콺 ��ư�� ������ ��
					// if(���� Ŭ���� ���)
					// ���� ���� �����ǰ� ���� �巡�� �ǵ���
					p = e.getPoint();
					pointList.remove(p);
 
					// else if(���� �ƴ� ������ Ŭ���� ���)
					// ���� �׷���
					
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) { // ���콺 ��ư�� ������ ��
					// if, ������ ������ �����ǰ� �����̱�
					// else, �巡�� ������ ��ġ�� ���� �׷�����
					p = e.getPoint();
					pointList.add(p);

					repaint();
				}
			});

			// ���� �巡���ϴ� ������
			addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) { // ���콺�� ������ ������Ʈ ������ �巡���� ��
//               setTitle("(" + e.getX() + ", " + e.getY() + ")");
					pointList.remove(p);
					p = e.getPoint();
					pointList.add(p);

					repaint();
				}

				@Override
				public void mouseMoved(MouseEvent e) { // ���콺�� ������ ������Ʈ ������ ������ ��
				}

			});
		}

		// ���� �׸��� paint �޼���
		public void paint(Graphics g) {
//         Color c = new Color(0, 0, 225, 100);
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