package ru.tsconsulting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tsconsulting.soap.SOAPConnector;
import ru.tsconsulting.soap.model.ya.CheckTextRequest;
import ru.tsconsulting.soap.model.ya.CheckTextResponse;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    CommandLineRunner lookup(SOAPConnector soapConnector) {
        return strings -> {
            CheckTextRequest request;
            try {
                request = new CheckTextRequest();
                request.setText("Домек");
                request.setLang("ru");
                CheckTextResponse response = (CheckTextResponse) soapConnector.callWebService("https://speller.yandex.net/services/spellservice", request);
                System.out.println(response.getSpellResult());
            } catch (Exception e) {
                System.out.println(e);
            }
        };
    }


}
