package pl.zajonz.currencyconsumer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pl.zajonz.currencyconsumer.model.CurrenciesMessage;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfiguration {

    @Value("${currencies.queue}")
    private String queueName;

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("exchange.headers");
    }

    @Bean
    public Binding currenciesBinding(Queue queue, HeadersExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .where("type")
                .matches("currencies");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Jackson2JsonMessageConverter json = new Jackson2JsonMessageConverter(mapper);
        json.setClassMapper(classMapper());
        return json;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("CurrenciesMessage", CurrenciesMessage.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setConcurrentConsumers(2);
        container.setMaxConcurrentConsumers(2);
        container.setTaskExecutor(taskExecutor());
        return container;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setThreadNamePrefix("RabbitMQ-");
        executor.initialize();
        return executor;
    }

}
