package ru.tsconsulting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tsconsulting.soap.SOAPConnector;
import ru.tsconsulting.soap.model.ya.CheckTextRequest;
import ru.tsconsulting.soap.model.ya.CheckTextResponse;
import ru.tsconsulting.soap.model.ya.SpellError;
import ru.tsconsulting.soap.model.ya.SpellResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    CommandLineRunner lookup(SOAPConnector soapConnector) {
        return strings -> {
            try {
                CheckTextRequest request = new CheckTextRequest();
                request.setText("Домек");
                request.setLang("ru");
                CheckTextResponse response = (CheckTextResponse) soapConnector.callWebService("http://speller.yandex.net/services/spellservice/checkText", request);
                System.out.println("*********************************************************************");
                SpellResult spellResult = response.getSpellResult();
                List<SpellError> errors = spellResult.getError();
                for (SpellError error : errors) {
                    System.out.println("Варианты написания:");
                    error.getS().forEach(System.out::println);
                }
                System.out.println("*********************************************************************");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }


}
