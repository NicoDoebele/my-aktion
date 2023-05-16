package io.ds.myaktion.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query(value = "SELECT SUM(amount) FROM Donation WHERE campaign_id = ?1 GROUP BY campaign_id")
    public Optional<Double> getAmountDonatedSoFar(long campaignId);
}
