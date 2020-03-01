
import java.util.HashSet;
import java.util.Iterator;

public class SiteInfoList{
	
	private HashSet<SiteInfo> infos;
	
	public SiteInfoList() {
		infos = new HashSet<SiteInfo>();
	}
	
	public HashSet<SiteInfo> getList(){
		return infos;
	}
	
	public void addList(SiteInfo f) {
		infos.add(f);
	}
	
	public void removeList(SiteInfo f) {
		infos.remove(f);
	}
	
	// site 개수 리턴
	public int getSize() {
		Iterator it = infos.iterator();
		int n = 0;
		
		while(it.hasNext()) {
			n++;
		}
		
		return n;
	}
}
