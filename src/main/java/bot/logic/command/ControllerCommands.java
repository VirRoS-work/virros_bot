package bot.logic.command;

import java.util.List;

public class ControllerCommands {

    private List<Command> commands;

    public Command getCommandByText(String str){

        for (Command command : commands){
            if(command.getTextCommand().equals(str)) return command;
        }

        return null;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return commands;
    }
}
