package com.easybcp.client;

import javax.validation.Valid;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.easybcp.tccCoordinator.model.TccRequest;

/**
 * @author Zhao Junjian
 */
//@FeignClient(name = TccClient.SERVICE_ID, fallback = TccClientFallback.class)
@FeignClient(name = TccClient.SERVICE_ID)
public interface TccClient {
    /**
     * eureka service name
     */
    String SERVICE_ID = "TCC-COORDINATOR";
    /**
     * api prefix
     */
    String API_PATH = "/api/v1/coordinator";
    
     String TEST_URI_PREFIX = "/test";

    @RequestMapping(value = API_PATH + "/confirmation", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    void confirm(@RequestBody TccRequest request);

    @RequestMapping(value = API_PATH + "/cancellation", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    void cancel(@RequestBody TccRequest request);

    @RequestMapping(value = TEST_URI_PREFIX + "/204", method = RequestMethod.PUT)
    void _204();
    
  
    /*@RequestMapping(value = API_PATH + "/confirmation", method = RequestMethod.PUT)
    public void confirm( @RequestBody TccRequest request) ;*/
}
