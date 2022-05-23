@RestController
public class PaypalController {
    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;

    @GetMapping("/mappingtest")
    public String test(@RequestParam String id) {
        System.out.println("hi");
        return "";
    }


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

            System.out.println(response.statusCode());
            System.out.println(response.body());

            if(response.statusCode() < 200 || response.statusCode() >= 300) {
                return purchaseError();
            }

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            System.out.println(jsonObject.get("status"));
            if(!validPurchase(jsonObject)) {
                return purchaseError();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return purchaseError();
        }

        return "Thanks for your purchase!";
    }
}