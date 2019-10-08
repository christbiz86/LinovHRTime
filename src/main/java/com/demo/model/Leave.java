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
@Table(name = "time_leaves", uniqueConstraints = @UniqueConstraint(columnNames = { "company_id", "code" }))
public class Leave extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "max_quota")
	private Integer maxQuota;

	@Column(name = "quota_expiration")
	private Integer quotaExpiration;

	@Column(name = "day_taken_min")
	private Integer dayTakenMin;

	@Column(name = "day_taken_max")
	private Integer dayTakenMax;

	@Column(name = "carry_max")
	private Integer carryMax;

	@Column(name = "carry_expiration_day")
	private Integer carryExpirationDay;

	@JoinColumn(name = "lov_lcty", referencedColumnName = "id")
	@OneToOne
	private Lov leaveType;

	@JoinColumn(name = "lov_lcpt", referencedColumnName = "id")
	@OneToOne
	private Lov leavePart;

	@JoinColumn(name = "lov_leave", referencedColumnName = "id")
	@OneToOne
	private Lov lovLeave;

	@Column(name = "is_annual_leave")
	private Integer isAnnualLeave;

	@Column(name = "is_allow_half_day")
	private Integer isAllowHalfDay;

	@Column(name = "is_annual_leave_deductor")
	private Integer isAnnualLeaveDeductor;

	@Column(name = "is_requestable")
	private Integer isRequestAble;

	@Column(name = "is_quota_based")
	private Integer isQuotaBased;

	@Column(name = "quota_type")
	private String quotaType;

	@Column(name = "quota_value")
	private String quotaValue;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if(code.isEmpty()) {
			this.code = new String();
		} else {
			this.code = code;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.isEmpty()) {
			this.name = new String();
		} else {
			this.name = name;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description.isEmpty()) {
			this.description = new String();
		} else {
			this.description = description;
		}
	}

	public Integer getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(Integer maxQuota) {
		if(maxQuota == null) {
			this.maxQuota = 0;
		} else {
			this.maxQuota = maxQuota;
		}
	}

	public Integer getQuotaExpiration() {
		return quotaExpiration;
	}

	public void setQuotaExpiration(Integer quotaExpiration) {
		if(quotaExpiration == null) {
			this.quotaExpiration = 0;
		} else {
			this.quotaExpiration = quotaExpiration;
		}
	}

	public Integer getDayTakenMin() {
		return dayTakenMin;
	}

	public void setDayTakenMin(Integer dayTakenMin) {
		if(dayTakenMin == null) {
			this.dayTakenMin = 0;
		} else {
			this.dayTakenMin = dayTakenMin;
		}
	}

	public Integer getDayTakenMax() {
		return dayTakenMax;
	}

	public void setDayTakenMax(Integer dayTakenMax) {
		if(dayTakenMax == null) {
			this.dayTakenMax = 0;
		} else {
			this.dayTakenMax = dayTakenMax;
		}
	}

	public Integer getCarryMax() {
		return carryMax;
	}

	public void setCarryMax(Integer carryMax) {
		if(carryMax == null) {
			this.carryMax = 0;
		} else {
			this.carryMax = carryMax;
		}
	}

	public Integer getCarryExpirationDay() {
		return carryExpirationDay;
	}

	public void setCarryExpirationDay(Integer carryExpirationDay) {
		if(carryExpirationDay == null) {
			this.carryExpirationDay = 0;
		} else {
			this.carryExpirationDay = carryExpirationDay;
		}
	}

	public Lov getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Lov leaveType) {
		if(leaveType == null) {
			this.leaveType = new Lov();
		} else {
			this.leaveType = leaveType;
		}
	}

	public Lov getLeavePart() {
		return leavePart;
	}

	public void setLeavePart(Lov leavePart) {
		if(leavePart == null) {
			this.leavePart = new Lov();
		} else {
			this.leavePart = leavePart;
		}
	}

	public Integer getIsAnnualLeave() {
		return isAnnualLeave;
	}

	public void setIsAnnualLeave(Integer isAnnualLeave) {
		if(isAnnualLeave == null) {
			this.isAnnualLeave = 0;
		} else {
			this.isAnnualLeave = isAnnualLeave;
		}
	}

	public Integer getIsAllowHalfDay() {
		return isAllowHalfDay;
	}

	public void setIsAllowHalfDay(Integer isAllowHalfDay) {
		if(isAllowHalfDay == null) {
			this.isAllowHalfDay = 0;
		} else {
			this.isAllowHalfDay = isAllowHalfDay;
		}
	}

	public Integer getIsAnnualLeaveDeductor() {
		return isAnnualLeaveDeductor;
	}

	public void setIsAnnualLeaveDeductor(Integer isAnnualLeaveDeductor) {
		if(isAnnualLeaveDeductor == null) {
			this.isAnnualLeaveDeductor = 0;
		} else {
			this.isAnnualLeaveDeductor = isAnnualLeaveDeductor;
		}
	}

	public Integer getIsRequestAble() {
		return isRequestAble;
	}

	public void setIsRequestAble(Integer isRequestAble) {
		if(isRequestAble == null) {
			this.isRequestAble = 0;
		} else {
			this.isRequestAble = isRequestAble;
		}
	}

	public Integer getIsQuotaBased() {
		return isQuotaBased;
	}

	public void setIsQuotaBased(Integer isQuotaBased) {
		if(isQuotaBased == null) {
			this.isQuotaBased = 0;
		} else {
			this.isQuotaBased = isQuotaBased;
		}
	}

	public String getQuotaType() {
		return quotaType;
	}

	public void setQuotaType(String quotaType) {
		if(quotaType.isEmpty()) {
			this.quotaType = new String();
		} else {
			this.quotaType = quotaType;
		}
	}

	public String getQuotaValue() {
		return quotaValue;
	}

	public void setQuotaValue(String quotaValue) {
		if(quotaValue.isEmpty()) {
			this.quotaValue = new String();
		} else {
			this.quotaValue = quotaValue;
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

	public Lov getLovLeave() {
		return lovLeave;
	}

	public void setLovLeave(Lov lovLeave) {
		if(lovLeave == null) {
			this.lovLeave = new Lov();
		} else {
			this.lovLeave = lovLeave;
		}
	}
}