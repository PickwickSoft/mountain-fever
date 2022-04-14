package org.pickwicksoft.mountainfever.repositories;

import org.pickwicksoft.mountainfever.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
