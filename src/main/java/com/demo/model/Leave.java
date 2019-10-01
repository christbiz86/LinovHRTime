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
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(Integer maxQuota) {
		this.maxQuota = maxQuota;
	}

	public Integer getQuotaExpiration() {
		return quotaExpiration;
	}

	public void setQuotaExpiration(Integer quotaExpiration) {
		this.quotaExpiration = quotaExpiration;
	}

	public Integer getDayTakenMin() {
		return dayTakenMin;
	}

	public void setDayTakenMin(Integer dayTakenMin) {
		this.dayTakenMin = dayTakenMin;
	}

	public Integer getDayTakenMax() {
		return dayTakenMax;
	}

	public void setDayTakenMax(Integer dayTakenMax) {
		this.dayTakenMax = dayTakenMax;
	}

	public Integer getCarryMax() {
		return carryMax;
	}

	public void setCarryMax(Integer carryMax) {
		this.carryMax = carryMax;
	}

	public Integer getCarryExpirationDay() {
		return carryExpirationDay;
	}

	public void setCarryExpirationDay(Integer carryExpirationDay) {
		this.carryExpirationDay = carryExpirationDay;
	}

	public Lov getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Lov leaveType) {
		this.leaveType = leaveType;
	}

	public Lov getLeavePart() {
		return leavePart;
	}

	public void setLeavePart(Lov leavePart) {
		this.leavePart = leavePart;
	}

	public Integer getIsAnnualLeave() {
		return isAnnualLeave;
	}

	public void setIsAnnualLeave(Integer isAnnualLeave) {
		this.isAnnualLeave = isAnnualLeave;
	}

	public Integer getIsAllowHalfDay() {
		return isAllowHalfDay;
	}

	public void setIsAllowHalfDay(Integer isAllowHalfDay) {
		this.isAllowHalfDay = isAllowHalfDay;
	}

	public Integer getIsAnnualLeaveDeductor() {
		return isAnnualLeaveDeductor;
	}

	public void setIsAnnualLeaveDeductor(Integer isAnnualLeaveDeductor) {
		this.isAnnualLeaveDeductor = isAnnualLeaveDeductor;
	}

	public Integer getIsRequestAble() {
		return isRequestAble;
	}

	public void setIsRequestAble(Integer isRequestAble) {
		this.isRequestAble = isRequestAble;
	}

	public Integer getIsQuotaBased() {
		return isQuotaBased;
	}

	public void setIsQuotaBased(Integer isQuotaBased) {
		this.isQuotaBased = isQuotaBased;
	}

	public String getQuotaType() {
		return quotaType;
	}

	public void setQuotaType(String quotaType) {
		this.quotaType = quotaType;
	}

	public String getQuotaValue() {
		return quotaValue;
	}

	public void setQuotaValue(String quotaValue) {
		this.quotaValue = quotaValue;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Lov getLovLeave() {
		return lovLeave;
	}

	public void setLovLeave(Lov lovLeave) {
		this.lovLeave = lovLeave;
	}
}