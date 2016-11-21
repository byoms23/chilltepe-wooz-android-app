package me.wooz.mobile.android.dto.policies;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.joda.time.DateTime;
import org.greenrobot.greendao.annotation.Generated;

import me.wooz.mobile.android.utils.JodaDateTimeConverter;

/**
 * Created by byron on 14/11/16.
 */

@Entity
public class Policy {

	@Id
	@Property(nameInDb = "id")
	private Long id;

	private String number;

	@JsonProperty("user_id")
	private Long userId;

	@JsonProperty("insurance_type_id")
	private Long insuranceTypeId;

	@JsonProperty("insurance_company_id")
	private Long insuranceCompanyId;

	@Convert(converter = JodaDateTimeConverter.class, columnType = Long.class)
	@JsonProperty("created_at")
	private DateTime createdAt;

	@Convert(converter = JodaDateTimeConverter.class, columnType = Long.class)
	@JsonProperty("updated_at")
	private DateTime updatedAt;

	@Generated(hash = 950350911)
	public Policy() {
	}

	@Generated(hash = 2063163158)
	public Policy(Long id, String number, Long userId, Long insuranceTypeId,
									Long insuranceCompanyId, DateTime createdAt, DateTime updatedAt) {
					this.id = id;
					this.number = number;
					this.userId = userId;
					this.insuranceTypeId = insuranceTypeId;
					this.insuranceCompanyId = insuranceCompanyId;
					this.createdAt = createdAt;
					this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getInsuranceTypeId() {
		return insuranceTypeId;
	}

	public void setInsuranceTypeId(Long insuranceTypeId) {
		this.insuranceTypeId = insuranceTypeId;
	}

	public Long getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(Long insuranceCompanyId) {
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
