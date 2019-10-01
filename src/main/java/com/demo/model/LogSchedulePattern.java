package com.demo.model;

import java.sql.Time;

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
@Table(name="time_log_schedule_patterns",uniqueConstraints = @UniqueConstraint(columnNames = {"log_schedule_id","sequence"}))
public class LogSchedulePattern extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name="work_start")
	private Time workStart;

	@Column(name="break_start")
	private Time breakStart;

	@Column(name="sequence")
	private Integer sequence;

	@Column(name="work_duration")
	private Time workDuration;
	
	@Column(name="break_duration")
	private Time breakDuration;
	
	@JoinColumn(name = "log_schedule_id", referencedColumnName = "id")
    @OneToOne
	private LogSchedule logSchedule;
	
	@JoinColumn(name = "leave_id", referencedColumnName = "id")
    @OneToOne
	private Leave leave;

	public Time getWorkStart() {
		return workStart;
	}

	public void setWorkStart(Time workStart) {
		this.workStart = workStart;
	}

	public Time getBreakStart() {
		return breakStart;
	}

	public void setWorkEnd(Time breakStart) {
		this.breakStart = breakStart;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Time getWorkDuration() {
		return workDuration;
	}

	public void setWorkDuration(Time workDuration) {
		this.workDuration = workDuration;
	}

	public Time getBreakDuration() {
		return breakDuration;
	}

	public void setBreakDuration(Time breakDuration) {
		this.breakDuration = breakDuration;
	}

	public LogSchedule getLogSchedule() {
		return logSchedule;
	}

	public void setLogSchedule(LogSchedule logSchedule) {
		if(logSchedule==null) {
			this.logSchedule = new LogSchedule();
		}else {
			this.logSchedule = logSchedule;			
		}
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		if(leave.getId().isEmpty()) {
			this.leave = null;
		}else {
			this.leave = leave;			
		}
	}
}
