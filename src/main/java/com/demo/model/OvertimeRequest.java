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
@Table(name="time_overtime_requests",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","schedule_date"}))
public class OvertimeRequest extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "schedule_date")
	private Date scheduleDate;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "time_start")
	private Timestamp timeStart;	
	
	@Column(name = "time_end")
	private Timestamp timeEnd;
	
	@Column(name = "file_reference")
	private String fileReference;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	@OneToOne
	private Employee employee;

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		if(scheduleDate==null) {
			this.scheduleDate = new Date();
		}else {
			this.scheduleDate = scheduleDate;			
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if(status==null) {
			this.status = new String();
		}else {
			this.status = status;			
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

	public String getFileReference() {
		return fileReference;
	}

	public void setFileReference(String fileReference) {
		if(fileReference==null) {
			this.fileReference = new String();
		}else {
			this.fileReference = fileReference;			
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
}