package com.easybcp.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.easybcp.tccCoordinator.controller.StatusCode;
import com.easybcp.tccCoordinator.exception.Shift;

/**
 * @author Zhao Junjian
 */
@Component
public class Test2ClientFallback implements Test2Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test2ClientFallback.class);

  

    @Override
    public ReservationResponse reserve(@RequestBody StockReservationRequest request) {
        didNotGetResponse();
        Shift.fatal(StatusCode.HTTP_MESSAGE_NOT_READABLE);
        return null;
    }

    private void didNotGetResponse() {
    	LOGGER.error("service '{}' has become unreachable", TccClient.SERVICE_ID);
    }
}
