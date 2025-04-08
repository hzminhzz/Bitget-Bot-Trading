package com.example.trading;

import com.example.bitget.api.BitgetApiClient;

public class TradingService {
    private final BitgetApiClient apiClient;

    public TradingService(BitgetApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void executeBuyOrder(String symbol, String size, String price) {
        try {
            String response = apiClient.placeOrder(symbol, "buy", "limit", size, price);
            System.out.println("Buy order response: " + response);
        } catch (Exception e) {
            System.err.println("Error executing buy order: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void executeSellOrder(String symbol, String size, String price) {
        try {
            String response = apiClient.placeOrder(symbol, "sell", "limit", size, price);
            System.out.println("Sell order response: " + response);
        } catch (Exception e) {
            System.err.println("Error executing sell order: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
