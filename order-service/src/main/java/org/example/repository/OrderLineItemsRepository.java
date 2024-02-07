package org.example.repository;

import org.example.domain.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems,Long> {
}
