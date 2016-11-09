package me.wooz.mobile.android.dto.ensurance;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by byron on 8/11/16.
 */

public class InsuranceCompany {

	private Integer id;

	private String name;

	@JsonProperty("email_to_report")
	private List<String> emailToReport;

	private List<String> phone;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getEmailToReport() {
		return emailToReport;
	}

	public void setEmailToReport(List<String> emailToReport) {
		this.emailToReport = emailToReport;
	}

	public List<String> getPhone() {
		return phone;
	}

	public void setPhone(List<String> phone) {
		this.phone = phone;
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

	@Override
	public String toString() {
		return name;
	}
}
