package bot;

import bot.logic.command.Command;
import bot.logic.command.ControllerCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class VirrosBot extends TelegramLongPollingBot {

    private ControllerCommands controllerCommands;

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage sendMessage;

            Command command = controllerCommands.getCommandByText(cutCommand(update.getMessage().getText()));

            if (command != null) {
                sendMessage = command.execute(update);
            } else {
                sendMessage = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Unknown command! ");
            }

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "virros_bot";
    }

    @Override
    public String getBotToken() {
        return "455066013:AAGofSGH6xrBQJKtPTMSuf4DKe0GI2l5Dnk";
    }

    //Deprecated

    private String cutCommand(String text) {

        int position = text.indexOf(' ');

        if (position != -1) return text.substring(0, position);
        else return text;
    }

    private String cutAdditionalParametrs(String text) {

        int position = text.indexOf(' ');

        if (position != -1) return text.substring(position + 1);
        else return "";
    }

    public void setControllerCommands(ControllerCommands controllerCommands) {
        this.controllerCommands = controllerCommands;
    }
}
