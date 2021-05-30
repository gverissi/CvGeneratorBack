package com.example.cv.service;

import com.example.cv.entity.CurriculumVitae;
import com.lowagie.text.DocumentException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;

@Service
public class PdfService {

    private final SpringTemplateEngine templateEngine;

    @Autowired
    public PdfService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public File generatePdf(CurriculumVitae cv) throws IOException, DocumentException {
        Context context = getContext(cv);
        String html = loadAndFillTemplate(context);
        return renderPdf(html, cv.getName());
    }


    private File renderPdf(String html, String cvName) throws IOException, DocumentException {
        File file = File.createTempFile(cvName, ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html, new ClassPathResource("/static/").getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext(CurriculumVitae cv) {
        Context context = new Context();
        context.setVariable("cv", cv);
        byte[] encodeBase64 = Base64.encodeBase64(cv.getImage().getContent());
        String image = new String(encodeBase64, StandardCharsets.UTF_8);
        context.setVariable("image", image);
        int age = Period.between(cv.getInformation().getBirthDate(), LocalDate.now()).getYears();
        context.setVariable("age", age);
        context.setVariable("type", "pdf");
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("pdf-template", context);
    }

}
