package nl.payconiq.service;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import javassist.NotFoundException;
import nl.payconiq.converter.StockConverter;
import nl.payconiq.entity.StockEntity;
import nl.payconiq.repository.StockRepository;
import nl.payconiq.request.PostStockVO;
import nl.payconiq.request.PutStockVO;
import nl.payconiq.response.ResultStockVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */

@Service
public class StockServiceImpl implements StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockRepository stockRepository;

    @Override
    public ResultStockVO createStock(final PostStockVO postStockVO) {
        StockEntity stockEntity = StockConverter.from(postStockVO);
        stockEntity.setLastUpdate(LocalDateTime.now());
        stockRepository.save(stockEntity);
        return StockConverter.to(stockEntity);
    }

    @Override
    public ResultStockVO updateStock(final Integer stockId, final PutStockVO putStockVO) throws NotFoundException {

        StockEntity stock = stockRepository.findOne(stockId);

        if (stock == null) {
            LOGGER.error("m=updateStock error=notFound stockId={}",stockId);
            throw new NotFoundException(String.format("The stock with ID:%1$d doesn't exist ",stockId));
        }

        stock.setCurrentPrice(putStockVO.getCurrentPrice());
        stock.setLastUpdate(LocalDateTime.now());

        stockRepository.save(stock);
        return StockConverter.to(stock);
    }

    @Override
    public List<ResultStockVO> listStocks() {
        return stockRepository.findAll().stream().map(StockConverter::to).collect(toList());
    }

    @Override
    public ResultStockVO getStock(final Integer stockId) throws NotFoundException {
        StockEntity stock = stockRepository.findOne(stockId);

        if (stock == null) {
            LOGGER.error("m=updateStock error=notFound stockId={}",stockId);
            throw new NotFoundException(String.format("The stock with ID:%1$d doesn't exist ",stockId));
        }

        return StockConverter.to(stock);
    }
}
