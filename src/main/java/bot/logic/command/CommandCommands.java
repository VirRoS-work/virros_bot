package bot.logic.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class CommandCommands implements Command{

    private final String textCommand = "/commands";

    @Autowired
    private ControllerCommands controllerCommands;

    @Override
    public SendMessage execute(Update update) {

        StringBuilder listCommand = new StringBuilder("Список команд: \n");

        for (Command command : controllerCommands.getCommands()) {
            listCommand.append("\t" + command.getTextCommand() + " - " + command.getInfoCommand() + "\n");
        }

        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(listCommand.toString());
    }

    @Override
    public String getTextCommand() {
        return textCommand;
    }

    @Override
    public String getInfoCommand() {
        return "Показывает список всех команд.";
    }

    public void setControllerCommands(ControllerCommands controllerCommands) {
        this.controllerCommands = controllerCommands;
    }

    public ControllerCommands getControllerCommands() {
        return controllerCommands;
    }
}
