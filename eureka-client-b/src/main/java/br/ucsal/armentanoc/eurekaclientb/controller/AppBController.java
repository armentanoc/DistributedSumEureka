package br.ucsal.armentanoc.eurekaclientb.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
public class AppBController {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/sumWithRandomFromAppC")
    public Double sumWithRandomFromAppC() {
        Double randomNumberFromC = getRandomDoubleFromC();
        Double randomNumberFromB = new Random().nextDouble(1, 100);
        Double sum = randomNumberFromB + randomNumberFromC;

        System.out.println("Número aleatório gerado na App C: "+randomNumberFromC);
        System.out.println("Número aleatório gerado na App B: "+randomNumberFromB);
        System.out.println("Soma calculada: "+sum);
        return sum;
    }

    private Double getRandomDoubleFromC() {

        InstanceInfo instance = checkIfAppCExists();
        HttpRequest request;
        HttpResponse<String> response;

        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI("http://" + instance.getIPAddr() + ":" + instance.getPort() + "/randomNumber"))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    
        try {
            response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            return Double.parseDouble(responseBody);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private InstanceInfo checkIfAppCExists() {
        String idAppC = "ME003501.mshome.net:eureka-client-c:8083";
        List<InstanceInfo> instances = eurekaClient.getInstancesById(idAppC);
        if (instances.isEmpty()) 
            throw new RuntimeException("AppC not found");
        return instances.get(0);
    }

    ///////////////////////////////////////////////////////////////

    @GetMapping("/health")
    public String healthy() {
        return "Estou vivo e bem! Sou a app " + appName + " - " + LocalDateTime.now();
    }

    @GetMapping("/discover")
    public String discover() {
        Applications otherApps = eurekaClient.getApplications();
        return otherApps.getRegisteredApplications().toString();
    }

    @PostMapping("/receiveCall/{name}")
    public String receiveCall(@PathVariable String name, @RequestBody String message) {
        return message + "\nOlá " + name + ". Aqui é " + appName + " e recebi sua mensagem.";
    }

    @GetMapping("/makeCall/{name}")
    public String makeCall(@PathVariable String name) throws URISyntaxException {
        String message = "Olá, tem alguém aí?!";

        List<InstanceInfo> instances = eurekaClient.getInstancesById(name);

        InstanceInfo instance = instances.get(0);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://" + instance.getIPAddr() + ":" + instance.getPort() + "/receiveCall/" + appName))
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .build();
        try {
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
