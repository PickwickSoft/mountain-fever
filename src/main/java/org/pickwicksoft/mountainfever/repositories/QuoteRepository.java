package org.pickwicksoft.mountainfever.repositories;

import org.pickwicksoft.mountainfever.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
