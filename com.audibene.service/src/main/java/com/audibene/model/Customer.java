package com.audibene.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="customer", schema="public", uniqueConstraints = {@UniqueConstraint(columnNames = "CUSTOMER_ID"), @UniqueConstraint(columnNames = "CUSTOMER_NAME")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

	private int id;
	private String customerName;
	private Set<Appointment> appoinments;
	private Audiologist audiologist;
	private Integer audiologistId;

	@Id
	@Column(name = "CUSTOMER_ID", nullable = false, columnDefinition = "INTEGER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "CUSTOMER_NAME", nullable = true, unique=true)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Appointment.class)
	@JsonIgnore
	public Set<Appointment> getAppoinments() {
		return appoinments;
	}

	public void setAppoinments(Set<Appointment> appoinments) {
		this.appoinments = appoinments;
	}
	
	@JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "AUDIOLOGIST_ID", referencedColumnName = "AUDIOLOGIST_ID", updatable = false, insertable = false)
	public Audiologist getAudiologist() {
		return audiologist;
	}

	public void setAudiologist(Audiologist audiologist) {
		this.audiologist = audiologist;
	}

	@JsonIgnore
	@Column(name = "AUDIOLOGIST_ID")
	public Integer getAudiologistId() {
		return audiologistId;
	}

	public void setAudiologistId(Integer audiologistId) {
		this.audiologistId = audiologistId;
	}
	

}