package pl.zajonz.currencyconsumer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajonz.currencyconsumer.model.CurrencyMessage;
import pl.zajonz.currencyconsumer.repository.CurrencyRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CurrencyConsumerServiceTest {

    @InjectMocks
    private CurrencyService currencyConsumerService;
    @Mock
    private CurrencyRepository currencyRepository;
    @Captor
    private ArgumentCaptor<CurrencyMessage> messageCaptor;

    @Test
    void testListen() {
        //given
        CurrencyMessage currenciesMessage = new CurrencyMessage("PLN", LocalDate.now(), 4.4, 4.5);

        //when
        currencyConsumerService.saveCurrencies(currenciesMessage);

        //then
        verify(currencyRepository, times(1)).save(messageCaptor.capture());
        CurrencyMessage captured = messageCaptor.getValue();

        assertEquals(currenciesMessage.getCurrency(), captured.getCurrency());
        assertEquals(currenciesMessage.getTimestamp(), captured.getTimestamp());
        assertEquals(currenciesMessage.getBid(), captured.getBid());
        assertEquals(currenciesMessage.getAsk(), captured.getAsk());
    }

}