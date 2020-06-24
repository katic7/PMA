package rs.ac.uns.ftn.weplayserver.dto;

import lombok.Data;

@Data
public class StringDTO {

	String message;
	Integer code;
	
	public StringDTO(String string, Integer code) {
		// TODO Auto-generated constructor stub
		this.message = string;
		this.code = code;
	}


}
