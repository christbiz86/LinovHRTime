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
@Table(name="time_time_group_schedules",uniqueConstraints = @UniqueConstraint(columnNames = {"time_group_id","code"}))
public class ScheduleException extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "leave_id", referencedColumnName = "id")
    @OneToOne
	private Leave leave;
	
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
    @OneToOne
	private Employee employee;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="time_in")
	private Timestamp timeIn;
	
	@Column(name="time_out")
	private Timestamp timeOut;
	
	@Column(name="break_start")
	private Timestamp breakStart;
	
	@Column(name="break_end")
	private Timestamp breakEnd;
	
	@Column(name="reason")
	private String reason;

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		if (leave == null) {
			this.leave = new Leave();
		} else {
			this.leave = leave;	
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

	public Timestamp getBreakStart() {
		return breakStart;
	}

	public void setBreakStart(Timestamp breakStart) {
		this.breakStart = breakStart;
	}

	public Timestamp getBreakEnd() {
		return breakEnd;
	}

	public void setBreakEnd(Timestamp breakEnd) {
		this.breakEnd = breakEnd;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		if (reason == null) {
			this.reason = new String();
		} else {
			this.reason = reason;	
		}
	}
}
