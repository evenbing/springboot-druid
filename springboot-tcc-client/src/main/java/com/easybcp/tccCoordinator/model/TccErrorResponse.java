package com.easybcp.tccCoordinator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class TccErrorResponse implements Serializable {

    private static final long serialVersionUID = 6016973979617189095L;

  
    @ApiModelProperty(value = "参与方提供的链接集合", required = true)
    private List<Participant> participantLinks;


	public TccErrorResponse(List<Participant> participantLinks) {
		super();
		this.participantLinks = participantLinks;
	}


	public List<Participant> getParticipantLinks() {
		return participantLinks;
	}


	public void setParticipantLinks(List<Participant> participantLinks) {
		this.participantLinks = participantLinks;
	}

}
