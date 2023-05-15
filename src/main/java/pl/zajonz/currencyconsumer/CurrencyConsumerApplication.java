package pl.zajonz.currencyconsumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableRabbit
public class CurrencyConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConsumerApplication.class, args);
    }

}
