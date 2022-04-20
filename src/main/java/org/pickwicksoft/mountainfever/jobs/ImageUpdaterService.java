package org.pickwicksoft.mountainfever.jobs;

import org.pickwicksoft.mountainfever.models.UsedPixabayImage;
import org.pickwicksoft.mountainfever.pixabay.ImageFinder;
import org.pickwicksoft.mountainfever.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.blizzed.pixabaylib.PixabayCallException;
import ru.blizzed.pixabaylib.PixabayErrorException;
import ru.blizzed.pixabaylib.model.PixabayImage;

import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Service
@Transactional
public class ImageUpdaterService {
    private final ImageFinder finder = new ImageFinder();
    @Autowired
    private ImageService imgService;

    @Scheduled(fixedDelayString = "${app.images.refresh-interval}")
    public void updateImage() {
        try {
            List<PixabayImage> images = finder.search("mountain");
            List<String> usedImages = imgService.getUsedPixabayImages().stream().map(UsedPixabayImage::getPixabay_id).toList();
            List<PixabayImage> unusedImages = images.stream().filter((e) -> !usedImages.contains(String.valueOf(e.getId()))).toList();
            if (unusedImages.size() > 0) {
                PixabayImage newImage = unusedImages.get(0);
                imgService.saveNewCurrentImageFromPixabayUrl(newImage.getLargeImageURL(), newImage.getPageURL());
                imgService.addUsedPixabayImage(newImage.getId());
            } else {
                finder.nextPage();
                updateImage();
            }

        } catch (PixabayErrorException | PixabayCallException | MalformedURLException e) {
            e.printStackTrace();
        }
        Logger logger = Logger.getLogger("");
        logger.log(new LogRecord(Level.INFO, "Set new Image"));
    }

}
