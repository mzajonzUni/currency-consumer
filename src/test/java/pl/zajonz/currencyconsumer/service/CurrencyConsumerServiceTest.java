package pl.zajonz.currencyconsumer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajonz.currencyconsumer.model.CurrenciesMessage;
import pl.zajonz.currencyconsumer.repository.CurrencyConsumerRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CurrencyConsumerServiceTest {

    @InjectMocks
    private CurrencyConsumerService currencyConsumerService;
    @Mock
    private CurrencyConsumerRepository currencyConsumerRepository;
    @Captor
    private ArgumentCaptor<CurrenciesMessage> messageCaptor;

    @Test
    void testListen(){
        //given
        CurrenciesMessage currenciesMessage = new CurrenciesMessage("PLN", LocalDate.now(),4.4,4.5);

        //when
        currencyConsumerService.listen(currenciesMessage);

        //then
        verify(currencyConsumerRepository, times(1)).save(messageCaptor.capture());
        CurrenciesMessage captured = messageCaptor.getValue();

        assertEquals(currenciesMessage.getCurrency(),captured.getCurrency());
        assertEquals(currenciesMessage.getTimestamp(),captured.getTimestamp());
        assertEquals(currenciesMessage.getBid(),captured.getBid());
        assertEquals(currenciesMessage.getAsk(),captured.getAsk());
    }

}