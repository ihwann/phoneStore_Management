package phoneStore.dto;

public class PhoneItemDto {
	String phone_model_id;
	String phone_name;
	int phone_lease_price;
	
	public PhoneItemDto() {
		
	} // 무조건 파라미터가 있어야 객체가 생성되는 경우를 방지하기 위해
	
	public PhoneItemDto(String phone_model_id, String phone_name, int phone_lease_price) {
		super();
		this.phone_model_id = phone_model_id;
		this.phone_name = phone_name;
		this.phone_lease_price = phone_lease_price;
	}

	public String getPhone_model_id() {
		return phone_model_id;
	}

	public void setPhone_model_id(String phone_model_id) {
		this.phone_model_id = phone_model_id;
	}

	public String getPhone_name() {
		return phone_name;
	}

	public void setPhone_name(String phone_name) {
		this.phone_name = phone_name;
	}

	public int getPhone_lease_price() {
		return phone_lease_price;
	}

	public void setPhone_lease_price(int phone_lease_price) {
		this.phone_lease_price = phone_lease_price;
	}
	
}
