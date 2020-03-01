package L07;

// �ణ �ٵ��� �ʿ� �ִ� �ҽ� �ڵ�
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JDialogTest extends JFrame {
	JButton b1, b2, b3, b4;

	public JDialogTest() {
		super("JDialogTest");

		buildGUI();
		setBounds(100, 100, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void buildGUI() {
		setLayout(new FlowLayout());
//		JButton button = new JButton("��ȭ����");

		b1 = new JButton("��ȭ����1");
		b2 = new JButton("��ȭ����2");
		b3 = new JButton("��ȭ����3");
		b4 = new JButton("��ȭ����4");

//		add(button);
		add(b1);
		add(b2);
		add(b3);
		add(b4);

		b1.addActionListener(l);
		b2.addActionListener(l);
		b3.addActionListener(l);
		b4.addActionListener(l);
	}

	private ActionListener l = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// ��14 : JOptionPane
			Object src = e.getSource();

			if (src == b1) {
				JOptionPane.showMessageDialog(JDialogTest.this, "�޼��� ���̾�α׹ڽ�", "�޼���", JOptionPane.INFORMATION_MESSAGE);
			} else if (src == b2) {
				int n = JOptionPane.showConfirmDialog(JDialogTest.this, "Ȯ�δ��̾�α׹ڽ�", "Ȯ��",
						JOptionPane.YES_NO_CANCEL_OPTION);
			} else if (src == b3) {
				String s = JOptionPane.showInputDialog(JDialogTest.this, "�Է´��̾�α׹ڽ�", "�Է�",
						JOptionPane.QUESTION_MESSAGE);
				System.out.println(s);
			} else if (src == b4) {
				String[] str = { "�α���", "ȸ������" };
				int n = JOptionPane.showOptionDialog(JDialogTest.this, "�ɼǴ��̾�α׹ڽ�", "�ɼ�", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
				System.out.println(str[n]);
			}

			// ��9 : ��ȭ����
//			JMyDialog dlg = new JMyDialog(JDialogTest.this); // �׼Ǹ����ʷ� ���� ������ ������ ��ü
//			// this : �������� �ƴ� ������ ��ü�� ����Ŵ
//			// JDialogTest.this 
//			dlg.setVisible(true); // ����ȭ ����
//
//			String input = dlg.getInput(); // ���� ����ȭ�� ���� "���� ��"�� ����Ǵ� �Ϳ� ����	**
//			if (input != null)
//				System.out.println(input);
//			else
//				System.out.println("�Էµ��� ����");
		}
	};

	public static void main(String[] args) {
		new JDialogTest();
	}

}
