package org.pickwicksoft.mountainfever.repositories;

import org.pickwicksoft.mountainfever.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findTopByOrderByIdDesc();
}
