package L06;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import java.awt.event.*;

public class TableBasicRenderer extends JFrame {

	private JTable mTable;
	
	public TableBasicRenderer() {
		super("테이블 테스트5");
		
		buildGUI();
		registerTableSelectionEventListener();
		
		setSize(450, 300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void buildGUI() {
		add(createTablePanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		/*****************************************************************************/
		String[] columnNames = { "이  름", "나이", "성별" };
		Object[][] data = { { "고주몽", 22, "남" },
							{ "소서노", 20, "여" } };
		
		//DefaultTableModel tm = new DefaultTableModel(columnNames, 0);		// 빈 테이블 작성시
		DefaultTableModel tm = new DefaultTableModel(data, columnNames);	// 초기값을 갖는 테이블 작성시
		/*****************************************************************************/

		mTable = new JTable(tm);
		
		mTable.setRowHeight(30);
		mTable.setCellSelectionEnabled(false);
		mTable.setRowSelectionAllowed(true);
		mTable.setAutoCreateRowSorter(true);
		
		panel.add(new JScrollPane(mTable));

		// 표 정보 표시
		System.out.println("컬럼 수 : " + tm.getColumnCount());
		System.out.println("세번째 컬럼 명 : " + tm.getColumnName(2));
		System.out.println("줄 수 : " + tm.getRowCount());

		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 4));
		
		JButton button = new JButton("셀내용 삽입");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel dtm = (DefaultTableModel)mTable.getModel();
						
						dtm.setValueAt("여성", 1, 2);
					}
					
				}
		);
		panel.add(button);
		
		button = new JButton("열(row) 추가");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel dtm = (DefaultTableModel)mTable.getModel();
						
						String[] rowData = { "홍길동", "20", "남" };
						dtm.addRow(rowData);
					}
				}
		);
		panel.add(button);
		
		button = new JButton("열(row) 삽입");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel dtm = (DefaultTableModel)mTable.getModel();
						
						String[] rowData = { "일지매", "18", "남" };
						dtm.insertRow(1, rowData);
					}
				}
		);
		panel.add(button);
		
		button = new JButton("셀 서식");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 2번 컬럼에 대해
						TableColumnModel tcm = mTable.getColumnModel();
						TableColumn tc = tcm.getColumn(2);

						// 셀 너비 설정
						tc.setPreferredWidth(20);

						// 셀 정렬 방법 설정
						DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
						dtcr.setHorizontalAlignment(SwingConstants.CENTER);

						tc.setCellRenderer(dtcr);
					}
				}
		);
		panel.add(button);

		/*/
		button = new JButton("정렬");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel tm = (DefaultTableModel)mTable.getModel();

						TableRowSorter<DefaultTableModel> sorter;
						sorter = new TableRowSorter<DefaultTableModel>(tm);
						mTable.setRowSorter(sorter);

						java.util.List <RowSorter.SortKey> sortKeys = new java.util.ArrayList<RowSorter.SortKey>();
						sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
						sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
						sorter.setSortKeys(sortKeys); 
					}
				}
		);
		panel.add(button);
		//*/

		return panel;
	}
    
    private void registerTableSelectionEventListener() {
    	
    	mTable.getSelectionModel().addListSelectionListener(
    	        new ListSelectionListener() {
    	        	
    	        	@Override
    	            public void valueChanged(ListSelectionEvent event) {
    	                int viewRow = mTable.getSelectedRow();
    	                
    	                if (viewRow < 0) {
    	                    System.out.println("Selection got filtered away");
    	                }
    	                else {
    	                    int modelRow = mTable.convertRowIndexToModel(viewRow);
    	                    
    	                    System.out.printf("Selected Row in view: %d. ", viewRow);
    	                    System.out.printf("Selected Row in model: %d.\n", modelRow);
    	                }
    	            }
    	        	
    	        }
    	);
    }
	
	public static void main(String[] args) {
		new TableBasicRenderer();
	}

}
