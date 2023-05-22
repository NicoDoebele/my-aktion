package io.ds.myaktion.exceptions;

public class CampaignNotFoundException extends RuntimeException {
    
    public CampaignNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
