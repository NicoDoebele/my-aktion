package io.ds.myaktion.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonationRepository extends JpaRepository<Donation, Long>  {
    @Query(value = "SELECT * FROM Donation WHERE campaign_id = ?1", nativeQuery = true)
    public List<Donation> getDonationsFromCampaign(long campaignId);
}
