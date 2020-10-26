package com.eh.springbootamqp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class SpringbootAmqpApplicationTests {

    @Autowired
    private AmqpAdmin amqpAdmin;


    /**
     * 测试发送消息
     */
    @Test
    void contextLoads() {
        // 创建exchange
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        // 创建queue
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
        // 创建binding
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,
                "amqpadmin.exchange", "amqpadmin.routingKey", null));
        // 解绑
        amqpAdmin.removeBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,
                "amqpadmin.exchange", "amqpadmin.routingKey", null));
        // 删除exchange
        amqpAdmin.deleteExchange("amqpadmin.exchange");
        // 删除queue
        amqpAdmin.deleteQueue("amqpadmin.queue");

    }
}
