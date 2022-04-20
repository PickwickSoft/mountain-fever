package org.pickwicksoft.mountainfever.services;

import org.pickwicksoft.mountainfever.models.CurrentImage;
import org.pickwicksoft.mountainfever.repositories.CurrentImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentImageService {

    @Autowired
    private CurrentImageRepository currentImgRepo;

    public void setNewCurrentImage(Long id) {
        currentImgRepo.deleteAll();
        CurrentImage img = new CurrentImage();
        img.setId(id);
        currentImgRepo.save(img);
    }

    public CurrentImage getCurrentImage() {
        if (currentImgRepo.findAll().size() > 0) {
            return currentImgRepo.findAll().get(0);
        } else {
            return null;
        }
    }

}
