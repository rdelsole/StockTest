package nl.payconiq.converter;

import nl.payconiq.entity.StockEntity;
import nl.payconiq.request.PostStockVO;
import nl.payconiq.response.ResultStockVO;

/**
 * Convert the all StockVO to Entity or Entity to VO
 * @author Rafael Del Sole
 */
public class StockConverter {

    /**
     * Copy the non auto generate properties to StockEntity class
     * @param postStockVO
     * @return StockEntity object filled
     */
    public static StockEntity from(final PostStockVO postStockVO) {

        StockEntity stock = new StockEntity();
        stock.setCurrentPrice(postStockVO.getCurrentPrice());
        stock.setName(postStockVO.getName());
        return stock;
    }

    /**
     * Convert the entity to StockResultVO
     * @param stock
     * @return ResultStockVO with all properties filled
     */
    public static ResultStockVO to(final StockEntity stock) {

        ResultStockVO stockResult = new ResultStockVO();
        stockResult.setId(stock.getId());
        stockResult.setLastUpdate(stock.getLastUpdate());
        stockResult.setCurrentPrice(stock.getCurrentPrice());
        stockResult.setName(stock.getName());
        return stockResult;
    }
}
