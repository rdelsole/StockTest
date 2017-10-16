package nl.payconiq.converter;

import nl.payconiq.entity.StockEntity;
import nl.payconiq.request.PostStockVO;
import nl.payconiq.request.PutStockVO;
import nl.payconiq.response.ResultStockVO;

public class StockConverter {

    public static StockEntity from(final PostStockVO postStockVO) {

        StockEntity stock = new StockEntity();
        stock.setCurrentPrice(postStockVO.getPrice());
        stock.setName(postStockVO.getName());
        return stock;
    }

    public static ResultStockVO to(final StockEntity stock) {

        ResultStockVO stockResult = new ResultStockVO();
        stockResult.setId(stock.getId());
        stockResult.setLastUpdate(stock.getLastUpdate());
        stockResult.setCurrentPrice(stock.getCurrentPrice());
        stockResult.setName(stock.getName());
        return stockResult;
    }
}
