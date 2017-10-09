package com.easybcp.tccCoordinator.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.URL;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.time.OffsetDateTime;


public class Participant implements Serializable {

    private static final long serialVersionUID = -1896632687832278753L;

    @URL
    @ApiModelProperty(value = "资源URI", required = true, example = "http://www.example.com/part/123")
    private String uri;

    @ApiModelProperty(value = "过期时间, ISO标准", required = true, example = "2017-03-20T14:00:41+08:00")

    private OffsetDateTime expireTime;

    @ApiModelProperty(value = "发起confirm的时间, ISO标准", hidden = true, example = "2017-03-20T14:00:41+08:00")
 
    private OffsetDateTime executeTime;

    @ApiModelProperty(value = "参与方返回的错误码", hidden = true, example = "451")
    private ResponseEntity<?> participantErrorResponse;

    @ApiModelProperty(value = "预留资源的状态", hidden = true, example = "TO_BE_CONFIRMED")
    private TccStatus tccStatus = TccStatus.TO_BE_CONFIRMED;

	

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public OffsetDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(OffsetDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public OffsetDateTime getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(OffsetDateTime executeTime) {
		this.executeTime = executeTime;
	}

	public ResponseEntity<?> getParticipantErrorResponse() {
		return participantErrorResponse;
	}

	public void setParticipantErrorResponse(ResponseEntity<?> participantErrorResponse) {
		this.participantErrorResponse = participantErrorResponse;
	}

	public TccStatus getTccStatus() {
		return tccStatus;
	}

	public void setTccStatus(TccStatus tccStatus) {
		this.tccStatus = tccStatus;
	}

	public Participant(String uri, OffsetDateTime expireTime) {
		super();
		this.uri = uri;
		this.expireTime = expireTime;
	}
    
}
