package com.example.emp_regtrn.dto;

import java.util.List;

public class PincodeFinalResponse {
	
	 private String city;
	    private String state;
	    private String country;
	    private List<String> postOffices;

	    public PincodeFinalResponse(String city,
	                            String state,
	                            String country,
	                            List<String> postOffices) {
	        this.city = city;
	        this.state = state;
	        this.country = country;
	        this.postOffices = postOffices;
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

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public List<String> getPostOffices() {
			return postOffices;
		}

		public void setPostOffices(List<String> postOffices) {
			this.postOffices = postOffices;
		}

	    
}
