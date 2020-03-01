package L06;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class VectorTableModel extends AbstractTableModel {
	private Vector<String> mColumnNames;
	private Vector<Person> mData;

	public VectorTableModel(Vector<Person> v) {
		// ��� ����
		String[] s = { "�̸�", "����", "��ȭ��ȣ" };
		mColumnNames = new Vector<String>(s.length);

		for (int c = 0; c < s.length; c++) {
			mColumnNames.addElement(s[c]);
		}

		// �� ������ ����
		mData = v;
	}

	@Override
	public int getColumnCount() {
		return mColumnNames.size();
	}

	@Override
	public int getRowCount() {
		return mData.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Person p = mData.get(row);

		switch (column) {
		case 0:
			return p.getName();
		case 1:
			return p.getAge();
		case 2:
			return p.getPhoneNum();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return (String) mColumnNames.get(column);
	}

}
