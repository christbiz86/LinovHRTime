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
@Table(name="time_time_groups",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","code"}))
public class TimeGroup extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
    @OneToOne
	private Company company;
	
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="is_ignore_holiday")
	private Boolean isIgnoreHoliday;
	
	@Column(name="is_flexy_hour")
	private Boolean isFlexyHour;
	
	@Column(name="is_allow_overtime")
	private Boolean isAllowOvertime;
	
	@Column(name="is_flexy_holiday_overtime")
	private Boolean isFlexyHolidayOvertime;
	
	@Column(name="is_default")
	private Boolean isDefault;
	
	@Column(name="is_absence_as_annual_leave")
	private Boolean isAbsenceAsAnnLeave;
	
	@Column(name="ovt_rounding")
	private Time ovtRounding;
	
	@Column(name="late_tolerance")
	private Time lateTolerance;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			this.name = new String();
		} else {
			this.name = name;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null) {
			this.description = new String();
		} else {
			this.description = description;	
		}
	}

	public Boolean getIsIgnoreHoliday() {
		return isIgnoreHoliday;
	}

	public void setIsIgnoreHoliday(Boolean isIgnoreHoliday) {
		this.isIgnoreHoliday = isIgnoreHoliday;
	}

	public Boolean getIsFlexyHour() {
		return isFlexyHour;
	}

	public void setIsFlexyHour(Boolean isFlexyHour) {
		this.isFlexyHour = isFlexyHour;
	}

	public Boolean getIsAllowOvertime() {
		return isAllowOvertime;
	}

	public void setIsAllowOvertime(Boolean isAllowOvertime) {
		this.isAllowOvertime = isAllowOvertime;
	}

	public Boolean getIsFlexyHolidayOvertime() {
		return isFlexyHolidayOvertime;
	}

	public void setIsFlexyHolidayOvertime(Boolean isFlexyHolidayOvertime) {
		this.isFlexyHolidayOvertime = isFlexyHolidayOvertime;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Boolean getIsAbsenceAsAnnLeave() {
		return isAbsenceAsAnnLeave;
	}

	public void setIsAbsenceAsAnnLeave(Boolean isAbsenceAsAnnLeave) {
		this.isAbsenceAsAnnLeave = isAbsenceAsAnnLeave;
	}

	public Time getOvtRounding() {
		return ovtRounding;
	}

	public void setOvtRounding(Time ovtRounding) {
		this.ovtRounding = ovtRounding;
	}

	public Time getLateTolerance() {
		return lateTolerance;
	}

	public void setLateTolerance(Time lateTolerance) {
		this.lateTolerance = lateTolerance;
	}
}
