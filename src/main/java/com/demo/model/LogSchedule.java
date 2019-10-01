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
@Table(name="time_log_schedules",uniqueConstraints = @UniqueConstraint(columnNames = {"time_group_id","date_start","date_end"}))
public class LogSchedule extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "date_start")
	private Date dateStart;
	
	@Column(name = "date_end")
	private Date dateEnd;
	
	@JoinColumn(name = "time_group_id", referencedColumnName = "id")
	@OneToOne
	private TimeGroup timeGroup;

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public TimeGroup getTimeGroup() {
		return timeGroup;
	}

	public void setTimeGroup(TimeGroup timeGroup) {
		if(timeGroup==null) {
			this.timeGroup = new TimeGroup();
		}else {
			this.timeGroup = timeGroup;	
		}
	}
}
