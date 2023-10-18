package pdr.parking.producer;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pdr.parking.dto.parkDto.ParkingPlateRequestDto;

@Component
public class ParkProducer {
    private final RabbitTemplate rabbitTemplate;

    public ParkProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.plate.park}")
    private String routingKey;

    public void generateDetranTrafficMult(String plate){
        var plateDto = new ParkingPlateRequestDto(plate);
        rabbitTemplate.convertAndSend("", routingKey, plateDto);
    }
}
