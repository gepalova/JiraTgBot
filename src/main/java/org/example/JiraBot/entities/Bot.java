package org.example.JiraBot.entities;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private static final String TOKEN = "1373536564:AAGT2z6LVgr7T0OK5vcf25NFhPAxs8fxy9I";
    private static final String USERNAME = "YourOwnJiraBot";

    public Bot() { }

    public String getBotToken() {
        return TOKEN;
    }
    public String getBotUsername() {
        return USERNAME;
    }

    public void onUpdateReceived(Update update) {
        if(update.getMessage() != null && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String receiveMessage = update.getMessage().getText();
            String sendMessage = "";
            switch (receiveMessage) {
                case "/news":
                    sendMessage = "hi, this is all news: ";
                    break;
                case  "/help":
                    sendMessage = "I will help you";
                    /*message.setChatId(update.getMessage().
                            getChatId()).
                            setText("HI " + update.getMessage().getText());*/
                    break;
                default:
                    sendMessage = "HI " + update.getMessage().getText();
                    break;
            }
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(sendMessage);

            setButtons(message);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/news"));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }
}
