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
		super("���̺� �׽�Ʈ5");
		
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
		String[] columnNames = { "��  ��", "����", "����" };
		Object[][] data = { { "���ָ�", 22, "��" },
							{ "�Ҽ���", 20, "��" } };
		
		//DefaultTableModel tm = new DefaultTableModel(columnNames, 0);		// �� ���̺� �ۼ���
		DefaultTableModel tm = new DefaultTableModel(data, columnNames);	// �ʱⰪ�� ���� ���̺� �ۼ���
		/*****************************************************************************/

		mTable = new JTable(tm);
		
		mTable.setRowHeight(30);
		mTable.setCellSelectionEnabled(false);
		mTable.setRowSelectionAllowed(true);
		mTable.setAutoCreateRowSorter(true);
		
		panel.add(new JScrollPane(mTable));

		// ǥ ���� ǥ��
		System.out.println("�÷� �� : " + tm.getColumnCount());
		System.out.println("����° �÷� �� : " + tm.getColumnName(2));
		System.out.println("�� �� : " + tm.getRowCount());

		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 4));
		
		JButton button = new JButton("������ ����");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel dtm = (DefaultTableModel)mTable.getModel();
						
						dtm.setValueAt("����", 1, 2);
					}
					
				}
		);
		panel.add(button);
		
		button = new JButton("��(row) �߰�");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel dtm = (DefaultTableModel)mTable.getModel();
						
						String[] rowData = { "ȫ�浿", "20", "��" };
						dtm.addRow(rowData);
					}
				}
		);
		panel.add(button);
		
		button = new JButton("��(row) ����");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel dtm = (DefaultTableModel)mTable.getModel();
						
						String[] rowData = { "������", "18", "��" };
						dtm.insertRow(1, rowData);
					}
				}
		);
		panel.add(button);
		
		button = new JButton("�� ����");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 2�� �÷��� ����
						TableColumnModel tcm = mTable.getColumnModel();
						TableColumn tc = tcm.getColumn(2);

						// �� �ʺ� ����
						tc.setPreferredWidth(20);

						// �� ���� ��� ����
						DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
						dtcr.setHorizontalAlignment(SwingConstants.CENTER);

						tc.setCellRenderer(dtcr);
					}
				}
		);
		panel.add(button);

		/*/
		button = new JButton("����");
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
