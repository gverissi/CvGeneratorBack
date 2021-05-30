package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.service.CvService;
import com.example.cv.service.PdfService;
import com.lowagie.text.DocumentException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs/{cvId}")
public class PdfController {

    private final CvService cvService;
    private final PdfService pdfService;

    @Autowired
    public PdfController(CvService cvService, PdfService pdfService) {
        this.cvService = cvService;
        this.pdfService = pdfService;
    }

    @GetMapping("/download-pdf")
    public void downloadCvAsPdf(@PathVariable long cvId, HttpServletResponse response) {
        CurriculumVitae cv = cvService.findById(cvId);
        try {
            Path file = Paths.get(pdfService.generatePdf(cv).getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/show-html")
    public String showCvAsHtml(@PathVariable long cvId, Model model) {
        CurriculumVitae cv = cvService.findById(cvId);
        model.addAttribute("cv", cv);
        byte[] encodeBase64 = Base64.encodeBase64(cv.getImage().getContent());
        String image = new String(encodeBase64, StandardCharsets.UTF_8);
        model.addAttribute("image", image);
        return "pdf-template";
    }

}
