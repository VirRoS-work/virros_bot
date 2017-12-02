import base.model.Buy;
import base.service.BuyService;
import base.service.GenericService;
import bot.VirrosBot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring-config.xml");
        VirrosBot bot = (VirrosBot) applicationContext.getBean("VirrosBot");

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        System.out.println("Bot started...");

    }
}
