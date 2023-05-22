package pl.zajonz.currencyconsumer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currency")
public class CurrencyMessage {
    @Id
    private String currency;
    private LocalDate timestamp;
    private Double bid;
    private Double ask;

    @Override
    public String toString() {
        return "CurrencyMessage{" +
                "currency='" + currency + '\'' +
                ", timestamp=" + timestamp +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }

}
