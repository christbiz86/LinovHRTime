package com.demo.model;

import java.sql.Time;
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
@Table(name="time_timesheets",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","employee_id","date"}))
public class Timesheet extends BaseEntity {
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
    
    @JoinColumn(name = "time_group_id", referencedColumnName = "id")
    @OneToOne
    private TimeGroup timeGroup;
    
    @Column(name = "attendance_codes")
    private String attendanceCodes;
    
    @Column(name = "date")
    private Date date;
    
    @Column(name = "schedule_time_in")
    private Timestamp scheduleTimeIn;
    
    @Column(name = "time_in")
    private Timestamp timeIn;
    
    @Column(name = "time_in_deviation")
    private Time timeInDeviation;
    
    @Column(name = "schedule_time_out")
    private Timestamp scheduleTimeOut;
    
    @Column(name = "time_out")
    private Timestamp timeOut;
    
    @Column(name = "time_out_deviation")
    private Time timeOutDeviation;
    
    @Column(name = "schedule_duration")
    private Time scheduleDuration;
    
    @Column(name = "duration")
    private Time duration;
    
    @Column(name = "duration_deviation")
    private Time durationDeviation;
    
    @Column(name = "leave_weight")
    private Double leaveWeight;
    
    @Column(name = "overtime")
    private Time overtime;
    
    @Column(name = "process_code")
    private Time processCode;
    
    @Column(name = "is_work_day")
    private Boolean isWorkDay;
    
    @Column(name = "is_flexy_hour")
    private Boolean isFlexyHour;
    
    @Column(name = "value_1")
    private Integer value1;
    
    @Column(name = "value_2")
    private Integer value2;
    
    @Column(name = "time_out_lat")
    private Double timeOutLat;
    
    @Column(name = "time_out_long")
    private Double timeOutLong;
    
    @Column(name = "time_in_lat")
    private Double timeInLat;
    
    @Column(name = "time_in_long")
    private Double timeInLong;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if (company == null) {
			this.company = new Company();
		} else {
			this.company = company;
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

	public String getAttendanceCodes() {
		return attendanceCodes;
	}

	public void setAttendanceCodes(String attendanceCodes) {
		if (attendanceCodes == null) {
			this.attendanceCodes = new String();
		} else {
			this.attendanceCodes = attendanceCodes;	
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getScheduleTimeIn() {
		return scheduleTimeIn;
	}

	public void setScheduleTimeIn(Timestamp scheduleTimeIn) {
		this.scheduleTimeIn = scheduleTimeIn;
	}

	public Timestamp getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Timestamp timeIn) {
		this.timeIn = timeIn;
	}

	public Time getTimeInDeviation() {
		return timeInDeviation;
	}

	public void setTimeInDeviation(Time timeInDeviation) {
		this.timeInDeviation = timeInDeviation;
	}

	public Timestamp getScheduleTimeOut() {
		return scheduleTimeOut;
	}

	public void setScheduleTimeOut(Timestamp scheduleTimeOut) {
		this.scheduleTimeOut = scheduleTimeOut;
	}

	public Timestamp getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Timestamp timeOut) {
		this.timeOut = timeOut;
	}

	public Time getTimeOutDeviation() {
		return timeOutDeviation;
	}

	public void setTimeOutDeviation(Time timeOutDeviation) {
		this.timeOutDeviation = timeOutDeviation;
	}

	public Time getScheduleDuration() {
		return scheduleDuration;
	}

	public void setScheduleDuration(Time scheduleDuration) {
		this.scheduleDuration = scheduleDuration;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public Time getDurationDeviation() {
		return durationDeviation;
	}

	public void setDurationDeviation(Time durationDeviation) {
		this.durationDeviation = durationDeviation;
	}

	public Double getLeaveWeight() {
		return leaveWeight;
	}

	public void setLeaveWeight(Double leaveWeight) {
		if (leaveWeight == null) {
			this.leaveWeight = 0D;
		} else {
			this.leaveWeight = leaveWeight;	
		}
	}

	public Time getOvertime() {
		return overtime;
	}

	public void setOvertime(Time overtime) {
		this.overtime = overtime;
	}

	public Time getProcessCode() {
		return processCode;
	}

	public void setProcessCode(Time processCode) {
		this.processCode = processCode;
	}

	public Boolean getIsWorkDay() {
		return isWorkDay;
	}

	public void setIsWorkDay(Boolean isWorkDay) {
		this.isWorkDay = isWorkDay;
	}

	public Boolean getIsFlexyHour() {
		return isFlexyHour;
	}

	public void setIsFlexyHour(Boolean isFlexyHour) {
		this.isFlexyHour = isFlexyHour;
	}

	public Integer getValue1() {
		return value1;
	}

	public void setValue1(Integer value1) {
		if (value1 == null) {
			this.value1 = 0;
		} else {
			this.value1 = value1;
		}
	}

	public Integer getValue2() {
		return value2;
	}

	public void setValue2(Integer value2) {
		if (value2 == null) {
			this.value2 = 0;
		} else {
			this.value2 = value2;	
		}
	}

	public Double getTimeOutLat() {
		return timeOutLat;
	}

	public void setTimeOutLat(Double timeOutLat) {
		if (timeOutLat == null) {
			this.timeOutLat = 0D;
		} else {
			this.timeOutLat = timeOutLat;
		}
	}

	public Double getTimeOutLong() {
		return timeOutLong;
	}

	public void setTimeOutLong(Double timeOutLong) {
		if (timeOutLong == null) {
			this.timeOutLong = 0D;
		} else {
			this.timeOutLong = timeOutLong;	
		}
	}

	public Double getTimeInLat() {
		return timeInLat;
	}

	public void setTimeInLat(Double timeInLat) {
		if (timeInLat == null) {
			this.timeInLat = 0D;
		} else {
			this.timeInLat = timeInLat;	
		}
	}

	public Double getTimeInLong() {
		return timeInLong;
	}

	public void setTimeInLong(Double timeInLong) {
		if (timeInLong == null) {
			this.timeInLong = 0D;
		} else {
			this.timeInLong = timeInLong;	
		}
	}
}
