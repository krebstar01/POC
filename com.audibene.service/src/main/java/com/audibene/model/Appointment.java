package com.audibene.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;

import com.audibene.model.adapter.LocalDateTimeAdapter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="appointment", schema="public", uniqueConstraints = {@UniqueConstraint(columnNames = "APPOINTMENT_ID"), @UniqueConstraint(columnNames = "APPOINTMENT_IDENTIFIER")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment {

	private int id;
	private Audiologist audiologist;
	private Customer customer;
	private LocalDateTime appointmentDate;
	private Rating rating;
	private String appointmentIdentifier;
	private Integer audiologistId;
	private Integer customerId;

	@Id
	@Column(name = "APPOINTMENT_ID", nullable = false, columnDefinition = "INTEGER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "APPOINTMENT_DATE")
	@Type(type="org.hibernate.type.LocalDateTimeType")
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	@Column(name = "RATING", nullable = true)
	@Enumerated(EnumType.STRING)
	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	@JsonIgnore
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = Audiologist.class)
	@JoinColumn(name = "AUDIOLOGIST_ID",updatable = false, insertable = false)
	public Audiologist getAudiologist() {
		return audiologist;
	}

	public void setAudiologist(Audiologist audiologist) {
		this.audiologist = audiologist;
	}

	@JsonIgnore
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER , targetEntity = Customer.class)
	@JoinColumn(name = "CUSTOMER_ID",updatable = false, insertable = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@JsonIgnore
	@Column(name = "AUDIOLOGIST_ID")
	public Integer getAudiologistId() {
		return audiologistId;
	}

	public void setAudiologistId(Integer audiologistId) {
		this.audiologistId = audiologistId;
	}
	
	@JsonIgnore
	@Column(name = "CUSTOMER_ID")
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "APPOINTMENT_IDENTIFIER", nullable = true, unique=true)
	public String getAppointmentIdentifier() {
		return appointmentIdentifier;
	}

	public void setAppointmentIdentifier(String appointmentIdentifier) {
		this.appointmentIdentifier = appointmentIdentifier;
	}
	
}
