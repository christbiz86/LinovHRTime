package com.demo.model;

import java.sql.Date;

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
		name = "time_employee_quotas", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"employee_id","leave_id"}
				)
		)
public class EmployeeQuota extends BaseEntity {
	public static final long serialVersionUID = 1L;
	
	@OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
	
	@OneToOne
    @JoinColumn(name = "leave_id", referencedColumnName = "id")
    private Leave leave;
	
	@OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
	
	@Column(name = "max_quota")
	private Double maxQuota;
	
	@Column(name = "carried_quota")
	private Integer carriedQuota;
	
	@Column(name = "is_active")
	private Boolean isActive = true;
	
	@Column(name = "eff_begin")
	private Date effBegin;
	
	@Column(name = "eff_end")
	private Date effEnd;
    
    public Boolean getIsActive() {
		return isActive;
	}

	public Date getEffBegin() {
		return effBegin;
	}

	public void setEffBegin(Date effBegin) {
		this.effBegin = effBegin;
	}

	public Date getEffEnd() {
		return effEnd;
	}

	public void setEffEnd(Date effEnd) {
		this.effEnd = effEnd;
	}

	public void setIsActive(Boolean isActive) {
		if(isActive == null) {
			this.isActive = true;
		} else {
			this.isActive = isActive;
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

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		if(leave == null) {
			this.leave = new Leave();
		} else {
			this.leave = leave;
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

	public Double getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(Double maxQuota) {
		if(maxQuota == null) {
			this.maxQuota = 0D;
		} else {
			this.maxQuota = maxQuota;
		}
	}

	public Integer getCarriedQuota() {
		return carriedQuota;
	}

	public void setCarriedQuota(Integer carriedQuota) {
		if(carriedQuota == null) {
			this.carriedQuota = 0;
		} else {
			this.carriedQuota = carriedQuota;
		}
	}

}
