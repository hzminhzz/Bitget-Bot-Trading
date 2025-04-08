package com.example.bitget.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class BitgetApiClient {

    private final HttpClient httpClient;
    private final String apiKey;
    private final String apiSecret;
    private final String passphrase;

    public BitgetApiClient(String apiKey, String apiSecret, String passphrase) {
        this.httpClient = HttpClient.newHttpClient();
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.passphrase = passphrase;
    }
    
    /**
     * Generate a signature for Bitget API requests.
     */
    private String generateSignature(String timestamp, String method, String requestPath, String body) {
        try {
            String payload = timestamp + method.toUpperCase() + requestPath + (body != null ? body : "");
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(apiSecret.getBytes("UTF-8"), "HmacSHA256");
            mac.init(secretKey);
            byte[] hash = mac.doFinal(payload.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error generating signature", e);
        }
    }
    
    /**
     * Place a new order on Bitget.
     * This method supports a simple limit order.
     */
    public String placeOrder(String symbol, String side, String type, String size, String price) throws Exception {
        String baseUrl = "https://api.bitget.com";
        String requestPath = "/api/spot/v1/trade/orders";
        String method = "POST";
        
        String body = String.format("{\"symbol\":\"%s\",\"side\":\"%s\",\"type\":\"%s\",\"size\":\"%s\",\"price\":\"%s\"}", 
                                      symbol, side, type, size, price);
        String timestamp = String.valueOf(Instant.now().getEpochSecond());
        String signature = generateSignature(timestamp, method, requestPath, body);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + requestPath))
                .header("Content-Type", "application/json")
                .header("ACCESS-KEY", apiKey)
                .header("ACCESS-SIGN", signature)
                .header("ACCESS-TIMESTAMP", timestamp)
                .header("ACCESS-PASSPHRASE", passphrase)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    
    // Additional methods such as cancelOrder or getOrderStatus can be added as needed.
}
