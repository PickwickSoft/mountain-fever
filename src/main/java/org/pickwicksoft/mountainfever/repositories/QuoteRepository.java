package org.pickwicksoft.mountainfever.repositories;

import org.pickwicksoft.mountainfever.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
