package org.pickwicksoft.mountainfever.services;


import org.pickwicksoft.mountainfever.models.CurrentImage;
import org.pickwicksoft.mountainfever.models.Image;
import org.pickwicksoft.mountainfever.models.UsedPixabayImage;
import org.pickwicksoft.mountainfever.repositories.ImageRepository;
import org.pickwicksoft.mountainfever.repositories.UsedPixaybayImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.pickwicksoft.mountainfever.web.WebResourceHelper.downloadUrl;
import static org.pickwicksoft.mountainfever.web.WebResourceHelper.getContentType;

@Service
@Transactional
public class ImageService {
    @Autowired
    private ImageRepository imgRepository;

    @Autowired
    private UsedPixaybayImageRepository usedPixaybayImageRepository;

    @Autowired
    private CurrentImageService currentImageService;

    public void save(MultipartFile file) throws IOException {
        // save(file.getBytes(), file.getOriginalFilename(), file.getContentType());
        Image image = new Image();
        image.setImage(file.getBytes());
        image.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        image.setContent_type(file.getContentType());

        imgRepository.save(image);
    }

    public void save(byte[] byteImage, String name, String contentType) {
        save(byteImage, name, contentType, false);
    }

    public void save(byte[] byteImage, String name, String contentType, Boolean setCurrent) {
        Image image = new Image();
        image.setImage(byteImage);
        image.setName(StringUtils.cleanPath(Objects.requireNonNull(name)));
        image.setContent_type(contentType);
        imgRepository.saveAndFlush(image);
        if (setCurrent) {
            currentImageService.setNewCurrentImage(imgRepository.findTopByOrderByIdDesc().getId());
        }
    }

    public Optional<Image> getImage(Long id) {
        return imgRepository.findById(id);
    }

    public List<Image> getAllImages() {
        return imgRepository.findAll();
    }

    public List<UsedPixabayImage> getUsedPixabayImages() {
        return usedPixaybayImageRepository.findAll();
    }

    @Modifying
    public void addUsedPixabayImage(Long id) {
        UsedPixabayImage pixabayImg = new UsedPixabayImage();
        pixabayImg.setPixabay_id(String.valueOf(id));
        usedPixaybayImageRepository.saveAndFlush(pixabayImg);
    }

    public void saveNewCurrentImageFromPixabayUrl(String imgUrl, String name) throws MalformedURLException {
        URL url = new URL(imgUrl);
        byte[] bytes = downloadUrl(url);
        save(bytes, name, getContentType(url), true);
    }

    public Optional<CurrentImage> getCurrentImage() {
        return Optional.ofNullable(currentImageService.getCurrentImage());
    }

}