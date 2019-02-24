package com.nazdaq.srvm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "applicant")
public class Applicant implements Serializable {
	
	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "applicant_type")
	private Integer applicantType;

	@ManyToOne
	@JoinColumn(name="bank_app_id",nullable=true)
	private BankApplicant bankApplicant;
	
	@ManyToOne
	@JoinColumn(name="nbfi_app_id",nullable=true)
	private NbfiApplicant nbfiApplicant;
	
	@ManyToOne
	@JoinColumn(name="customer_app_id",nullable=true)
	private CustomerApplicant customerApplicant;
	
	@Column(name = "property_type")
	private Integer propertyType;
	
	@ManyToOne
	@JoinColumn(name="land_id",nullable=true)
	private Land land;
	
	@ManyToOne
	@JoinColumn(name="building_id",nullable=true)
	private Building building;
	
	@ManyToOne
	@JoinColumn(name="flat_id",nullable=true)
	private Flat flat;
	
	@ManyToOne
	@JoinColumn(name="machinary_id",nullable=true)
	private Machinary machinary;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(Integer applicantType) {
		this.applicantType = applicantType;
	}

	public BankApplicant getBankApplicant() {
		return bankApplicant;
	}

	public void setBankApplicant(BankApplicant bankApplicant) {
		this.bankApplicant = bankApplicant;
	}

	public NbfiApplicant getNbfiApplicant() {
		return nbfiApplicant;
	}

	public void setNbfiApplicant(NbfiApplicant nbfiApplicant) {
		this.nbfiApplicant = nbfiApplicant;
	}

	public CustomerApplicant getCustomerApplicant() {
		return customerApplicant;
	}

	public void setCustomerApplicant(CustomerApplicant customerApplicant) {
		this.customerApplicant = customerApplicant;
	}

	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public Machinary getMachinary() {
		return machinary;
	}

	public void setMachinary(Machinary machinary) {
		this.machinary = machinary;
	}

	
	
	
	
}
