
public class SiteInfo {

	private String siteName;
	private String url;
	private String id;
	private String passwd;
	private String categ;
	private String prefer;
	private String memo;

	public SiteInfo(String tf_siteName, String tf_url, String tf_id, String tf_passwd, String list_categ,
			String list_prefer, String memo) {
		this.siteName = tf_siteName;
		this.url = tf_url;
		this.id = tf_id;
		this.passwd = tf_passwd;
		this.categ = list_categ;
		this.prefer = list_prefer;
		this.memo = memo;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getCateg() {
		return categ;
	}

	public void setCateg(String categ) {
		this.categ = categ;
	}

	public String getPrefer() {
		return prefer;
	}

	public void setPrefer(String prefer) {
		this.prefer = prefer;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
