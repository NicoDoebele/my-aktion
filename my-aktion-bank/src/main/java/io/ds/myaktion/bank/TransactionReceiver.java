package io.ds.myaktion.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import io.ds.myaktion.bank.dto.Transaction;
import io.lettuce.core.RedisCommandTimeoutException;

public class TransactionReceiver {

    private static final Logger LOGGER= LoggerFactory.getLogger(TransactionReceiver.class);

    @Autowired
    private RedisTemplate<String, Transaction> redisTemplate;

    public void receiveTransaction(Transaction transaction) {
        LOGGER.info("Received<" + transaction+ ">");

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("Transaction processed<" + transaction+ ">");

        try{
            redisTemplate.convertAndSend("transactionProcessed", transaction);
        } catch(RedisCommandTimeoutException e) {
            //TODO Implement Retrymechanism
            LOGGER.info("Couldnot send transaction<" + transaction+ ">");
        }
    }
}
