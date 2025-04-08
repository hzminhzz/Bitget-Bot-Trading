# Bitget Telegram Bot

This is an automated trading bot that integrates Bitget's trading API with Telegram signals. It listens for trading signals via Telegram and executes orders on Bitget.

## Prerequisites

- Java 21
- Maven
- Bitget account (API credentials)
- Telegram account and bot token

## Environment Variables

Before running, set the following environment variables:

BITGET_API_KEY=
BITGET_API_SECRET=
BITGET_API_PASSPHRASE=
TELEGRAM_BOT_USERNAME=
TELEGRAM_BOT_TOKEN=

## Build and Run

1. Build the project using Maven:

mvn clean package


2. Run the executable jar:

java -jar target/bitget-telegram-bot-0.0.1-SNAPSHOT.jar


Your bot should now be running and listening for trading signals on Telegram.
