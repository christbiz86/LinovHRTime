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
@Table(name="time_worksheets",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","employee_id","date","time_start","time_end"}))
public class Worksheet extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@Column(name = "date")
	private Date date;	
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "time_start")
	private Timestamp timeStart;	
	
	@Column(name = "time_end")
	private Timestamp timeEnd;
	
	@Column(name = "activity_value_1")
	private Integer activityValue1;	
	
	@Column(name = "activity_value_2")
	private Integer activityValue2;
	
	@Column(name = "description_2")
	private String description2;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	@OneToOne
	private Employee employee;
	
	@JoinColumn(name = "activity_id", referencedColumnName = "id")
	@OneToOne
	private WorksheetActivity activity;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		if(date==null) {
			this.date = new Date();
		}else {
			this.date = date;			
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description==null) {
			this.description = new String();
		}else {
			this.description = description;			
		}
	}

	public Timestamp getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Timestamp timeStart) {
		if(timeStart==null) {
			this.timeStart = new Timestamp(System.currentTimeMillis());
		}else {
			this.timeStart = timeStart;			
		}
	}

	public Timestamp getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Timestamp timeEnd) {
		if(timeEnd==null) {
			this.timeEnd = new Timestamp(System.currentTimeMillis());
		}else {
			this.timeEnd = timeEnd;			
		}
	}

	public Integer getActivityValue1() {
		return activityValue1;
	}

	public void setActivityValue1(Integer activityValue1) {
		if(activityValue1==null) {
			this.activityValue1 = 0;
		}else {
			this.activityValue1 = activityValue1;			
		}
	}

	public Integer getActivityValue2() {
		return activityValue2;
	}

	public void setActivityValue2(Integer activityValue2) {
		if(activityValue2==null) {
			this.activityValue2 = 0;
		}else {
			this.activityValue2 = activityValue2;			
		}
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		if(description2==null) {
			this.description2 = new String();
		}else {
			this.description2 = description2;			
		}
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if(company==null) {
			this.company =new Company();
		}else {
			this.company = company;			
		}
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		if(employee==null) {
			this.employee = new Employee();			
		}else {
			this.employee = employee;
		}
	}

	public WorksheetActivity getActivity() {
		return activity;
	}

	public void setActivity(WorksheetActivity activity) {
		if(activity==null) {
			this.activity = new WorksheetActivity();
		}else {
			this.activity = activity;			
		}
	}
}