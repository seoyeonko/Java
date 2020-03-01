package L07;

import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuEx extends JFrame {

	// �Ӽ�
	JPanel p_main;
	CardLayout card = new CardLayout();

	// ������
	MenuEx() {
		super("JMenu");

		createMenu();
		buildGUI();

		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// ȭ�� ���� �Լ�
	private void buildGUI() {
		p_main = new JPanel(card);

		p_main.add("main", new JLabel("Hello!", JLabel.CENTER));
		p_main.add("second", new JButton("�ȳ��ϼ���"));

		add(p_main);
	}

	// �޴��� ���� �Լ�
	private void createMenu() {
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		// JMenu
		JMenu menu1 = new JMenu("ù��°(F)");
		menu1.setMnemonic('F'); // ����Ű ��� : ���������� GUI ����� ���� ǥ�� (artŰ + ���� charŰ)
		JMenu menu2 = new JMenu("�ι�°");

		menubar.add(menu1);
		menubar.add(menu2);

		// JMenuItem
		JMenuItem item1 = new JMenuItem("123");
		item1.setMnemonic('1'); // ����Ű ���
		JMenuItem item2 = new JMenuItem("456");

		item1.addActionListener(listener1);
		item2.addActionListener(listener1);

		menu1.add(item1);
		menu1.add(item2);

		menu1.addSeparator();

		// JCheckBoxMenuItem
		JCheckBoxMenuItem citem1 = new JCheckBoxMenuItem("abc");
		JCheckBoxMenuItem citem2 = new JCheckBoxMenuItem("def");

		citem1.addItemListener(listener2);
		citem2.addItemListener(listener2);

		menu1.add(citem1);
		menu1.add(citem2);

		// Cascading Menu
		JMenu sub = new JMenu("�θ޴�");

		menu2.add(sub);
		JMenuItem titem1 = new JMenuItem("���");
		JMenuItem titem2 = new JMenuItem("����");

		sub.add(titem1);
		sub.add(titem2);

		titem1.addActionListener(listener3);
		titem2.addActionListener(listener3);

	}

	// �̺�Ʈ ������ ����
	private ActionListener listener1 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			card.next(p_main);
		}
	};

	private ItemListener listener2 = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
		}
	};

	private ActionListener listener3 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	};

	// ���� �޼ҵ�
	public static void main(String[] args) {
		new MenuEx();
	}

}
