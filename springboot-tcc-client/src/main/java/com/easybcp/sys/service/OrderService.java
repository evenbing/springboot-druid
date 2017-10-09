package com.easybcp.sys.service;

import com.easybcp.client.BalanceReservationRequest;
import com.easybcp.client.ReservationResponse;
import com.easybcp.client.StockReservationRequest;
import com.easybcp.client.TccClient;
import com.easybcp.client.Test1Client;
import com.easybcp.client.Test1Client1Test;
import com.easybcp.client.Test2Client;
import com.easybcp.tccCoordinator.controller.StatusCode;
import com.easybcp.tccCoordinator.exception.PartialConfirmException;
import com.easybcp.tccCoordinator.exception.ReservationExpireException;
import com.easybcp.tccCoordinator.exception.Shift;
import com.easybcp.tccCoordinator.model.Participant;
import com.easybcp.tccCoordinator.model.PaymentRequest;
import com.easybcp.tccCoordinator.model.PlaceOrderRequest;
import com.easybcp.tccCoordinator.model.TccRequest;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhao Junjian
 */
@Service
public class OrderService  {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private TccClient tccClient;
    @Autowired
    private Test1Client1Test test1Client1Test;
    @Autowired
    private Test1Client accountClient;
    
    @Autowired
    private Test2Client productClient;
   
   public String test() {
        
	   test1Client1Test.test1("123");
        return "placeOrder";
    }
   public String reserve(BalanceReservationRequest request) {
       
	   accountClient.reserve(request);
        return "placeOrder";
    }
   
public String confirm(TccRequest request) {
       
	tccClient.confirm(request);
        return "placeOrder";
    }
   public String _204() {
       
	   tccClient._204();
        return "placeOrder";
    }
    @Transactional(rollbackFor = Exception.class)
    public String placeOrder(PlaceOrderRequest request) {
        
        // 预留库存
        reserveProductAndPersistParticipant();
        // 预留余额
        reserveBalanceAndPersistParticipant();
        return "placeOrder";
    }

    private void reserveBalanceAndPersistParticipant() {
        
        final ReservationResponse balanceResponse = reserveBalance();
        // 判断是否try失败
        final Participant participant = balanceResponse.getParticipantLink();
        if (participant == null) {
            Shift.fatal(StatusCode.HTTP_MESSAGE_NOT_READABLE);
        }
        //persistParticipant(participant, order.getId());
    }

    private void reserveProductAndPersistParticipant() {

        final ReservationResponse stockResponse = reserveProduct();
        // 判断是否try失败
        final Participant participant = stockResponse.getParticipantLink();
        if (participant == null) {
            Shift.fatal(StatusCode.HTTP_MESSAGE_NOT_READABLE);
        }
        //persistParticipant(participant, order.getId());
    }

    /*private void persistParticipant(Participant participant, Long orderId) {
        Preconditions.checkNotNull(participant);
        Preconditions.checkNotNull(orderId);
        final OrderParticipant orderParticipant = OrikaMapper.map(participant, OrderParticipant.class);
        orderParticipant.setOrderId(orderId);
        participantMapper.insertSelective(orderParticipant);
    }*/

    
    @Transactional(rollbackFor = Exception.class)
    public String confirm(PaymentRequest request) {
       /* Preconditions.checkNotNull(request);
        final Long orderId = request.getOrderId();
        // 检查订单是否存在
        final Order order = super.find(orderId);
        if (order == null) {
            Shift.fatal(StatusCode.ORDER_NOT_EXISTS);
        }
        final List<OrderParticipant> participants = participantMapper.selectByOrderId(orderId);
        if (participants.isEmpty()) {
            LOGGER.error("order id '{}' does not reserve any resource", orderId);
            Shift.fatal(StatusCode.SERVER_UNKNOWN_ERROR);
        }
        if (order.getStatus() == OrderStatus.PROCESSING) {
            confirmPhase(order, participants);
        }
        return new ObjectDataResponse<>(order);
        */
       
        Long tccId = 100L;
        OffsetDateTime expireTime = OffsetDateTime.now();
         Participant participant1 = new Participant("http://tcc-rest2/api/v1/stocks/reservation/" + tccId, expireTime);
        Participant participant2 = new Participant("http://tcc-rest1/api/v1/balances/reservation/" + tccId, expireTime);
        List<Participant> participants=new ArrayList<Participant>();
        participants.add(participant1);
        participants.add(participant2);
        confirmPhase(participants);
        return "test";
    }

    private void confirmPhase(List<Participant> participants) {
       
        // 表示全部try成功, 现在进行确认操作
        //final ImmutableList<Participant> links = ImmutableList.copyOf(participants);
        final TccRequest tccRequest = new TccRequest(participants);
        try {
            tccClient.confirm(tccRequest);
            /*order.setStatus(OrderStatus.DONE);
            if (super.updateNonNullProperties(order) > 0) {
                final ImmutableMap.Builder<String, Object> payloadBuilder = ImmutableMap.builder();
                payloadBuilder.put("point", order.getPrice());
                payloadBuilder.put("order_id", order.getId());
                payloadBuilder.put("user_id", order.getUserId());
                payloadBuilder.put("product_id", order.getProductId());
                // 发送积分添加事件
                publisher.persistPublishMessage(Jacksons.parse(payloadBuilder.build()), EventBusinessType.ADD_PTS.name());
            }*/
        } catch (HystrixRuntimeException e) {
            final Class<? extends Throwable> exceptionCause = e.getCause().getClass();
            if (ReservationExpireException.class.isAssignableFrom(exceptionCause)) {
                // 全部确认预留超时
                /*order.setStatus(OrderStatus.TIMEOUT);
                super.updateNonNullProperties(order);*/
            } else if (PartialConfirmException.class.isAssignableFrom(exceptionCause)) {
               /* order.setStatus(OrderStatus.CONFLICT);
                super.updateNonNullProperties(order);
                markdownConfliction(order, e);*/
            } else {
                throw e;
            }
        }
    }

  /*  private void markdownConfliction(Order order, HystrixRuntimeException e) {
        Preconditions.checkNotNull(order);
        Preconditions.checkNotNull(e);
        final String message = e.getCause().getMessage();
        LOGGER.error("order id '{}' has come across an confliction. {}", order.getId(), message);
        final OrderConflict conflict = new OrderConflict();
        conflict.setOrderId(order.getId());
        conflict.setErrorDetail(message);
        conflictService.persistNonNullProperties(conflict);
    }*/

    private ReservationResponse reserveBalance() {
        
        final BalanceReservationRequest balanceReservation = new BalanceReservationRequest();
        balanceReservation.setUserId(100L);
        balanceReservation.setAmount(100L);
        return accountClient.reserve(balanceReservation);
    }

    private ReservationResponse reserveProduct() {
        
        final StockReservationRequest reservation = new StockReservationRequest();
        reservation.setProductId(100L);
        return productClient.reserve(reservation);
    }

}
