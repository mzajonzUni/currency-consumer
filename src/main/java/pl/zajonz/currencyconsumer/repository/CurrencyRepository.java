package pl.zajonz.currencyconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zajonz.currencyconsumer.model.CurrencyMessage;

public interface CurrencyRepository extends JpaRepository<CurrencyMessage,String> {

}
