package L08;

import javax.swing.JFrame;

public class ImageDrawing extends JFrame {
	public ImageDrawing() {
		super("Image Drawing");
		
		buildGUI();

		setLocation(500, 200);
		setSize(400, 300);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void buildGUI()	{
		
	}
	
	public static void main(String[] args) {
		new ImageDrawing();
	}

}
