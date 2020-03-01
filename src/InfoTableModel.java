
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

// SiteInfo 기반의 셀 데이터 접근 메서드 추가.
public class InfoTableModel extends AbstractTableModel {

	private Vector<String> mColumnNames;
	private HashSet<SiteInfo> data; // SiteInfoList와 같은 자료형

	public InfoTableModel(SiteInfoList list) {
		// SiteInfoList를 매개변수로 갖는 생성자.
		String[] s = { "분류", "선호도", "사이트 이름", "사이트 주소" };
		mColumnNames = new Vector<String>(s.length);

		for (int i = 0; i < s.length; i++)
			mColumnNames.addElement(s[i]);

		data = list.getList();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return mColumnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Iterator it = data.iterator();

		int row = rowIndex;
		SiteInfo si = null;

		for (int i = 0; i <= row; i++) {
			si = (SiteInfo) (it.next());

		}

		switch (columnIndex) {

		case 0:
			return si.getCateg();
		case 1:
			return si.getPrefer();
		case 2:
			return si.getSiteName();
		case 3:
			return si.getUrl();
		}

		return null;
	}

	@Override
	public String getColumnName(int column) {
		return (String) mColumnNames.get(column);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	}

	public SiteInfo getSiteInfo(int r) {
		Iterator it = data.iterator();

		int row = r;
		SiteInfo si = null;

		for (int i = 0; i <= row; i++) {
			si = (SiteInfo) (it.next());

		}

		return si;
	}

}
