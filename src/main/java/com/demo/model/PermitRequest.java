package com.demo.model;

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
@Table(
		name = "time_permit_requests", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "lov_permit"}
				)
		)
public class PermitRequest extends BaseEntity {
	public static final long serialVersionUID = 1L;
	
	@OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
	
	@OneToOne
    @JoinColumn(name = "lov_permit", referencedColumnName = "id")
	private Lov lovPermit;
	
	@OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
	
	@Column(name = "file_reference")
	private String fileReference;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "status")
	private String status;

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

	public Lov getLovPermit() {
		return lovPermit;
	}

	public void setLovPermit(Lov lovPermit) {
		if(lovPermit == null) {
			this.lovPermit = new Lov();
		} else {
			this.lovPermit = lovPermit;
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

	public String getFileReference() {
		return fileReference;
	}

	public void setFileReference(String fileReference) {
		if(fileReference.isEmpty()) {
			this.fileReference = new String();
		} else {
			this.fileReference = fileReference;
		}
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		if(reason.isEmpty()) {
			this.reason = new String();
		} else {
			this.reason = reason;
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

}
