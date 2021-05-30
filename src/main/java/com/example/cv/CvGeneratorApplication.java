package com.example.cv;

import com.example.cv.entity.*;
import com.example.cv.repository.CurriculumRepository;
import com.example.cv.repository.SkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDate;

@SpringBootApplication
public class CvGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvGeneratorApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CurriculumRepository cvRepository, SkillRepository skillRepository) {
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
            experience.setLocation("Vannes");
            experience.setYear(2020);
            experience.setDuration(2);
            experience.setDescription(
                    "- Développement d'un module d'agrégation bancaire\n" +
                    "- App. web : php, html, css, js, PostgreSql, Git, Gitea\n" +
                    "- Travail en équipe");
            cv.addExperience(experience);

            experience = new Experience();
            experience.setTitle("Stage en entreprise");
            experience.setSociety("Start-Up Iole Solutions");
            experience.setLocation("Vannes (56)");
            cv.addExperience(experience);

            skillRepository.findAllByTypeOrderByName(SkillType.LANGUAGE.label).forEach(cv::addSkill);
            skillRepository.findAllByTypeOrderByName(SkillType.FRAMEWORK.label).forEach(cv::addSkill);
            skillRepository.findAllByTypeOrderByName(SkillType.DATABASE.label).forEach(cv::addSkill);

            Information information = new Information();
            information.setBirthDate(LocalDate.of(1982, 9, 20));
            information.setMobility("Bretagne");
            information.setPhone("06 28 33 81 29");
            information.setEmail("greg.verissimo@gmail.com");
            information.setLinkedIn("www.linkedin.com/in/gverissi");
            information.setGitHub("https://github.com/gverissi");
            cv.setInformation(information);

            cvRepository.save(cv);
        };
    }

}
