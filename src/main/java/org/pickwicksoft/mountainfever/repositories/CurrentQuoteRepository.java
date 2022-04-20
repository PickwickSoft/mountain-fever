package org.pickwicksoft.mountainfever.repositories;

import org.pickwicksoft.mountainfever.models.CurrentQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentQuoteRepository extends JpaRepository<CurrentQuote, Long> {

}