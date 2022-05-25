package com.example.backendtheater;

import com.example.backendtheater.user.User;
import com.example.backendtheater.user.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Controller
public class MainController {
    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String landingPage() {
        System.out.println("Main Controller // " +
                "Now viewing index.html");
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/user")
    public String home() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    //TODO send current user ID in purchase data
    @GetMapping("/confirmpurchase")
    public String confirmPurchase(@RequestParam String id) {
        try {
            String encoding = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
            var client = HttpClient.newHttpClient();

            var request = HttpRequest.newBuilder(
                            URI.create("https://api-m.sandbox.paypal.com/v2/payments/captures/" + id))
                    .header("accept", "application/json")
                    .header(HttpHeaders.AUTHORIZATION, "Basic " + encoding)
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() < 200 || response.statusCode() >= 300) {
                return purchaseError();
            }

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            if(!validPurchase(jsonObject)) {
                return purchaseError();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return purchaseError();
        }

        return completePurchase();
    }

    private String completePurchase() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("gensparkmovies@gmail.com");
            message.setTo("duncan@clotfelter.net");
            message.setSubject("Thank you for your purchase!");
            message.setText("Don't forget to collect your free popcorn! It's on the house!");
            BackendTheaterApplication.getJavaMailSender().send(message);
        } catch(Exception e) {e.printStackTrace();}

        return "Thanks for your purchase!";//TODO template
    }

    //TODO more validation
    private boolean validPurchase(JsonObject purchase) {
        return (
            purchase.get("status").getAsString().equals("COMPLETED")
        );
    }

    //TODO template
    @GetMapping("/error")
    private String purchaseError() {
        return "We are sorry, but there was an issue validating your purchase. Please try again.";
    }
}