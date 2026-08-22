package com.cn.trademaster.service;

import com.cn.trademaster.dto.TradeDto;
import com.cn.trademaster.model.Trade;
import com.cn.trademaster.repository.TradeRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;

@Service
@RequiredArgsConstructor
public class TradeService
{
    private final TradeRepo tradeRepo;
    private final Logger logger = LoggerFactory.getLogger(TradeService.class);
    /**

     1. Create a logger object here from LoggerFactory and pass tradeService class
       as parameter for the getLogger() method.

     2. Implement logger in the methods below wherever required.

     **/

    public void executeTrade(TradeDto dto)
    {
        Trade trade = new Trade();
        trade.setPrice(dto.getPrice());
        trade.setQuantity(dto.getQuantity());
        trade.setStockHolderUserName(dto.getStockHolderUserName());
        trade.setStockId(dto.getStockId());
        trade.setStockName(dto.getStockName());
        if(trade.getQuantity() > 1500)
        {
            logger.error("stock exceeded 1500");
            return;
        }
        /**
         1. First map the tradeDto object with the trade entity object before saving.

        2. After mapping tradeDto write the logic for printing an "ERROR" level log
            with an appropriate message whenever the quantity of stock exceeds the value
            of 1500 thus ending the execution of executeTrade() method.

        3. If quantity is in required range i.e. (less than 1500 ) then provide a "WARN"
            level log asking user to remember their unique username.
        **/
        logger.warn("remember your unique username");
        tradeRepo.save(trade);
        /**
         4. Lastly, provide an "INFO" level log displaying a success message after the trade
            is successfully executed/saved.
        **/
        logger.info("successfully saved trade");
    }


    public List<Trade> getTradeHistory(String username)
    {
        List<Trade> tradesByUsername = this.tradeRepo.findByStockHolderUserName(username);
        if(tradesByUsername == null || tradesByUsername.isEmpty())
        {
            logger.error("no trade history found");
        }
        /**

         1. Use the findByStockHolderUserName() method of TradeRepo for fetching
            the list of Trade by username and save the returned value from this method
            to the tradesByUsername object declared above.

         2. Implement an "ERROR" level log if no trades are found by the username.

         3. Provide a simple "INFO" level log with a meaningful message if trades are found.

         4. Lastly, return tradesByUsername object.
         **/
        logger.info("found trades for the user");
        return tradesByUsername;
    }
}
