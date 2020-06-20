package com.algaworks.osworks.domain.repository;

import com.algaworks.osworks.domain.model.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {

}
