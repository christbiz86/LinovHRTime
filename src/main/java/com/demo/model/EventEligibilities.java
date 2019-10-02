package com.demo.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "time_event_eligibilities", uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "event_id"}))
public class EventEligibilities implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "privilege")
	private String privilege;
	
	@JoinColumn(name = "lov_evel", referencedColumnName = "id")
	@OneToOne
	private Lov lovLevel;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	@OneToOne
	private Event event;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id == null) {
			this.id = new String();
		} else {
			this.id = id;
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if(value == null) {
			this.value = new String();
		} else {
			this.value = value;
		}
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		if(privilege == null) {
			this.privilege = new String();
		} else {
			this.privilege = privilege;
		}
	}

	public Lov getLovLevel() {
		return lovLevel;
	}

	public void setLovLevel(Lov lovLevel) {
		if(lovLevel == null) {
			this.lovLevel = new Lov();
		} else {
			this.lovLevel = lovLevel;
		}
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if(company == null) {
			this.company = new Company();
		} else {
			this.company = company;
		}
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		if(event == null) {
			this.event = new Event();
		} else {
			this.event = event;
		}
	}
}
