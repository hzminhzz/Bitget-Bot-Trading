package com.example.telegram;

import com.example.trading.TradingService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class SignalBot extends TelegramLongPollingBot {
    
    private final TradingService tradingService;

    public SignalBot(TradingService tradingService) {
        this.tradingService = tradingService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            // A simple demo: if the message contains "BUY" or "SELL", execute an order.
            if (messageText.contains("BUY")) {
                // You could add more advanced parsing here.
                tradingService.executeBuyOrder("BTCUSDT", "0.001", "20000");
                sendResponse(update, "Buy order placed for BTCUSDT");
            } else if (messageText.contains("SELL")) {
                tradingService.executeSellOrder("BTCUSDT", "0.001", "21000");
                sendResponse(update, "Sell order placed for BTCUSDT");
            }
        }
    }

    private void sendResponse(Update update, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // Return the bot username from environment variable
        return System.getenv("TELEGRAM_BOT_USERNAME");
    }

    @Override
    public String getBotToken() {
        // Return the bot token from environment variable
        return System.getenv("TELEGRAM_BOT_TOKEN");
    }
    
    public static void initBot(SignalBot bot) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
