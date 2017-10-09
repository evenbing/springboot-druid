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
public class ProductStockTcc  {

    private static final long serialVersionUID = 2528053219589278252L;
    private Long id;

    private OffsetDateTime createTime;

    private OffsetDateTime updateTime;

    private OffsetDateTime deleteTime;
    

    private OffsetDateTime expireTime;

    private Integer stock;

    private TccStatus status;

    private Long productId;

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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public TccStatus getStatus() {
		return status;
	}

	public void setStatus(TccStatus status) {
		this.status = status;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
    

}