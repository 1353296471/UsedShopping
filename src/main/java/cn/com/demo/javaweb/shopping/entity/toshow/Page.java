package cn.com.demo.javaweb.shopping.entity.toshow;

public class Page {
	private int pageNo;
	private int maxPage;
	private int pageSize;
	private int index;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getPageNo() {
		if (pageNo > maxPage) {
			pageNo = maxPage;
		}
		return pageNo;
	}

	public int getFristPage() {
		return 1;
	}

	public int getLastPage() {
		maxPage = pageNo - 1;
		if (maxPage < 1) {
			maxPage = 1;
		}
		return maxPage;
	}

	public int getBeforePage() {
		int beforePage = pageNo - 1;
		if (beforePage < 1) {
			beforePage = 1;
		}
		return beforePage;
	}

	public int getNextPage() {
		int nextPage = pageNo + 1;
		if (nextPage > maxPage) {
			nextPage = maxPage;
		}
		return nextPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setMaxPage(int maxPage) {
		if (maxPage <= 0) {
			maxPage = 1;
		}
		this.maxPage = maxPage;
	}

	public Page(int pageNo, int maxPage) {
		super();
		this.pageNo = pageNo;
		if (maxPage <= 0) {
			maxPage = 1;
		}
		this.maxPage = maxPage;
	}

	public Page(int pageNo, int maxPage, int pageSize) {
		super();
		this.pageNo = pageNo;
		if (maxPage <= 0) {
			maxPage = 1;
		}
		this.maxPage = maxPage;
		this.pageSize = pageSize;
		this.index = (pageNo - 1) * pageSize;
	}

	public Page() {
		// TODO 自动生成的构造函数存根
	}

}
