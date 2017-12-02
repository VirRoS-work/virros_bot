package bot;

import bot.logic.command.Command;
import bot.logic.command.ControllerCommands;
import helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class VirrosBot extends TelegramLongPollingBot {

    @Autowired
    private ControllerCommands controllerCommands;

    @Autowired
    private StringHelper sh;

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage sendMessage;

            Command command = controllerCommands.getCommandByText(sh.cutCommand(update.getMessage().getText()));

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

    public void setControllerCommands(ControllerCommands controllerCommands) {
        this.controllerCommands = controllerCommands;
    }

    public void setSh(StringHelper sh) {
        this.sh = sh;
    }

}
