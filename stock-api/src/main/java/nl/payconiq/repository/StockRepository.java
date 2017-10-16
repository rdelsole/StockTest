package nl.payconiq.repository;

import nl.payconiq.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manipulate data in Stock table
 * @author Rafael Del Sole
 */
public interface StockRepository extends JpaRepository<StockEntity,Integer> {
}
