package com.easybcp.client;

import com.easybcp.tccCoordinator.model.Participant;
import com.easybcp.tccCoordinator.model.response.Response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zhao Junjian
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ReservationResponse implements Response {

    private static final long serialVersionUID = 841299264931794001L;

    @ApiModelProperty(value = "参与者确认资源", required = true)
    private Participant participantLink;

    public Participant getParticipantLink() {
		return participantLink;
	}

	public void setParticipantLink(Participant participantLink) {
		this.participantLink = participantLink;
	}

	public ReservationResponse(Participant participant) {
        this.participantLink = participant;
    }

}
