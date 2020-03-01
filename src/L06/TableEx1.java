package L06;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableEx1 extends JFrame implements ActionListener {
	private JTable mTable;

	public TableEx1() {
		super("���̺� ����1");

		buildGUI();

		setSize(450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void buildGUI() {
		this.add(createTablePanel(), BorderLayout.CENTER); // this�� frame �� �ڽ�
		this.add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel createTablePanel() {
		JPanel p = new JPanel(new BorderLayout());
		// Q1. ���̺��� ��ä�������� ����?
		// A1. ���̺��� center������ ��ä�������� ���̺��� ������ JPanel�� flowlayout�� �⺻���̱⿡ ��ä�� ������ ����
		// ����, JPanel�� borderLayout���� �ؾ���

		String[] columnNames = { "�̸�", "����", "����" };
		Object[][] data = { { new String("���ָ�"), new Integer(22), "��" }, { "�Ҽ���", 20, "��" } }; // wrapper Ŭ����

		mTable = new JTable(data, columnNames); // ���1: �迭 �μ�
		p.add(new JScrollPane(mTable));
		// Q2. columnNames�� ����?
		// A2. ��ũ�� ���� �ܿ��� columnNames ������ ���� JScrollPane�� ������ �ݵ�� �ʿ�

		mTable.setRowHeight(30); // ���̰� �ȼ� ����
		return p;
	}

	
	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new BorderLayout());
		JButton b = new JButton("���");
		p.add(b);
		b.addActionListener(this);
		return p;
	}

	// �̺�Ʈ ó�� : ��¹�ư ������ �ܼ� â�� �ش� ���� ����ϱ� (���1 : ���� Ŭ���� ���)
	
	// ���1
//	public void actionPerformed(ActionEvent e) {
//		int rowNum = mTable.getRowCount();
//		int colNum = mTable.getColumnCount();
//
//		for (int c = 0; c < colNum; c++) {
//			String colName = mTable.getColumnName(c);
//			System.out.print(colName + "\t");
//		}
//		System.out.println();
//
//		for (int r = 0; r < rowNum; r++) {
//			for (int c = 0; c < colNum; c++) {
//				Object cell = mTable.getValueAt(r, c);
//				System.out.print(cell + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
	
	// ���2
	public void actionPerformed(ActionEvent e) {
		TableModel model = mTable.getModel();
		
		int rowNum = model.getRowCount();
		int colNum = model.getColumnCount();
		
		for (int c = 0; c < colNum; c++) {
			String colName = model.getColumnName(c);
			System.out.print(colName + "\t");
		}
		System.out.println();

		for (int r = 0; r < rowNum; r++) {
			for (int c = 0; c < colNum; c++) {
				Object cell = model.getValueAt(r, c);
				System.out.print(cell + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
		
	
	public static void main(String[] args) {
		new TableEx1();
	}

}
