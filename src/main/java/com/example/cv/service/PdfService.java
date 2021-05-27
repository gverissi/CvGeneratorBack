package com.example.cv.service;

import com.example.cv.entity.CurriculumVitae;
import com.lowagie.text.DocumentException;
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
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource("").getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext(CurriculumVitae cv) {
        Context context = new Context();
        context.setVariable("cv", cv);
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("pdf-template", context);
    }

}
