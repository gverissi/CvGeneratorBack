package com.example.cv;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Experience;
import com.example.cv.entity.Image;
import com.example.cv.entity.Person;
import com.example.cv.repository.CurriculumRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class CvGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvGeneratorApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CurriculumRepository cvRepository) {
        return args -> {
            CurriculumVitae cv = new CurriculumVitae();
            cv.setName("CV_dev");

            Person person = new Person();
            person.setFirstName("Greory");
            person.setLastName("Verissimo");
            person.setJob("Développeur Java");
            person.setProfile("Issu d'un cursus scientifique (Docteur en physique), j’ai effectué une reconversion professionnelle pour devenir concepteur développeur d'applications.");
            cv.setPerson(person);

            BufferedImage bImage = ImageIO.read(new File("C:\\Users\\gregv\\Pictures\\portrait.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );
            byte[] data = bos.toByteArray();
            Image image = new Image();
            image.setContent(data);
            image.setName("portrait.jpg");
            cv.setImage(image);

            Experience experience = new Experience();
            experience.setTitle("Stage en entreprise");
            experience.setSociety("Start-Up Iole Solutions");
            experience.setLocation("Vannes (56)");
            cv.addExperience(experience);

            experience = new Experience();
            experience.setTitle("Stage en entreprise");
            experience.setSociety("Start-Up Iole Solutions");
            experience.setLocation("Vannes (56)");
            cv.addExperience(experience);

            cvRepository.save(cv);
        };
    }

}
