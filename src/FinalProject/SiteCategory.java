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

	// equals : 두 객체에서(현재 이 객체와 인자로 넘어온 obj)가 같다는 것을 알려주기 위함, true반환시
	// hashcode : equals에서 두 객체가 같다고 treu반환시 hashCode는 두 객체에서 항상 같은 값을 반환
	/*
	@Override
	public int hashCode() {
		return Objects.hashCode(category); // category의 해쉬값 반환 -> category가 다르다면 항상 다른값 반환
	}

	@Override
	public boolean equals(Object obj) {
		SiteCategory sc = (SiteCategory) obj;
		return sc.category == this.category; // category만 비교해서 같으면 true, 다르면 false 반환
	}
*/
}
