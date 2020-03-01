// SiteCategoryList.java
package FinalProject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

public class SiteCategoryList {

	private HashSet<SiteCategory> items;

	public SiteCategoryList() {
		items = new HashSet<SiteCategory>();
//		items.add(new SiteCategory("학교"));
//		items.add(new SiteCategory("정보"));
//		items.add(new SiteCategory("포털"));
//
//		Iterator it = items.iterator();
//		while(it.hasNext()) {
//			SiteCategory s = (SiteCategory) it.next();
//			System.out.println(s.getSiteCategory());
//		}
	}

	public HashSet<SiteCategory> getList() {
		return items;
	}

	public void addList(SiteCategory s) {
		items.add(s);
	}

	public void removeList(SiteCategory s) {
		items.remove(s);
	}

	public Vector<SiteCategory> toVector() {
		Vector<SiteCategory> v = new Vector<SiteCategory>();
		Iterator it = items.iterator();
		while (it.hasNext()) {
			SiteCategory s = (SiteCategory) it.next();
			v.add(s);
//			System.out.println(s.getSiteCategory());
		}
		return v;
	}
}
