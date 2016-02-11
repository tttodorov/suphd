package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EKATTE")
public class EKATTE {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "isActive")
	private Integer isActive;
	
	@Column(name = "EKATTEId")
	private Integer mesId;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "municipality")
	private String mesName;
	
	@Column(name = "region")
	private String region;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		if(isActive == null) {
			isActive = 1;
		}
		this.isActive = isActive;
	}

	public Integer getMesId() {
		return mesId;
	}

	public void setMesId(Integer eKATTEId) {
		mesId = eKATTEId;
	}

	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMesName() {
		return mesName;
	}

	public void setMesName(String municipality) {
		this.mesName = municipality;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof EKATTE){
			EKATTE userPhdReport = (EKATTE) obj;
			return userPhdReport.getId() == getId();
		}
		
		return false;
	}

	@Override
	public String toString() {
		return String.format("Id: %d; IsActive: %d; mesId: %d; City: %s; Municipality: %s; Region: %s", getId(),
				getIsActive(), getMesId(), getCity(), getMesName(), getRegion());
	}
}