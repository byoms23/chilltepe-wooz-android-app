package me.wooz.mobile.android.dto.policies;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;

/**
 * Created by byron on 14/11/16.
 */

public class Policy {

	private Integer id;

	private String number;

	@JsonProperty("user_id")
	private Integer userId;

	@JsonProperty("insurance_type_id")
	private Integer insuranceTypeId;

	@JsonProperty("insurance_company_id")
	private Integer insuranceCompanyId;

	@JsonProperty("created_at")
	private DateTime createdAt;

	@JsonProperty("updated_at")
	private DateTime updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getInsuranceTypeId() {
		return insuranceTypeId;
	}

	public void setInsuranceTypeId(Integer insuranceTypeId) {
		this.insuranceTypeId = insuranceTypeId;
	}

	public Integer getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(Integer insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
