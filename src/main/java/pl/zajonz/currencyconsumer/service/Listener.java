package pl.zajonz.currencyconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.zajonz.currencyconsumer.model.CurrencyMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class Listener {

    private final CurrencyService currencyService;

    @Async
    @RabbitListener(queues = "${currencies.queue}")
    public void listen(CurrencyMessage message) {
            log.info(message.toString());
            currencyService.saveCurrencies(message);
    }
}
