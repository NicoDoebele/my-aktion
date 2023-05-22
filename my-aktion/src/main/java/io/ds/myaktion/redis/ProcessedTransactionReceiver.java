package io.ds.myaktion.redis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.ds.myaktion.dto.Transaction;
import io.ds.myaktion.exceptions.DonationNotFoundException;
import io.ds.myaktion.service.DonationService;

public class ProcessedTransactionReceiver{

    private static final Logger LOGGER= LoggerFactory.getLogger(ProcessedTransactionReceiver.class);

    @Autowired
    DonationService donationService;

    public void receiveProcessedTransaction(Transaction transaction) {
        LOGGER.info("Received<" + transaction+ ">");
        try{
            donationService.changeDonationState(transaction.getDonationId());
            LOGGER.info("ChangedstateofdonationtoTRANSFERRED. donationId="+transaction.getDonationId());
        } catch(DonationNotFoundException e) {
            LOGGER.error("Donation alreadydeleted.", e);
        }
    }
}