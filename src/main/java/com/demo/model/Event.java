package com.demo.model;

import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="time_events",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "name", "event_start"}))
public class Event extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "event_start")
	private Timestamp eventStart;
	
	@Column(name = "event_end")
	private Timestamp eventEnd;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "is_full_day")
	private Boolean isFullDay;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@JoinColumn(name = "leave_id", referencedColumnName = "id")
	@OneToOne
	private Leave leave;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null) {
			this.name = new String();
		} else {
			this.name = name;
		}
	}

	public Timestamp getEventStart() {
		return eventStart;
	}

	public void setEventStart(Timestamp eventStart) {
		this.eventStart = eventStart;
	}

	public Timestamp getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Timestamp eventEnd) {
		this.eventEnd = eventEnd;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description == null) {
			this.description = new String();
		} else {
			this.description = description;
		}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if(color == null) {
			this.color = new String();
		} else {
			this.color = color;
		}
	}

	public Boolean getIsFullDay() {
		return isFullDay;
	}

	public void setIsFullDay(Boolean isFullDay) {
		this.isFullDay = isFullDay;
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

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}
}
