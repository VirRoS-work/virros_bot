package bot.logic.command;

import base.model.Buy;
import base.service.GenericService;
import helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class CommandBought implements Command {

    private final String textCommand = "/bought";

    @Autowired
    private StringHelper stringHelper;

    @Qualifier("buyService")
    @Autowired
    private GenericService<Buy, Integer> service;

    @Override
    public SendMessage execute(Update update) {

        List<String> parametrs = stringHelper.cutAdditionalParametrs(update.getMessage().getText());

        if (parametrs.size() >= 1) {

            List<Buy> buys = new ArrayList<Buy>();

            for (Buy buy : service.getAllObjects()){
                if(buy.getChat_id() == update.getMessage().getChatId()) buys.add(buy);
            }

            StringBuilder products = new StringBuilder();

            for (String str : parametrs){
                for (Buy buy : buys){
                    if(buy.getName_product().equals(str)){
                        products.append(buy.getName_product() + ", ");
                        service.delete(buy.getId());
                    }
                }
            }

            if (products.length() != 0) {

                return new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText(update.getMessage().getFrom().getFirstName() + ", я удалил из списка " +
                                "товары: " + products.substring(0, products.length() - 2) + ".");

            } else {

                return new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Извините, я не нашел этого в списке покупок!");

            }
        } else {

            for (Buy buy : service.getAllObjects()) {
                if (buy.getChat_id() == update.getMessage().getChatId()) service.delete(buy.getId());
            }

            return new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getFrom().getFirstName() + ", я рад, что Вы все купили!");

        }

    }

    @Override
    public String getTextCommand() {
        return textCommand;
    }

    @Override
    public String getInfoCommand() {
        return "[products] Убирает купленные товары из списка покупок";
    }

    public void setStringHelper(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }
}
