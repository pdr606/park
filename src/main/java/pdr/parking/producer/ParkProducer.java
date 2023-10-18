package pdr.parking.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pdr.parking.dto.parkDto.ParkingPlateRequestDto;

@Component
public class ParkProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.plate.park}")
    private String routingKey;

    public ParkProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void generateDetranTrafficMult(String plate){
        var plateDto = new ParkingPlateRequestDto(plate);
        rabbitTemplate.convertAndSend("", routingKey, plateDto);
    }
}
