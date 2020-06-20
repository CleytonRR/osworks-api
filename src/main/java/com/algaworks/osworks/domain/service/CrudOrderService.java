package com.algaworks.osworks.domain.service;

import com.algaworks.osworks.domain.model.OrderService;
import com.algaworks.osworks.domain.model.StatusOrderServico;
import com.algaworks.osworks.domain.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CrudOrderService {

    @Autowired
    private OrderServiceRepository orderServiceRepository;
    public OrderService create(OrderService orderService) {
        orderService.setStatus(StatusOrderServico.ABERTA);
        orderService.setDataAbertura(LocalDateTime.now());
        return orderServiceRepository.save(orderService);
    }
}
