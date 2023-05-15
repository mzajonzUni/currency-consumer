package pl.zajonz.currencyconsumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CurrencyConsumerService {
    
    @RabbitListener(queues = "currencies")
    public void rabbitListener(String s){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        System.out.println(gson.fromJson(s,CurrenciesMessage.class).toString());
    }


}
