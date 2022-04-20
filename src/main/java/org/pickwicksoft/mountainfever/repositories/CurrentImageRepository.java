package org.pickwicksoft.mountainfever.repositories;

import org.pickwicksoft.mountainfever.models.CurrentImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentImageRepository extends JpaRepository<CurrentImage, Long> {
}
