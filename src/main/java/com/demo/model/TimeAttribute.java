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
@Table(name="time_time_attributes",uniqueConstraints = @UniqueConstraint(columnNames = {"time_group_id","employee_id"}))
public class TimeAttribute extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "time_group_id", referencedColumnName = "id")
    @OneToOne
	private TimeGroup timeGroup;
	
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
    @OneToOne
	private Employee employee;
	
	@Column(name="eff_begin")
	private Date effBegin;
	
	@Column(name="eff_end")
	private Date effEnd;

	public TimeGroup getTimeGroup() {
		return timeGroup;
	}

	public void setTimeGroup(TimeGroup timeGroup) {
		if (timeGroup == null) {
			this.timeGroup = new TimeGroup();
		} else {
			this.timeGroup = timeGroup;	
		}
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		if (employee == null) {
			this.employee = new Employee();
		} else {
			this.employee = employee;	
		}
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
}
