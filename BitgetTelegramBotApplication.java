package com.example;

import com.example.bitget.api.BitgetApiClient;
import com.example.trading.TradingService;
import com.example.telegram.SignalBot;

public class BitgetTelegramBotApplication {

    public static void main(String[] args) {
        // Retrieve Bitget API credentials from environment variables.
        String bitgetApiKey = System.getenv("BITGET_API_KEY");
        String bitgetApiSecret = System.getenv("BITGET_API_SECRET");
        String bitgetPassphrase = System.getenv("BITGET_API_PASSPHRASE");
        
        if (bitgetApiKey == null || bitgetApiSecret == null || bitgetPassphrase == null) {
            System.err.println("Missing Bitget API credentials in environment variables.");
            System.exit(1);
        }
        
        // Initialize Bitget API client.
        BitgetApiClient bitgetApiClient = new BitgetApiClient(bitgetApiKey, bitgetApiSecret, bitgetPassphrase);
        
        // Initialize TradingService.
        TradingService tradingService = new TradingService(bitgetApiClient);
        
        // Initialize Telegram bot and register it.
        SignalBot bot = new SignalBot(tradingService);
        SignalBot.initBot(bot);
        
        System.out.println("Bitget Telegram Bot started...");
    }
}
