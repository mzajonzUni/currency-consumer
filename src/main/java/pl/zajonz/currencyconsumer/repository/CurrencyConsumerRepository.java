package pl.zajonz.currencyconsumer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zajonz.currencyconsumer.model.Currency;

public interface CurrencyConsumerRepository extends JpaRepository<Currency,String> {



}
