package com.easybcp.sys.model;

import com.easybcp.tccCoordinator.model.TccStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.OffsetDateTime;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserBalanceTcc {
    private static final long serialVersionUID = 8291022579522014250L;
    private Long id;

    private OffsetDateTime createTime;

    private OffsetDateTime updateTime;

    private OffsetDateTime deleteTime;
    
    private OffsetDateTime expireTime;

    private Long amount;

    private TccStatus status;

    private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(OffsetDateTime createTime) {
		this.createTime = createTime;
	}

	public OffsetDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(OffsetDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public OffsetDateTime getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(OffsetDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}

	public OffsetDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(OffsetDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public TccStatus getStatus() {
		return status;
	}

	public void setStatus(TccStatus status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    

}