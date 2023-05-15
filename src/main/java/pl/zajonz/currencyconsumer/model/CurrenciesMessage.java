package pl.zajonz.currencyconsumer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "currency")
public class CurrencyMessage implements Serializable {

    @Id
    private String currency;
    private LocalDate timestamp;
    private Double bid;
    private Double ask;

    @Override
    public String toString() {
        return "CurrenciesMessage{" +
                "currency='" + currency + '\'' +
                ", timestamp=" + timestamp +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }

}
