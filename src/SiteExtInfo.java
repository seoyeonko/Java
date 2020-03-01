
public class SiteExtInfo {
	private String list_search;
	private String searchField;
	private String sort_categ;
	private String sort_name;

	public SiteExtInfo(String list_search, String searchField, String sort_categ, String sort_name) {
		this.list_search = list_search;
		this.searchField = searchField;
		this.sort_categ = sort_categ;
		this.sort_name = sort_name;
	}

	public String getList_search() {
		return list_search;
	}

	public void setList_search(String list_search) {
		this.list_search = list_search;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSort_categ() {
		return sort_categ;
	}

	public void setSort_categ(String sort_categ) {
		this.sort_categ = sort_categ;
	}

	public String getSort_name() {
		return sort_name;
	}

	public void setSort_name(String sort_name) {
		this.sort_name = sort_name;
	}
}
