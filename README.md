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

# Setup Instructions for Bitget Telegram Bot

This Bitget Telegram Bot integrates Bitget’s trading capabilities with signals received from Telegram channels. Follow these instructions to ensure the system functions correctly.

## Telegram Bot Setup

1. **Create a Telegram Bot:**  
   Follow [Telegram's official documentation](https://core.telegram.org/bots) to create a new bot.

2. **Generate a Bot Token:**  
   After creation, generate and store the bot token. You will use this token in your environment configuration.

## Error Reporting with Telegram

1. **Create a New Bot for Error Reporting:**  
   Follow the same steps as above to create a separate Telegram bot dedicated to error reporting.

2. **Configure an Error Reporting Channel:**  
   Create a troubleshooting channel on Telegram and add the error reporting bot as an admin.  
   Start the system and then send a message to the troubleshooting channel to initialize the error reporting mechanism.

## Telegram Channel Listener

Ensure that your primary trading bot is added as an admin of the Telegram channel you want to monitor for trading signals. This allows the bot to read messages and act on them.

## Database Configuration

This setup uses Azure Key Vault and Azure SQL Database by default. If you prefer to use a local or alternative database, update your application configuration accordingly (e.g., in `application.properties` or your environment variables).

### Azure Key Vault

1. **Create an Azure Key Vault:**  
   Follow the [Azure Key Vault documentation](https://learn.microsoft.com/en-us/azure/key-vault/general/basic-concepts) to set up a new key vault.

2. **Connect to Azure Key Vault:**  
   Use the provided steps in the documentation to connect your application with the key vault. Store sensitive configurations there.

### Azure SQL Database

1. **Create an Azure SQL Database:**  
   Detailed instructions can be found in the [Azure SQL Database documentation](https://learn.microsoft.com/en-us/azure/azure-sql/database/single-database-get-started). A serverless configuration may be a cost-effective option.

2. **Connect to Azure SQL Database:**  
   Follow the connection steps and ensure that your application is configured to use the correct JDBC URL, username, and password.

## Environment Variables

Ensure you set up the following environment variables for your application. Replace the placeholder values with your actual configuration details:

Database Configuration (if using Azure SQL Database)
AZURE_DB_PASSWORD=
AZURE_DB_URL=
AZURE_DB_USERNAME=

Azure Key Vault Configuration
AZURE_KEYVAULT_CLIENT_ID=
AZURE_KEYVAULT_CLIENT_KEY=
AZURE_KEYVAULT_TENANT_ID=
AZURE_KEYVAULT_URL=

Telegram Configuration
TELEGRAM_BOT_TOKEN=
TELEGRAM_BOT_USERNAME=
TELEGRAM_ERROR_TOKEN=
TELEGRAM_ERROR_USERNAME=

Bitget API Credentials
BITGET_API_KEY=
BITGET_API_SECRET=
BITGET_API_PASSPHRASE=


## Usage

The Bitget Telegram Bot monitors specified Telegram channels for trading signals and uses the Bitget API to execute the appropriate trades. Below is an example signal format that the bot can process:

### Trading Signal Example

NKNBTC

ENTRY: 0.00000260 - 0.00000290

TP1: 0.00000315
TP2: 0.00000360
TP3: 0.00000432
TP4: 0.00000486
TP5: 0.00000550
TP6: 0.00000666
TP7: 0.00000741

STOP: Close weekly below 0.00000240


## Optional Parameters: Invest Amount & Sell Later Strategy

The bot allows two optional parameters in trading signals:

- **INVEST:** Specifies how much to invest in the trade (e.g., `INVEST: 0.001`). If omitted, the bot uses the default investment configuration.
- **STRATEGY: SELL_LATER:** When set, the bot will hold the asset instead of selling immediately, triggering the sell order later based on specified conditions (e.g., price spikes or custom alerts).

### How It Works

| Parameter | Description |
|-----------|-------------|
| `INVEST` | Defines the amount allocated for the trade (e.g., `"0.0001 BTC"`). If not provided, the default investment amount is used. |
| `STRATEGY` | If set to `SELL_LATER`, the bot delays automatic selling until later conditions are met. |

### Example Signal with Optional Parameters

NKNBTC

ENTRY: 0.00000260 - 0.00000290

TP1: 0.00000315
TP2: 0.00000360
TP3: 0.00000432
TP4: 0.00000486
TP5: 0.00000550
TP6: 0.00000666
TP7: 0.00000741

STOP: Close weekly below 0.00000240

Optional Parameters
INVEST: 0.001
STRATEGY: SELL_LATER


#### Behavior of `SELL_LATER` Strategy

- **Without SELL_LATER:**  
  The bot automatically sells when one of the predefined profit targets (TP levels) is reached.
  
- **With SELL_LATER Enabled:**  
  The bot holds the asset instead of executing an immediate sell order. The sell order will be executed later when conditions (e.g., a price spike or a custom alert) are met.

#### How to Use These Options

- **No `INVEST` value provided:**  
  Uses the default investment configuration.
  
- **`INVEST` provided:**  
  Overrides the default configuration with the specified investment amount.
  
- **No `SELL_LATER` provided:**  
  The bot sells at the profit targets as soon as they are reached.
  
- **`SELL_LATER` enabled:**  
  The bot holds the asset until further trading logic triggers a sell order.

## Test Coverage

### Running Tests

Execute the test suite using Maven:

mvn test


### Coverage Reporting

After running the tests, generate a coverage report using JaCoCo:

mvn jacoco:report


The coverage report will be available at `target/site/jacoco/index.html`. Open this file in your browser for detailed coverage statistics.

---

Ensure that all prerequisites are met and that each environment variable is correctly set. Once everything is in place, build and run your bot using Maven, and it will start monitoring your chosen Telegram channels and execute trades on Bitget accordingly.
