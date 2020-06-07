package rs.ac.uns.ftn.weplayserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.weplayserver.model.Address;

@Data
@NoArgsConstructor
public class AddressDTO {

	private String street;

    private String city;

    private String country;

    private String postalCode;
    
    private double lat;
    
    private double lon;
    
    public AddressDTO(Address adr) {
    	this.city = adr.getCity();
    	this.street = adr.getStreet();
    	this.country = adr.getCountry();
    	this.postalCode = adr.getPostalCode();
    	this.lat = adr.getLat();
    	this.lon = adr.getLon();
    }
}
