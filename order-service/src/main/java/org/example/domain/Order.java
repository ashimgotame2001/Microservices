package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private String orderStatus;
    private UUID orderBy;
    private LocalDateTime orderDate;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private OrderDetail orderDetail;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private OrderLocation location;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;



}
