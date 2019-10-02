package com.demo.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="time_leave_request_details",uniqueConstraints = @UniqueConstraint(columnNames = {"leave_request_id","date"}))
public class LeaveRequestDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
    @JsonIgnoreProperties(value = {"leaveReqDet"})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "leave_request_id", referencedColumnName = "id")
    private LeaveRequest leaveRequest;
    
	@Column(name="date")
	private Date date;
	
	@Column(name="weight")
	private Double weight;
	
	@Column(name="status")
	private String status;

	public LeaveRequest getLeaveRequest() {
		return leaveRequest;
	}

	public void setLeaveRequest(LeaveRequest leaveRequest) {
		this.leaveRequest = leaveRequest;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
