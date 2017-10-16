package nl.payconiq.controller;

import java.util.List;
import javassist.NotFoundException;
import javax.validation.Valid;
import nl.payconiq.request.PostStockVO;
import nl.payconiq.request.PutStockVO;
import nl.payconiq.response.ResultStockVO;
import nl.payconiq.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller represents all operations about Stocks API
 * @author Rafael Del Sole
 */

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;

    @CrossOrigin(origins = "*")
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
