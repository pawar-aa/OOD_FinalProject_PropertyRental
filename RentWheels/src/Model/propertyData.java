package Model;

import java.sql.Date;

public class propertyData {
	
    private Integer propertyID;
    private String street;
    private String unit;
    private Double upfrontAmount;
    private String status;
    private String city;
    private String state;
    private Date date;
    private String image;
    
    public propertyData(Integer propertyID, String street, String unit
            , Double upfrontAmount, String status, String city, String state,String image, Date date){
        this.propertyID = propertyID;
        this.street = street;
        this.unit = unit;
        this.upfrontAmount = upfrontAmount;
        this.status = status;
        this.city = city;
        this.state = state;
        this.date = date;
        this.image = image;
    }
    
    
    public Integer getPropertyID() {
		return propertyID;
	}


	public void setPropertyID(Integer propertyID) {
		this.propertyID = propertyID;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public Double getUpfrontAmount() {
		return upfrontAmount;
	}


	public void setUpfrontAmount(Double upfrontAmount) {
		this.upfrontAmount = upfrontAmount;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getStatus(){
        return status;
    }
    public Date getDate(){
        return date;
    }
    public String getImage(){
        return image;
    }

}
