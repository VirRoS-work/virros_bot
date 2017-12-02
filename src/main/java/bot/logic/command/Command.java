package bot.logic.command;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface Command {

    SendMessage execute(Update update);

    String getTextCommand();

    String getInfoCommand();
}
