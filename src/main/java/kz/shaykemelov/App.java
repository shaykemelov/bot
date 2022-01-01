package kz.shaykemelov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App
{
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) throws TelegramApiException
    {
        if (args.length != 2)
        {
            throw new IllegalArgumentException("Usage: java -jar bot.jar <bot name> <bot token>");
        }

        LOG.info("Initialization has been started");
        final var shaykemelovTestBot = new ShaykemelovTestBot(args[0], args[1]);

        final var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(shaykemelovTestBot);
        LOG.info("Initialization has been completed");
    }
}
