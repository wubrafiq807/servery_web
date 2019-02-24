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
import javax.persistence.Transient;

@Entity
@Table(name = "branch_nbfi")
public class BranchNbfi extends CommonModel implements Serializable {
	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name="nbfi_id",nullable=false)
	private Nbfi nbfi;
	
	@Transient
	private Integer nbfiId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Nbfi getNbfi() {
		return nbfi;
	}
	public void setNbfi(Nbfi nbfi) {
		this.nbfi = nbfi;
	}
	public Integer getNbfiId() {
		return nbfiId;
	}
	public void setNbfiId(Integer nbfiId) {
		this.nbfiId = nbfiId;
	}
	
	
	

}
