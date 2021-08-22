package lk.hotelManagement.backend.controller;

import lk.hotelManagement.backend.model.Stock;
import lk.hotelManagement.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping("/")
    public List<Stock> stocks() {
        return stockService.stocks();
    }

    @PostMapping("/")
    public boolean createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }
    @GetMapping("/stock")
    public Stock stockById(@RequestParam String stock_id){
        return  stockService.stockById(stock_id);
    }
    @PutMapping("/{stock_id}")
    public boolean editStock(@PathVariable String stock_id,@RequestBody Stock stock){
        return stockService.editStock(stock_id,stock);

    }
    @DeleteMapping("/{stock_id}")
    public boolean deleteStock(@PathVariable String stock_id){
        return stockService.deleteStock(stock_id);
    }


}
