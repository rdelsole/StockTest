package nl.payconiq.repository;

import nl.payconiq.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockRepository extends JpaRepository<StockEntity,Integer> {
}
