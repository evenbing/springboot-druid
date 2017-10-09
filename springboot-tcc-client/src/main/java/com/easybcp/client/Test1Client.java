package com.easybcp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Zhao Junjian
 */
@FeignClient(name = Test1Client.SERVICE_ID, fallback = Test1ClientFallback.class)
public interface Test1Client {
    /**
     * eureka service name
     */
    String SERVICE_ID = "TCC-REST1";
    /**
     * common api prefix
     */
    String API_PATH = "/api/v1";

   

    @RequestMapping(value = API_PATH + "/balances/reservation", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    ReservationResponse reserve(@RequestBody BalanceReservationRequest request);

}
