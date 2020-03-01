package L06;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DefaultTableModelEx extends JFrame {
	private JTable mTable;
	private JTextField mTvName;
	private JTextField mTvAge;
	private JTextField mTvGender;
	
	public DefaultTableModelEx() {
		super("���̺� ����2");

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
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		mTable = new JTable(model);
		mTable.setRowHeight(30); // ���̰� �ȼ� ����

		p.add(new JScrollPane(mTable));
		// Q2. columnNames�� ����?
		// A2. ��ũ�� ���� �ܿ��� columnNames ������ ���� JScrollPane�� ������ �ݵ�� �ʿ�
		
		return p;
	}

	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		JLabel l1 = new JLabel("�̸�");
		mTvName = new JTextField(5);
		JLabel l2 = new JLabel("����");
		mTvAge = new JTextField(2);
		JLabel l3 = new JLabel("����");
		mTvGender = new JTextField(2);

		JButton badd = new JButton("�߰�");
		JButton bdel = new JButton("����");
		badd.addActionListener(mAddActonListener);
		bdel.addActionListener(mRemoveActionListener);
		
		p1.add(l1);
		p1.add(mTvName);
		p1.add(l2);
		p1.add(mTvAge);
		p1.add(l3);
		p1.add(mTvGender);
		p2.add(badd);
		p2.add(bdel);

		p.add(p1);
		p.add(p2);

		return p;
	}

	// �̺�Ʈ ó�� : ���3) ���� Ŭ���� ���
	ActionListener mAddActonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String arr[] = new String[3];
			arr[0] = mTvName.getText();
			arr[2] = mTvAge.getText();
			arr[2] = mTvGender.getText();
			
			DefaultTableModel model = (DefaultTableModel) mTable.getModel();
			model.addRow(arr);
			
			mTvName.setText("");
			mTvAge.setText("");
			mTvGender.setText("");
		}
	};
	
	ActionListener mRemoveActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int row = mTable.getSelectedRow();
			if(row==-1) return;
			
			DefaultTableModel model = (DefaultTableModel) mTable.getModel();
			model.removeRow(row);
		}
	};
	
	public static void main(String[] args) {
		new DefaultTableModelEx();

	}


}
