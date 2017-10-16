package nl.payconiq.controller;

import javassist.NotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import nl.payconiq.request.PostStockVO;
import nl.payconiq.request.PutStockVO;
import nl.payconiq.response.ResultStockVO;
import nl.payconiq.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller represents all operations about Stocks
 * @author Rafael Del Sole
 */

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping(path = "/stocks")
    public List<ResultStockVO> listStocks() {
        return stockService.listStocks();
    }

    @GetMapping(path = "/stocks/{stockId}")
    public ResultStockVO getStock(@PathVariable("stockId") Integer stockId) throws NotFoundException {
        return stockService.getStock(stockId);
    }

    @PutMapping(value = "/stocks/{stockId}")
    public ResponseEntity<ResultStockVO> updateStock(@PathVariable("stockId") Integer stockId, @Valid @RequestBody PutStockVO putStockVO) throws NotFoundException {
        return new ResponseEntity<>(stockService.updateStock(stockId, putStockVO), HttpStatus.OK);

    }

    @PostMapping(value = "/stocks")
    public ResponseEntity<ResultStockVO> createStock(@Valid @RequestBody PostStockVO postStockVO) {
        return new ResponseEntity<>(stockService.createStock(postStockVO), HttpStatus.CREATED);
    }


}
