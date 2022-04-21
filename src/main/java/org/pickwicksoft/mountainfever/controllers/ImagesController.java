package org.pickwicksoft.mountainfever.controllers;


import org.jetbrains.annotations.NotNull;
import org.pickwicksoft.mountainfever.models.Image;
import org.pickwicksoft.mountainfever.models.ImageResponse;
import org.pickwicksoft.mountainfever.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/images/")
public class ImagesController {
    @Autowired
    private ImageService imgService;

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            imgService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping
    public List<ImageResponse> list() {
        return imgService.getAllImages()
                .stream()
                .map(this::mapToImageResponse)
                .collect(Collectors.toList());
    }

    private ImageResponse mapToImageResponse(Image image) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(String.valueOf(image.getId()))
                .toUriString();
        ImageResponse fileResponse = new ImageResponse();
        fileResponse.setId(String.valueOf(image.getId()));
        fileResponse.setName(image.getName());
        fileResponse.setContentType(image.getContent_type());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imgService.getImage(id);

        return getResponseEntity(imageOptional);
    }

    @NotNull
    private ResponseEntity<byte[]> getResponseEntity(Optional<Image> imageOptional) {
        if (imageOptional.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }

        Image image = imageOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment\"")
                .contentType(MediaType.valueOf(image.getContent_type()))
                .body(image.getImage());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteQuote(@PathVariable Long id) {
        imgService.deleteById(id);
    }

    @GetMapping
    @RequestMapping("/current")
    public ResponseEntity<byte[]> current() {
        if (imgService.getCurrentImage().isPresent()) {
            var currentID = imgService.getCurrentImage().get().getId();
            var img = imgService.getImage(currentID);
            return getResponseEntity(img);
        }
        return getResponseEntity(Optional.empty());
    }
}