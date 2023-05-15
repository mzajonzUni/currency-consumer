package pl.zajonz.currencyprovider.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.zajonz.currencyprovider.model.CurrenciesMessage;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfiguration {
    static final String queueName = "currencies";

    @Bean
    public Queue queue() {
        return new Queue(queueName, false,false,true);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("exchange.headers");
    }

    @Bean
    public Binding currenciesBinding(){
        return BindingBuilder.bind(queue()).to(headersExchange()).where("type").matches("currencies");
    }

    @Bean
    MessageConverter messageConverter(ObjectMapper mapper){
        mapper.findAndRegisterModules();
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter(mapper);
        jsonConverter.setClassMapper(classMapper());
        return jsonConverter;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("CurrenciesMessage", CurrenciesMessage.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

}
