package com.demo.model;

import java.sql.Date;
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
@Table(
		name = "time_request_raw_timesheets", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "employee_id", "date"}
				)
		)
public class RequestRawTimesheet extends BaseEntity {
	public static final long serialVersionUID = 1L;
	
	@OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
	
	@OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;
	
	@OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
	
	@OneToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private WorksheetActivity activity;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "time_in")
	private Timestamp timeIn;
	
	@Column(name = "time_out")
	private Timestamp timeOut;
	
	@Column(name = "value_1")
	private Integer value1;
	
	@Column(name = "value_2")
	private Integer value2;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "description_2")
	private String description2;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "time_in_lat")
	private Double timeInLat;
	
	@Column(name = "time_in_long")
	private Double timeInLong;
	
	@Column(name = "time_out_lat")
	private Double timeOutLat;
	
	@Column(name = "time_out_long")
	private Double timeOutLong;
	
	@Column(name = "timezone")
	private String timezone;

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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		if(project == null) {
			this.project = new Project();
		} else {
			this.project = project;
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

	public WorksheetActivity getActivity() {
		return activity;
	}

	public void setActivity(WorksheetActivity activity) {
		if(activity == null) {
			this.activity = new WorksheetActivity();
		} else {
			this.activity = activity;
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Timestamp timeIn) {
		this.timeIn = timeIn;
	}

	public Timestamp getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Timestamp timeOut) {
		this.timeOut = timeOut;
	}

	public Integer getValue1() {
		return value1;
	}

	public void setValue1(Integer value1) {
		if(value1 == null) {
			this.value1 = 0;
		} else {
			this.value1 = value1;
		}
	}

	public Integer getValue2() {
		return value2;
	}

	public void setValue2(Integer value2) {
		if(value2 == null) {
			this.value2 = 0;
		} else {
			this.value2 = value2;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description.isEmpty()) {
			this.description = new String();
		} else {
			this.description = description;
		}
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		if(description2.isEmpty()) {
			this.description2 = new String();
		} else {
			this.description2 = description2;
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if(status.isEmpty()) {
			this.status = new String();
		} else {
			this.status = status;
		}
	}

	public Double getTimeInLat() {
		return timeInLat;
	}

	public void setTimeInLat(Double timeInLat) {
		if(timeInLat == null) {
			this.timeInLat = 0D;
		} else {
			this.timeInLat = timeInLat;
		}
	}

	public Double getTimeInLong() {
		return timeInLong;
	}

	public void setTimeInLong(Double timeInLong) {
		if(timeInLong == null) {
			this.timeInLong = 0D;
		} else {
			this.timeInLong = timeInLong;
		}
	}

	public Double getTimeOutLat() {
		return timeOutLat;
	}

	public void setTimeOutLat(Double timeOutLat) {
		if(timeOutLat == null) {
			this.timeOutLat = 0D;
		} else {
			this.timeOutLat = timeOutLat;
		}
	}

	public Double getTimeOutLong() {
		return timeOutLong;
	}

	public void setTimeOutLong(Double timeOutLong) {
		if(timeOutLong == null) {
			this.timeOutLong = 0D;
		} else {
			this.timeOutLong = timeOutLong;
		}
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		if(timezone.isEmpty()) {
			this.timezone = new String();
		} else {
			this.timezone = timezone;
		}
	}

}
