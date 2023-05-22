package pl.zajonz.currencyconsumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajonz.currencyconsumer.model.CurrencyMessage;
import pl.zajonz.currencyconsumer.repository.CurrencyRepository;

@RequiredArgsConstructor
@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public void saveCurrencies(CurrencyMessage message) {
        currencyRepository.save(message);
    }
}
