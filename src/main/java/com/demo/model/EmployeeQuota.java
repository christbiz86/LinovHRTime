package com.demo.model;

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
	private Float maxQuota;
	
	@Column(name = "carried_quota")
	private Integer carriedQuota;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Float getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(Float maxQuota) {
		this.maxQuota = maxQuota;
	}

	public Integer getCarriedQuota() {
		return carriedQuota;
	}

	public void setCarriedQuota(Integer carriedQuota) {
		this.carriedQuota = carriedQuota;
	}

}
