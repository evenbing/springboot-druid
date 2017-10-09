package com.easybcp.sys.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easybcp.client.BalanceReservationRequest;
import com.easybcp.sys.service.OrderService;
import com.easybcp.tccCoordinator.model.PaymentRequest;
import com.easybcp.tccCoordinator.model.PlaceOrderRequest;
import com.easybcp.tccCoordinator.model.TccRequest;

import javax.validation.Valid;

/**
 * @author Zhao Junjian
 */
@RestController
@RequestMapping(value = "/api/v1", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "下单", notes = "生成预订单")
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public String placeOrder(@Valid @RequestBody PlaceOrderRequest request, BindingResult result) {
        return orderService.placeOrder(request);
    }


    @ApiOperation(value = "确认订单", notes = "支付及确认")
    @RequestMapping(value = "/orders/confirmation", method = RequestMethod.POST)
    public String payOff(@Valid @RequestBody PaymentRequest request, BindingResult result) {
        return orderService.confirm(request);
    }
    
    

    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public String test(@RequestBody BalanceReservationRequest request) {
        return orderService.reserve(request);
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return orderService.test();
    }
    @RequestMapping(value = "/_204", method = RequestMethod.POST)
    public String _204() {
        return orderService._204();
    }
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(@RequestBody TccRequest request) {
        
    	orderService.confirm(request);
            return "placeOrder";
        }
}
