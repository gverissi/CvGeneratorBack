package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Image;
import com.example.cv.service.CvService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        System.out.println(cvService.findById(cvId).getImage().getName());
        return cvService.findById(cvId).getImage().getName();
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
