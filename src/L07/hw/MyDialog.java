package L07.hw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyDialog extends JDialog {
	private JLabel l;
	private JButton b;
	private static Point pt;

	MyDialog(JFrame f) {
		super(f, "정보", true);

		buildGUI();
		setSize(200, 120);
		pt =f.getLocationOnScreen();
		int x = pt.x;
		int y = pt.y;
		setLocation(x + 105, y + 90);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void buildGUI() {

		setLayout(new BorderLayout());
		JPanel pl = new JPanel(new BorderLayout());
		JPanel pb = new JPanel();
		l = new JLabel("메뉴와 대화상자 연습", SwingConstants.CENTER); // 수평정렬 값
		b = new JButton("확인");

		l.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.BLACK));
		pl.add(l, BorderLayout.CENTER);
		pb.add(b);
		add(pl, BorderLayout.CENTER);
		add(pb, BorderLayout.SOUTH);

	}
}
