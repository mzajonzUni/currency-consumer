package pl.zajonz.currencyconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.zajonz.currencyconsumer.model.CurrenciesMessage;
import pl.zajonz.currencyconsumer.repository.CurrencyConsumerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyConsumerService {

    private final CurrencyConsumerRepository currencyConsumerRepository;

    @Async
    @RabbitListener(queues = "${currencies.queue}")
    public void listen(CurrenciesMessage message) {
            log.info(message.toString());
            currencyConsumerRepository.save(message);
    }
}
