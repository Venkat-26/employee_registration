package com.example.emp_regtrn.dto;



import java.math.BigDecimal;
import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;
//import org.hibernate.annotations.processing.Pattern;

import com.example.emp_regtrn.entity.Department;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;




public class EmployeeRequest {

	@NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    private LocalDate dob;

    @NotBlank
    @Size(max = 250)
    private String address;

    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal salary;

    private Department department;

    @NotBlank
    @Pattern(regexp = "^\\d{6}$")
    private String pincode;

    @NotBlank
    private String postOffice;

    private String city;
    private String state;
    private String country;

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
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

	@Override
	public String toString() {
		return "EmployeeRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", dob="
				+ dob + ", address=" + address + ", salary=" + salary + ", department=" + department + ", pincode="
				+ pincode + ", postOffice=" + postOffice + ", city=" + city + ", state=" + state + ", country="
				+ country + "]";
	}

   
}
