package kz.shaykemelov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ShaykemelovTestBot extends TelegramLongPollingBot
{
    private static final Logger LOG = LoggerFactory.getLogger(ShaykemelovTestBot.class);

    private final String botUsername;

    private final String botToken;

    public ShaykemelovTestBot(String botUsername, String botToken)
    {
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername()
    {
        return botUsername;
    }

    @Override
    public String getBotToken()
    {
        return botToken;
    }

    @Override
    public void onUpdateReceived(final Update update)
    {
        if (update.hasMessage() && update.getMessage().hasText())
        {
            LOG.info("New message has been received: {}", update);

            final var sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText("Echo: " + update.getMessage().getText());

            try
            {
                execute(sendMessage);
                LOG.info("Response has been sent: {}", sendMessage);
            }
            catch (final TelegramApiException e)
            {
                LOG.error("Could not execute send message: {}", sendMessage, e);
            }
        }
    }
}
