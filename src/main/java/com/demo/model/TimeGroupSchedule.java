package com.demo.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="time_time_group_schedules",uniqueConstraints = @UniqueConstraint(columnNames = {"time_group_id","code"}))
public class TimeGroupSchedule extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="code")
	private String code;
	
	@JoinColumn(name = "time_group_id", referencedColumnName = "id")
    @OneToOne
	private TimeGroup timeGroup;
	
	@JoinColumn(name = "leave_id", referencedColumnName = "id")
    @OneToOne
	private Leave leave;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	private Date date;
	
	@Temporal(TemporalType.TIME)
	@Column(name="time_in")
	private Timestamp timeIn;
	
	@Temporal(TemporalType.TIME)
	@Column(name="time_out")
	private Timestamp timeOut;
	
	@Column(name="break_start")
	private Timestamp breakStart;
	
	@Column(name="break_end")
	private Timestamp breakEnd;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code == null) {
			this.code = new String();
		} else {
			this.code = code;	
		}
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		if (date == null) {
			this.date = new Date();	
		} else {
			this.date = date;
		}
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
}
