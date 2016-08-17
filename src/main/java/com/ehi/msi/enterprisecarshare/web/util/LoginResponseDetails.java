package com.ehi.msi.enterprisecarshare.web.util;

import java.io.Serializable;

/**
 * LoginResponseDetails encapsulates the security token, only selected driver
 * restrictions items with response, default marketId response and all market
 * items response to be saved in cache.
 */
@SuppressWarnings("serial")
public class LoginResponseDetails implements Serializable {
	private SecurityToken secToken;
	private String driverQualStatus;
	private String loginToken;
	private String uuid;
	private String expiryDate;
	private String nextValidationDate;
	private String maskedLicenseNumber;
	private String issueState;
	private String issueCountry;
	private String homeEmail;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String contractName;
	private String lastValidatedTaskId;
	private String loginName;
	private String lastValidatedStatus;
	private String peoplesoftId;
	private String checkLicenseWithDriverQual;

	public SecurityToken getSecToken() {
		return secToken;
	}

	public void setSecToken(SecurityToken secToken) {
		this.secToken = secToken;
	}

	public String getDriverQualStatus() {
		return driverQualStatus;
	}

	public void setDriverQualStatus(String driverQualStatus) {
		this.driverQualStatus = driverQualStatus;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getNextValidationDate() {
		return nextValidationDate;
	}

	public void setNextValidationDate(String nextValidationDate) {
		this.nextValidationDate = nextValidationDate;
	}

	public String getMaskedLicenseNumber() {
		return maskedLicenseNumber;
	}

	public void setMaskedLicenseNumber(String maskedLicenseNumber) {
		this.maskedLicenseNumber = maskedLicenseNumber;
	}

	public String getIssueState() {
		return issueState;
	}

	public void setIssueState(String issueState) {
		this.issueState = issueState;
	}

	public String getIssueCountry() {
		return issueCountry;
	}

	public void setIssueCountry(String issueCountry) {
		this.issueCountry = issueCountry;
	}

	public String getHomeEmail() {
		return homeEmail;
	}

	public void setHomeEmail(String homeEmail) {
		this.homeEmail = homeEmail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getLastValidatedTaskId() {
		return lastValidatedTaskId;
	}

	public void setLastValidatedTaskId(String lastValidatedTaskId) {
		this.lastValidatedTaskId = lastValidatedTaskId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLastValidatedStatus() {
		return lastValidatedStatus;
	}

	public void setLastValidatedStatus(String lastValidatedStatus) {
		this.lastValidatedStatus = lastValidatedStatus;
	}

	public String getPeoplesoftId() {
		return peoplesoftId;
	}

	public void setPeoplesoftId(String peoplesoftId) {
		this.peoplesoftId = peoplesoftId;
	}

	public String getCheckLicenseWithDriverQual() {
		return checkLicenseWithDriverQual;
	}

	public void setCheckLicenseWithDriverQual(String checkLicenseWithDriverQual) {
		this.checkLicenseWithDriverQual = checkLicenseWithDriverQual;
	}

}
