package bot.logic.command;

import base.model.Buy;
import base.service.BuyService;
import base.service.GenericService;
import helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class CommandBuy implements Command {

    private final String textCommand = "/buy";

    @Autowired
    private StringHelper stringHelper;

    @Qualifier("buyService")
    @Autowired
    private GenericService<Buy, Integer> service;

    @Override
    public SendMessage execute(Update update) {

        List<String> parametrs = stringHelper.cutAdditionalParametrs(update.getMessage().getText());

        if (parametrs.size() >= 1) {

            Buy buy = new Buy();
            buy.setName_product(parametrs.get(0));

            if (parametrs.size() >= 2) {
                buy.setCount_product(parametrs.get(1));

                if (parametrs.size() >= 3) {
                    buy.setMeasurement_product(parametrs.get(2));
                }
            }

            buy.setChat_id(update.getMessage().getChatId());

            service.create(buy);

            return new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getFrom().getFirstName() + ", я сделал запись в список покупок!");

        } else {

            List<Buy> buys = new ArrayList<Buy>();

            for (Buy buy : service.getAllObjects()) {
                if (buy.getChat_id() == update.getMessage().getChatId()) buys.add(buy);
            }

            if (buys.size() > 0) {
                StringBuilder products = new StringBuilder("Вам необходимо купить:\n");
                int count = 1;

                for (Buy buy : buys) {
                    products.append(count++ + " - " + buy.getName_product() + " " + buy.getCount_product() + " " + buy.getMeasurement_product());
                }

                return new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText(products.toString());
            } else {
                return new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText(update.getMessage().getFrom().getFirstName() + ", Ваш список покупок пуст!");
            }
        }

    }

    @Override
    public String getTextCommand() {
        return textCommand;
    }

    @Override
    public String getInfoCommand() {
        return "[parametrs] Работа со списком покупок.";
    }

    public void setStringHelper(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

}
