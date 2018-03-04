package com.foodie.sf.util;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jersey.api.client.GenericType;

/** Bean to deserialize JSON Food truck results **/

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class FoodTruck implements Serializable{

	private static final long serialVersionUID = 7526471155622776147L;

	public static GenericType<List<FoodTruck>> LIST_DATATYPE = new GenericType<List<FoodTruck>>(){};

	@JsonProperty
	private String applicant;
	@JsonProperty
	private String start24;
	@JsonProperty
	private String end24;
	@JsonProperty
	private String dayofweekstr;

	@JsonIgnore
	private int startHour;
	@JsonIgnore
	private int endHour;

	@JsonProperty
	private String location;

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getStart24() {
		return start24;
	}

	public void setStart24(String start24) {
		this.start24 = start24;
		setStartHour();
	}

	public String getEnd24() {
		return end24;
	}

	public void setEnd24(String end24) {
		this.end24 = end24;
		setEndHour();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDayofweekstr() {
		return dayofweekstr;
	}

	public void setDayofweekstr(String dayofweekstr) {
		this.dayofweekstr = dayofweekstr;
	}


	public int getStartHour() {
		return startHour;
	}

	public void setStartHour() {
		int startHour = Integer.valueOf(start24.substring(0,2));
		this.startHour = startHour;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour() {
		int endHour = Integer.valueOf(end24.substring(0,2));
		this.endHour = endHour;
	}

	@Override
	public String toString(){
		StringJoiner sj = new StringJoiner("  |  ");
		sj.add(getApplicant());
		sj.add(getLocation());
		return sj.toString();

	}

}
