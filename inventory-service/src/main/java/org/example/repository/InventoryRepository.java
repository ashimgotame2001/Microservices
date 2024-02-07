package org.example.repository;

import org.example.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    Optional<Inventory> findBySkuCode(String code);

    List<Inventory> findBySkuCodeIn(List<String> code);

}
