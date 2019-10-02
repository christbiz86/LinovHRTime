package com.demo.model;

import java.sql.Timestamp;
import java.util.Date;

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
@Table(name = "time_raw_timesheets", uniqueConstraints = @UniqueConstraint(columnNames = {"date", "clock_time", "company_id", "employee_id"}))
public class RawTimesheet extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "is_analyzed")
	private Boolean isAnalyzed;
	
	@Column(name = "clock_time")
	private Timestamp clockTime;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "clock_time_lat")
	private Double clockTimeLat;
	
	@Column(name = "clock_time_long")
	private Double clockTimeLong;
	
	@Column(name = "source_type")
	private String sourceType;
	
	@Column(name = "machine_id")
	private String machineId;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	@OneToOne
	private Employee employee;
	
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	@OneToOne
	private Project project;
	
	@JoinColumn(name = "worksheet_id", referencedColumnName = "id")
	@OneToOne
	private Worksheet worksheet;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		if(date == null) {
			this.date = new Date();
		} else {
			this.date = date;
		}
	}

	public Boolean getIsAnalyzed() {
		return isAnalyzed;
	}

	public void setIsAnalyzed(Boolean isAnalyzed) {
		this.isAnalyzed = isAnalyzed;
	}

	public Timestamp getClockTime() {
		return clockTime;
	}

	public void setClockTime(Timestamp clockTime) {
		this.clockTime = clockTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(type == null) {
			this.type = new String();
		} else {
			this.type = type;
		}
	}

	public Double getClockTimeLat() {
		return clockTimeLat;
	}

	public void setClockTimeLat(Double clockTimeLat) {
		this.clockTimeLat = clockTimeLat;
	}

	public Double getClockTimeLong() {
		return clockTimeLong;
	}

	public void setClockTimeLong(Double clockTimeLong) {
		this.clockTimeLong = clockTimeLong;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		if(employee == null) {
			this.employee = new Employee();
		} else {
			this.employee = employee;
		}
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Worksheet getWorksheet() {
		return worksheet;
	}

	public void setWorksheet(Worksheet worksheet) {
		this.worksheet = worksheet;
	}
}
