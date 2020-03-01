// SiteCategory.java
package FinalProject;

import java.util.Objects;
import java.util.Vector;

public class SiteCategory {

	private String category;

	public SiteCategory(String category) {
		this.category = category;
	}

	public String getSiteCategory() {
		return category;
	}

	public String toString() { //
		return category;
	}

	// equals : �� ��ü����(���� �� ��ü�� ���ڷ� �Ѿ�� obj)�� ���ٴ� ���� �˷��ֱ� ����, true��ȯ��
	// hashcode : equals���� �� ��ü�� ���ٰ� treu��ȯ�� hashCode�� �� ��ü���� �׻� ���� ���� ��ȯ
	/*
	@Override
	public int hashCode() {
		return Objects.hashCode(category); // category�� �ؽ��� ��ȯ -> category�� �ٸ��ٸ� �׻� �ٸ��� ��ȯ
	}

	@Override
	public boolean equals(Object obj) {
		SiteCategory sc = (SiteCategory) obj;
		return sc.category == this.category; // category�� ���ؼ� ������ true, �ٸ��� false ��ȯ
	}
*/
}
