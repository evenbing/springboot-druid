package com.easybcp.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.easybcp.tccCoordinator.controller.StatusCode;
import com.easybcp.tccCoordinator.exception.Shift;
import com.easybcp.tccCoordinator.model.TccRequest;

/**
 * @author Zhao Junjian
 */
@Component
public class TccClientFallback implements TccClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(TccClientFallback.class);


    @Override
    public void confirm(@RequestBody TccRequest request) {
        didNotGetResponse();
        Shift.fatal(StatusCode.HTTP_MESSAGE_NOT_READABLE);
    }

    @Override
    public void cancel(@RequestBody TccRequest request) {
        didNotGetResponse();
        Shift.fatal(StatusCode.HTTP_MESSAGE_NOT_READABLE);
    }

    private void didNotGetResponse() {
        LOGGER.error("service '{}' has become unreachable", TccClient.SERVICE_ID);
    }

	@Override
	public void _204() {
		// TODO Auto-generated method stub
		
	}
}
