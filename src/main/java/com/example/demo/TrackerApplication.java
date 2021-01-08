package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.enums.Gender;
import com.example.demo.enums.LifeStyle;
import com.example.demo.enums.Specialization;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class TrackerApplication implements CommandLineRunner {


        @Autowired
        private CoachFlxRepository coachFlxRepository;

        @Autowired
        private LinkFlxRepository linkFlxRepository;

        @Autowired
        private ClientFlxRepository clientFlxRepository;

        @Autowired
        private DocumentFlxRepository documentFlxRepository;

        @Autowired
        private TrackingFlxRepository trackingFlxRepository;
//
//        @Autowired
//        private UserFlxRepository userFlxRepository;
//        @Autowired
//        private AuthGroupRepository authGroupRepository;

        @Override
        public void run(String... args) throws Exception {
            createData();
        }

        private void createData() {
            String str = "2015-03-31";

            CoachFlx coachFlx1 = new CoachFlx("Mike", "Taylor", Specialization.FITNESSCOACH, "mike.taylor@email.com",
                    "000001");
            CoachFlx coachFlx2 = new CoachFlx("Sara", "Skat", Specialization.DIETITIAN, "sara.skat@email.com", "000002");
            CoachFlx coachFlx3 = new CoachFlx("Michael", "Douglas", Specialization.FITNESSCOACH, "michael.daouglas@email.com",
                    "000003");

            coachFlxRepository.save(coachFlx1);
            coachFlxRepository.save(coachFlx2);
            coachFlxRepository.save(coachFlx3);

            for (int i = 1; i < 3; i++) {
                LinkFlx linkFlx = new LinkFlx("Streching_" + i, "http://test.com/Streching_" + i, coachFlx1);
                linkFlxRepository.save(linkFlx);
            }
            for (int i = 1; i < 3; i++) {
                LinkFlx linkFlx = new LinkFlx("Zumba_" + i, "http://test.com/Zumba_" + i, coachFlx2);
                linkFlxRepository.save(linkFlx);
            }

//
//        for (int i = 1; i < 50; i++) {
//            //Date registerDate = Date.valueOf("02/11/2020");
//            ClientFlx clientFlx  = new ClientFlx(
//                    "ClientFirstName_" + i,
//                    "Surname_"+i,
//                    50+i*2,
//                    160+i,
//                    "email_"+i+"@mail.com",
//                    "00000"+i,
//                    "diet_"+i,
//                    Date.valueOf(str),
//                    null,
//                    null,
//                    coachFlx1 );
//            clientFlxRepository.save(clientFlx);
//        }

//        for (int i = 1; i < 10; i++) {
//            ClientFlx clientFlx = new ClientFlx("ClientFirstName_" + i, "Surname_"+i, 50+i*2, 160+i, "email_"+i+"@mail.com", "00000"+i, "diet_"+i, Date.valueOf(str), i%2==0? Gender.MALE:Gender.FEMALE, i%2==0? LifeStyle.ACTIVE:LifeStyle.SLOW,coachFlx2 );
//            clientFlxRepository.save(clientFlx);
//        }

            ClientFlx clientFlx1 = new ClientFlx(
                    "Ben", "Ben", 76,
                    168, "email@mail.com", "0000012",
                    "diet", Date.valueOf(str), Gender.MALE,
                    LifeStyle.ACTIVE, coachFlx1);

            ClientFlx clientFlx2 = new ClientFlx(
                    "Ben2", "Ben2", 77,
                    160, "email2@mail.com", "00000134",
                    "diet2", Date.valueOf(str), Gender.MALE,
                    LifeStyle.ACTIVE, coachFlx2);

            ClientFlx clientFlx3 = new ClientFlx(
                    "Ben3", "Ben3", 70,
                    165, "email3@mail.com", "0000013",
                    "diet3", Date.valueOf(str), Gender.MALE,
                    LifeStyle.ACTIVE, coachFlx3);

            clientFlxRepository.save(clientFlx1);
            clientFlxRepository.save(clientFlx2);
            clientFlxRepository.save(clientFlx3);


            DocumentFlx documentFlx1 = new DocumentFlx(clientFlx1, "bloodtest", "http://test.com/Streching");
            documentFlxRepository.save(documentFlx1);

            DocumentFlx documentFlx2 = new DocumentFlx(clientFlx2, "checkup", "http://test.com/Zumba");
            documentFlxRepository.save(documentFlx2);

            DocumentFlx documentFlx3 = new DocumentFlx(clientFlx3, "blood3", "http://test.com/Zumba");
            documentFlxRepository.save(documentFlx3);

            TrackingFlx trackingFlx1 = new TrackingFlx(clientFlx1, Date.valueOf(str), 3,  2, 5, "I am good",
                    "String coachNote", false, "motivationalMessagePath");

            TrackingFlx trackingFlx2 = new TrackingFlx(clientFlx2, Date.valueOf(str), 4,  1, 1, "I am good",
                    "String coachNote2", true, "motivationalMessagePath2");

            TrackingFlx trackingFlx3 = new TrackingFlx(clientFlx3, Date.valueOf(str), 5,  3, 2, "I am good",
                    "String coachNote3", true, "motivationalMessagePath3");

            trackingFlxRepository.save(trackingFlx1);
            trackingFlxRepository.save(trackingFlx2);
            trackingFlxRepository.save(trackingFlx3);

//            UserFlx userFlx1=new UserFlx("tom", "$2a$09$RWNZYTvcXsYmcg/amv/4zOv8EW3dAqMlzO5Wp7Zmjgo2UFEgpObxq","tom@email.com");
//            UserFlx userFlx2=new UserFlx("sam", "$2a$09$RWNZYTvcXsYmcg/amv/4zOv8EW3dAqMlzO5Wp7Zmjgo2UFEgpObxq","sam@email.com");
//            UserFlx userFlx3=new UserFlx("gil", "$2a$09$RWNZYTvcXsYmcg/amv/4zOv8EW3dAqMlzO5Wp7Zmjgo2UFEgpObxq","gil@email.com");
//            userFlxRepository.save(userFlx1);
//            userFlxRepository.save(userFlx2);
//            userFlxRepository.save(userFlx3);

        }
   public static void main(String[] args) {
        SpringApplication.run(TrackerApplication.class, args);
    }

}
