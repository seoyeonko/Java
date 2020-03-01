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
		super("테이블 예제2");

		buildGUI();

		setSize(450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void buildGUI() {
		this.add(createTablePanel(), BorderLayout.CENTER); // this는 frame 나 자신
		this.add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel createTablePanel() {
		JPanel p = new JPanel(new BorderLayout());
		// Q1. 테이블이 꽉채워나오지 않음?
		// A1. 테이블은 center영역에 꽉채워졌으나 테이블이 더해진 JPanel은 flowlayout이 기본값이기에 꽉채워 나오지 않음
		// 따라서, JPanel도 borderLayout설정 해야해

		String[] columnNames = { "이름", "나이", "성별" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		mTable = new JTable(model);
		mTable.setRowHeight(30); // 높이값 픽셀 설정

		p.add(new JScrollPane(mTable));
		// Q2. columnNames는 어디로?
		// A2. 스크롤 생성 외에도 columnNames 생성을 위해 JScrollPane과 연결은 반드시 필요
		
		return p;
	}

	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		JLabel l1 = new JLabel("이름");
		mTvName = new JTextField(5);
		JLabel l2 = new JLabel("나이");
		mTvAge = new JTextField(2);
		JLabel l3 = new JLabel("성별");
		mTvGender = new JTextField(2);

		JButton badd = new JButton("추가");
		JButton bdel = new JButton("삭제");
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

	// 이벤트 처리 : 방법3) 내부 클래스 사용
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
