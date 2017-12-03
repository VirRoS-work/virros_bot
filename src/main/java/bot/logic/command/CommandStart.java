package bot.logic.command;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class CommandStart implements Command {

    private final String textCommand = "/start";

    @Override
    public SendMessage execute(Update update) {
        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Здравствуй, " + update.getMessage().getFrom().getFirstName() + "! Познакомиться с моими " +
                        "возможностями Вы можете по команде /commands =)");
    }

    @Override
    public String getTextCommand() {
        return textCommand;
    }

    @Override
    public String getInfoCommand() {
        return "Приветственная команда.";
    }
}
