package com.audibene.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="audiologist", schema="public", uniqueConstraints = {@UniqueConstraint(columnNames = "AUDIOLOGIST_ID"), @UniqueConstraint(columnNames = "AUDIOLOGIST_NAME")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Audiologist {

	private int id;
	private String audiologistName;

	private Set<Customer> customers;
	private Set<Appointment> appoinments;
	

    @Id
    @Column(name = "AUDIOLOGIST_ID", nullable = false, columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "AUDIOLOGIST_NAME", nullable = true, unique=true)
    public String getAudiologistName() {
		return audiologistName;
	}

	public void setAudiologistName(String audiologistName) {
		this.audiologistName = audiologistName;
	}

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Customer.class)
	@JsonIgnore
	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, targetEntity = Appointment.class)
    @JsonIgnore
	public Set<Appointment> getAppoinments() {
		return appoinments;
	}

	public void setAppoinments(Set<Appointment> appoinments) {
		this.appoinments = appoinments;
	}

}