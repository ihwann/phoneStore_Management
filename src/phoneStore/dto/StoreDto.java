package phoneStore.dto;

public class StoreDto {
	private String storeid;
	private String storeadd;
	private String storelevel;
	private String storetype;
	private String con_yn;
	
	
	public StoreDto() {
		
	}
	
	public StoreDto(String storeid, String storeadd, String storelevel, String storetype) {
		super();
		this.storeid = storeid;
		this.storeadd = storeadd;
		this.storelevel = storelevel;
		this.storetype = storetype;
	}

	public void setall(String storeid, String storeadd, String storelevel, String storetype) {
		this.storeid = storeid;
		this.storeadd = storeadd;
		this.storelevel = storelevel;
		this.storetype = storetype;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getStoreadd() {
		return storeadd;
	}

	public void setStoreadd(String storeadd) {
		this.storeadd = storeadd;
	}

	public String getStorelevel() {
		return storelevel;
	}

	public void setStorelevel(String storelevel) {
		this.storelevel = storelevel;
	}

	public String getStoretype() {
		return storetype;
	}

	public void setStoretype(String storetype) {
		this.storetype = storetype;
	}

	public String getCon_yn() {
		return con_yn;
	}

	public void setCon_yn(String con_yn) {
		this.con_yn = con_yn;
	}

	
	
}
