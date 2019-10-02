package com.demo.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="time_leave_requests",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","employee_id","leave_id"}))
public class LeaveRequest extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @OneToOne
    private Company company;
    
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @OneToOne
    private Employee employee;
    
    @JoinColumn(name = "leave_id", referencedColumnName = "id")
    @OneToOne
    private Leave leave;
    
    @Column(name = "file_reference")
    private String fileReference;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "status")
    private String status;
    
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "leaveRequest", fetch = FetchType.LAZY)
	private List<LeaveRequestDetail> leaveReqDet;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	public String getFileReference() {
		return fileReference;
	}

	public void setFileReference(String fileReference) {
		this.fileReference = fileReference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<LeaveRequestDetail> getLeaveReqDet() {
		return leaveReqDet;
	}

	public void setLeaveReqDet(List<LeaveRequestDetail> leaveReqDet) {
		this.leaveReqDet = leaveReqDet;
	}
}
