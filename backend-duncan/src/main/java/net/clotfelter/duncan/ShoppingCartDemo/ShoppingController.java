package net.clotfelter.duncan.ShoppingCartDemo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.clotfelter.duncan.ShoppingCartDemo.entities.Cart;
import net.clotfelter.duncan.ShoppingCartDemo.entities.products.Ticket;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@RestController
public class ShoppingController extends WebSecurityConfigurerAdapter {
    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(a -> a
                        .antMatchers("/", "/error", "/webjars/**", "/index.html").permitAll()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .oauth2Login();

        http.csrf().disable();
    }

    //Legacy
    @Deprecated
    @GetMapping("/api")
    public String mainPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return "-1";
    }

    @GetMapping("/api/myfilms")
    public List getUserTickets(@AuthenticationPrincipal OAuth2User principal) {
        List<String[]> toReturn = new ArrayList<>();
        try (var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM Cart WHERE user_id = :uid");
            query.setParameter("uid", principal.getName());
            List<Ticket> tickets = new ArrayList<>();
            for(Object o : query.list()) {
                if(o instanceof Cart c) {
                    tickets.addAll(c.getTickets());
                }
            }
            //Sure would be nice if this worked, wouldn't it, Hibernate?
            //return query.list().stream().flatMap(c -> ((Cart)c).getTickets()).toList();
            return tickets;
        }
    }

    @GetMapping("/api/user")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        return principal == null ? "null" : principal.getAttributes().toString();
    }

    @PostMapping("/api/confirmpurchase")
    public String confirmPurchasePost(@RequestBody Cart c, @AuthenticationPrincipal OAuth2User principal) {
        try {
            c.setUserId(principal.getName());
            String paypalOfficialDetails = getPaymentJson(JsonParser.parseString(c.getPaymentDetails()).getAsJsonObject().get("paymentID").getAsString(), c.getTotal());
            if (paypalOfficialDetails == null) {
                return purchaseError();
            }
            c.setPaymentDetails(paypalOfficialDetails);

            //Save payment
            try (var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession()) {
                var tx = session.beginTransaction();
                for(Ticket t : c.getTickets()) {
                    session.saveOrUpdate(t);
                }
                session.save(c);
                tx.commit();
            }
        } catch(Exception e) {e.printStackTrace(); return purchaseError();}

        sendReceipt(principal.getAttribute("email"));
        return "Thanks for your purchase!";
    }

    //Legacy, only supports 1 ticket, changed to POST
    @Deprecated
    @GetMapping("/api/confirmpurchase")
    public String confirmPurchase(@RequestParam String payment, @RequestParam String show, @AuthenticationPrincipal OAuth2User principal) {
        String paymentJson = getPaymentJson(payment, 10);
        return paymentJson != null ?
            completePurchase(paymentJson, payment, show, principal.getAttribute("email")) :
            purchaseError();
    }

    private String getPaymentJson(String paymentId, double requiredCurrency) {
        try {
            String encoding = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
            var client = HttpClient.newHttpClient();

            var request = HttpRequest.newBuilder(
                            URI.create("https://api-m.sandbox.paypal.com/v1/payments/payment/" + paymentId))
                    .header("accept", "application/json")
                    .header(HttpHeaders.AUTHORIZATION, "Basic " + encoding)
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() < 200 || response.statusCode() >= 300) {
                return null;
            }

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            if (
                    jsonObject.get("state").getAsString().equals("approved")
                &&  jsonObject.get("transactions").getAsJsonArray().get(0).getAsJsonObject().get("amount").getAsJsonObject().get("total").getAsDouble() == requiredCurrency
            ) {
                return response.body();
            }
        } catch(Exception e) {e.printStackTrace();}
        return null;
    }

    @Deprecated
    private String completePurchase(String json, String paymentId, String filmId, String email) {
        var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        var tx = session.beginTransaction();
        //Film toWatch = session.get(Film.class, filmId);

        Ticket soldTicket = new Ticket();
        soldTicket.setMoviedbId(paymentId);
        soldTicket.setFilm(filmId);
        soldTicket.setUnits(1);
        soldTicket.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
        soldTicket.setPurchase(json);

        session.saveOrUpdate(soldTicket);
        tx.commit();

        sendReceipt(email);
        return "Thanks for your purchase!";
    }

    public static boolean sendReceipt(String recipient) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("colonialdrivein@yahoo.com");
            message.setTo(recipient);
            message.setSubject("Thank you for your purchase!");
            message.setText("Don't forget to collect your free popcorn! It's on the house!");
            ShoppingCartDemoApplication.getJavaMailSender().send(message);
        } catch(Exception e) {e.printStackTrace(); return false;}
        return true;
    }

    //TODO template
    @GetMapping("/error")
    private String purchaseError() {
        return "We are sorry, but there was an issue validating your purchase. Please contact us to resolve the issue.";
    }

    /*
    @GetMapping("/productsbyid")
    public List<Product> getProductsById(@RequestParam int id) {
        try (var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            var criteria = builder.createQuery(Product.class);
            Root<Product> productRoot = criteria.from(Product.class);
            criteria.where(builder.equal(productRoot.get("id"), id));
            return session.createQuery(criteria).getResultList();
        }
    }

    @GetMapping("/productsbyname")
    public List getProductsByName(@RequestParam String name) {
        try (var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Product.class);
            criteria.add(Restrictions.ilike("name", "%"+name+"%"));
            return criteria.list();
        }
    }

    @GetMapping("/productsbytype")
    public List<Object> getProductsByType(@RequestParam String type) {
        try (var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            var criteria = builder.createQuery();
            criteria.select(criteria.from(Class.forName("net.clotfelter.duncan.ShoppingCartDemo.entities.products."+type)));
            return session.createQuery(criteria).getResultList();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @PostMapping("/cart")
    public void createCart() {
        var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        var tx = session.beginTransaction();
        User u = (User) ShoppingCartDemoApplication.context.getBean("OnlyUser");
        u = session.load(User.class, u.getId());
        if(u.getCart() == null) {
            Cart c = new Cart();
            c.getProducts().add((Product) ShoppingCartDemoApplication.context.getBean("book"));
            c.getProducts().add((Product) ShoppingCartDemoApplication.context.getBean("apparel"));
            u.setCart(c);
            session.saveOrUpdate(c);
            session.saveOrUpdate(u);
            System.out.println("Successfully created new cart: "+c);
        } else {
            System.out.println("Failed to create new cart: already exists!");
        }
        tx.commit();
    }

    @GetMapping("/cart")
    public Cart getCart() {
        var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        var tx = session.beginTransaction();
        User u = (User) ShoppingCartDemoApplication.context.getBean("OnlyUser");
        u = session.load(User.class, u.getId());
        System.out.println("Successfully read cart: "+u.getCart());
        return u.getCart();
    }

    @PutMapping("/cart")
    public void updateCart(@RequestBody Cart updatedCart) {
        var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        var tx = session.beginTransaction();
        session.saveOrUpdate(updatedCart);
        tx.commit();
        System.out.println("Successfully updated cart: "+updatedCart);
    }

    @DeleteMapping("/cart")
    public void deleteCart() {
        var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        var tx = session.beginTransaction();
        User u = (User) ShoppingCartDemoApplication.context.getBean("OnlyUser");
        u = session.load(User.class, u.getId());
        Cart toDelete = u.getCart();
        u.setCart(null);
        session.saveOrUpdate(u);
        session.delete(toDelete);
        tx.commit();
        System.out.println("Successfully deleted cart: "+u.getCart());
    }*/
}
