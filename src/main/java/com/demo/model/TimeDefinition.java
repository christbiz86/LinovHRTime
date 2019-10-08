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
@Table(
		name = "time_time_definitions", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "code"}
				)
		)
public class TimeDefinition extends BaseEntity {
	public static final long serialVersionUID = 1L;
	
	@OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "measurement")
	private String measurement;
	
	@JoinColumn(name = "lov_tdevty", referencedColumnName = "id")
	@OneToOne
	private Lov lovTdevty;
	
	@Column(name = "attendance_codes")
	private String attendanceCode;
	
	@JoinColumn(name = "lov_tddaty", referencedColumnName = "id")
	@OneToOne
	private Lov lovTddaty;
	
	@Column(name = "minimum")
	private Time minimum;
	
	@Column(name = "maximum")
	private Time maximum;
	
	@Column(name = "is_flexy")
	private Boolean isFlexy = false;
	
	@Column(name = "is_workday")
	private Boolean isWorkday = false;
	
	@Column(name = "is_value_1")
	private Boolean isValue1 = false;
	
	@Column(name = "is_value_2")
	private Boolean isValue2 = false;
	
	@OneToOne
    @JoinColumn(name = "leave_id", referencedColumnName = "id")
    private Leave leave;
	
	@OneToOne
    @JoinColumn(name = "time_group_id", referencedColumnName = "id")
    private TimeGroup timeGroup;
	
	@Column(name = "is_active")
	private Boolean isActive = true;
    
    public Boolean getIsActive() {
		return isActive;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if(code == null) {
			this.code = new String();
		} else {
			this.code = code;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null) {
			this.name = new String();
		} else {
			this.name = name;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description == null) {
			this.description = new String();
		} else {
			this.description = description;
		}
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		if(measurement == null) {
			this.measurement = new String();
		} else {
			this.measurement = measurement;
		}
	}

	public Lov getLovTdevty() {
		return lovTdevty;
	}

	public void setLovTdevty(Lov lovTdevty) {
		if(lovTdevty == null) {
			this.lovTdevty = new Lov();
		} else {
			this.lovTdevty = lovTdevty;
		}
	}

	public String getAttendanceCode() {
		return attendanceCode;
	}

	public void setAttendanceCode(String attendanceCode) {
		if(attendanceCode == null) {
			this.attendanceCode = new String();
		} else {
			this.attendanceCode = attendanceCode;
		}
	}

	public Lov getLovTddaty() {
		return lovTddaty;
	}

	public void setLovTddaty(Lov lovTddaty) {
		if(lovTddaty == null) {
			this.lovTddaty = new Lov();
		} else {
			this.lovTddaty = lovTddaty;
		}
	}

	public Time getMinimum() {
		return minimum;
	}

	public void setMinimum(Time minimum) {
		this.minimum = minimum;
	}

	public Time getMaximum() {
		return maximum;
	}

	public void setMaximum(Time maximum) {
		this.maximum = maximum;
	}

	public Boolean getIsFlexy() {
		return isFlexy;
	}

	public void setIsFlexy(Boolean isFlexy) {
		if(isFlexy == null) {
			this.isFlexy = false;
		} else {
			this.isFlexy = isFlexy;
		}
	}

	public Boolean getIsWorkday() {
		return isWorkday;
	}

	public void setIsWorkday(Boolean isWorkday) {
		if(isWorkday == null) {
			this.isWorkday = false;
		} else {
			this.isWorkday = isWorkday;
		}
	}

	public Boolean getIsValue1() {
		return isValue1;
	}

	public void setIsValue1(Boolean isValue1) {
		if(isValue1 == null) {
			this.isValue1 = false;
		} else {
			this.isValue1 = isValue1;
		}
	}

	public Boolean getIsValue2() {
		return isValue2;
	}

	public void setIsValue2(Boolean isValue2) {
		if(isValue2 == null) {
			this.isValue2 = false;
		} else {
			this.isValue2 = isValue2;
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

	public TimeGroup getTimeGroup() {
		return timeGroup;
	}

	public void setTimeGroup(TimeGroup timeGroup) {
		if(timeGroup == null) {
			this.timeGroup = new TimeGroup();
		} else {
			this.timeGroup = timeGroup;
		}
	}

}
