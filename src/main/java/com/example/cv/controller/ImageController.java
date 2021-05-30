package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Image;
import com.example.cv.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs/{cvId}")
public class ImageController {

    private final CvService cvService;

    @Autowired
    public ImageController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping(value = "/image", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getImage(@PathVariable long cvId) {
        Image image = cvService.findById(cvId).getImage();
        if (image != null) {
            return image.getName();
        } else {
            return "Aucune image selectionné.";
        }
    }

    @PostMapping("/image")
    public void setImage(@PathVariable long cvId, @RequestParam("imageForm") MultipartFile multipartImage) throws IOException {
        Image image = new Image();
        image.setContent(multipartImage.getBytes());
        image.setName(multipartImage.getOriginalFilename());
        CurriculumVitae cv = cvService.findById(cvId);
        cv.setImage(image);
        cvService.save(cv);
    }

}
