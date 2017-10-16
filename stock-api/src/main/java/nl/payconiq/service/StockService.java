package nl.payconiq.service;

import javassist.NotFoundException;
import nl.payconiq.request.PostStockVO;
import nl.payconiq.request.PutStockVO;
import nl.payconiq.response.ResultStockVO;

import java.util.List;

public interface StockService {


    ResultStockVO createStock(PostStockVO postStockVO);

    ResultStockVO updateStock(Integer stockId, PutStockVO putStockVO) throws NotFoundException;

    List<ResultStockVO> listStocks();

    ResultStockVO getStock(Integer stockId) throws NotFoundException;
}
