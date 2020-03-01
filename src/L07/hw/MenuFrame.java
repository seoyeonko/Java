package L07.hw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuFrame extends JFrame implements ActionListener {

	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu helpMenu;

	private JMenuItem fItem1;
	private JMenuItem fItem2;
	private JMenuItem fItem3;

	private JMenuItem eItem1;
	private JCheckBoxMenuItem eItem2;

	private JMenuItem hItem1;

	private JPanel p;
	private JLabel l;
	private JTextField tf;

	private JButton b;

	public MenuFrame() {
//		f = new JFrame("�޴��� ��ȭ���� ����");
		super("�޴��� ��ȭ���� ����");

		createMenu();
		add(buildGUI());

		setSize(420, 300);
		setLocation(550,250);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // [x]��ư ������ �ƹ� ���� ���� �ʵ��� ����
		setVisible(true);

		// [�������� ���� ��ư] Ŭ���� Ȯ�δ�ȭ���ڷ� ���� ���� ����
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { // ������ �ý��� �޴����� ������ �ݱ⸦ �õ��� ��
				Exit();
			}
		});

	}

	// �޴��� ���� �Լ�
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb); // �޴��ٸ� �����ӿ� ����

		// JMenu
		fileMenu = new JMenu("����(F)");
		editMenu = new JMenu("����(E)");
		helpMenu = new JMenu("����(H)");
		fileMenu.setMnemonic('F');
		editMenu.setMnemonic('H');
		helpMenu.setMnemonic('E');

		// JMenuItem
		fItem1 = new JMenuItem("����(O)...");
		fItem2 = new JMenuItem("����(S)...");
		fItem3 = new JMenuItem("����(X)");
		fItem1.setMnemonic('O');
		fItem2.setMnemonic('S');
		fItem3.setMnemonic('X');

		eItem1 = new JMenuItem("�Է�(I)...");
		eItem2 = new JCheckBoxMenuItem("���� ����(D)");
		eItem1.setMnemonic('I');
		eItem2.setMnemonic('D');

		hItem1 = new JMenuItem("����(A)...");
		hItem1.setMnemonic('A');

		// �� ������Ʈ�鿡 ����
		fileMenu.add(fItem1);
		fileMenu.add(fItem2);
		fileMenu.add(new JSeparator()); // ���м� �߰�
		fileMenu.add(fItem3);

		editMenu.add(eItem1);
		editMenu.add(eItem2);

		helpMenu.add(hItem1);

		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(helpMenu);

		// �̺�Ʈ ������ ����
		// ���� - ����
		fItem1.addActionListener(new ActionListener() {
			JFileChooser chooser = new JFileChooser();

			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF Imanges", "jpg", "gif");
				chooser.setFileFilter(filter);

				// ���� ���̾�α� ���
				int ret = chooser.showOpenDialog(null); // ����

				// ����ڰ� â�� ������ �ݾҰų� ��ҹ�ư�� ���� ���
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ��� : �޼��� ��ȭ���� ����
				String filePath = chooser.getSelectedFile().getPath();

				if (chooser.getSelectedFile() != null) {
					JOptionPane.showMessageDialog(MenuFrame.this, filePath + "�� ����", "Ȯ��",
							JOptionPane.INFORMATION_MESSAGE);
				}

				pack();
			}

		});
		// ���� - ����
		fItem2.addActionListener(new ActionListener() {
			JFileChooser chooser = new JFileChooser();

			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF Imanges", "jpg", "gif");
				chooser.setFileFilter(filter);

				// ���� ���̾�α� ���
				int ret = chooser.showSaveDialog(null); // ����

				// ����ڰ� â�� ������ �ݾҰų� ��ҹ�ư�� ���� ���
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ��� : �޼��� ��ȭ���� ����
				String filePath = chooser.getSelectedFile().getPath();

				if (chooser.getSelectedFile() != null) {
					JOptionPane.showMessageDialog(MenuFrame.this, filePath + "������", "Ȯ��",
							JOptionPane.INFORMATION_MESSAGE);
				}

				pack();
			}

		});
		fItem3.addActionListener(this); // ���� - ����
		eItem1.addActionListener(this); // ���� -�Է�
		// ���� - ��������
		eItem2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				eItem2 = (JCheckBoxMenuItem) e.getSource();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					tf.setEditable(true);
				} else {
					tf.setEditable(false);
				}
			}
		});
		hItem1.addActionListener(this); // ���� - ����
	}

	// ȭ�� ���� �Լ�
	private JTabbedPane buildGUI() {
		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP); // �� ���� ����

		// �⺻ ��
		p = new JPanel();
		l = new JLabel("�̸�");
		tf = new JTextField("���Է�", 10);
		p.add(l);
		p.add(tf);

		// �߰� ��
		b = new JButton("�׽�Ʈ");

		pane.addTab("�⺻", p);
		pane.addTab("�߰�", b);

		return pane;
	}

	// �̺�Ʈ ��� : ��ȭ���� - JOptionPane ���
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == eItem1) { // ���� - �Է�
			String name = JOptionPane.showInputDialog("�̸��� �Է��ϼ���");
			if (name != null) {
				tf.setText(name);
			}
		} else if (src == hItem1) { // ���� - ����
			MyDialog dlg = new MyDialog(MenuFrame.this);
			dlg.setVisible(true);

		} else if (src == fItem3) { // ���� - ����
			Exit();
		}
	}

	// ���α׷� ���� ó���� ���� �޼���
	public void Exit() {
		int n = JOptionPane.showConfirmDialog(MenuFrame.this, "���� �����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
		// [Ȯ��]�� ���õ� ��쿡�� ����ǵ��� ó��
		if (n == JOptionPane.YES_OPTION) {
			System.exit(WindowConstants.DO_NOTHING_ON_CLOSE);
		} else if(n == JOptionPane.NO_OPTION) {
			return;
		}
	}

	public static void main(String[] args) {
		new MenuFrame();
	}

}
