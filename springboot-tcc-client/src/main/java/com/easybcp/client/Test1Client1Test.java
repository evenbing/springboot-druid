package com.easybcp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easybcp.utils.BaseResponse;

/**
 * @author Zhao Junjian
 */
@FeignClient(name = Test1Client1Test.SERVICE_ID, fallback = Test1ClientFallback.class)
public interface Test1Client1Test {
    /**
     * eureka service name
     */
    String SERVICE_ID = "TCC-REST1";
  
    @RequestMapping(value = "/test/test1", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<String> test1(@RequestParam("id") String id);
}
