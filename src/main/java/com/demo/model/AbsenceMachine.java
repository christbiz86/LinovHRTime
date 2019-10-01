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
@Table(name="time_absence_machines",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","machine_id"}))
public class AbsenceMachine extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;

	@Column(name = "machine_id")
	private String machineId;

	@Column(name = "machine_name")
	private String machineName;
	
	@Column(name = "verification_code")
	private String verificationCode;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if(company==null) {
			this.company = new Company();
		}else {
			this.company = company;			
		}
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		if(machineId==null) {
			this.machineId = new String();
		}else {
			this.machineId = machineId;			
		}
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		if(machineName==null) {
			this.machineName = new String();
		}else {
			this.machineName = machineName;			
		}
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		if(verificationCode==null) {
			this.verificationCode = new String();
		}else {
			this.verificationCode = verificationCode;			
		}
	}
}