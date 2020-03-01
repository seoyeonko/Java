package L08;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class LogicalFontEx extends JFrame {

	public LogicalFontEx() {
		super("Logical Font");

		buildGUI();

		setLocation(500, 200);
		setSize(400, 300);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void buildGUI() {
		LogicalFontTestCanvas drawingCanvas = new LogicalFontTestCanvas();
		add(drawingCanvas);
	}

	class LogicalFontTestCanvas extends JComponent {
		Font[] font = new Font[5];

		LogicalFontTestCanvas() {
			font[0] = new Font("Serif", Font.PLAIN, 20);
		}

		public void paint(Graphics g) {
			for (int i = 0; i < font.length; i++) {

			}
		}
	}

	public static void main(String[] args) {
		new LogicalFontEx();
	}

}
