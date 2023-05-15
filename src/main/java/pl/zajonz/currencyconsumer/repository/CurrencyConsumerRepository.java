package pl.zajonz.currencyconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zajonz.currencyconsumer.model.CurrenciesMessage;

public interface CurrencyConsumerRepository extends JpaRepository<CurrenciesMessage,String> {

}
