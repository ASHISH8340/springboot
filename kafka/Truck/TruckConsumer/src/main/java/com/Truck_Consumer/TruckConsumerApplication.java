package com.Truck_Consumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

@SpringBootApplication
public class TruckConsumerApplication {

	public static void main(String[] args) {

		SpringApplication.run(TruckConsumerApplication.class, args);
	}


	@Autowired
	public void kStreamFiler(StreamsBuilder streamsBuilder) {
		KStream<String, String> kStream = streamsBuilder.stream("truck_topic",
				Consumed.with(Serdes.String(), Serdes.String()));


		kStream.filter((k, v) -> Pattern.matches("[snow|heavyrain]", v.split(",")[4].trim())
						|| Integer.parseInt(v.split(",")[2].trim()) < 100 || Integer.parseInt(v.split(",")[3].trim()) > 50)
				.to("truck_fail");

	}

	@KafkaListener(topics = "truck_fail")
	public void consume(ConsumerRecord<String, String> message) {
		String[] environmentalCondition = message.value().split(",");
		String[] EnginePerformance = message.value().split(",");
		String[] TyrePressure = message.value().split(",");
		if (environmentalCondition[4].equals("Snow") || environmentalCondition[4].equals("HeavyRain")) {
			System.out.println("------------------------");

			System.out.println("Key : " + message.key() + " Value : " + message.value());

			System.out.println("Likely to fail due to environmental condition " + environmentalCondition[4]);
			System.out.println("------------------------");
		} else if (Integer.parseInt(EnginePerformance[2]) < 100) {
			System.out.println("------------------------");
			System.out.println("Key : " + message.key() + " Value : " + message.value());
			System.out.println("Likely to fail due to poor engine performance " + EnginePerformance[2]);
			System.out.println("------------------------");
		} else if (Integer.parseInt(TyrePressure[3]) > 50) {
			System.out.println("------------------------");
			System.out.println("Key : " + message.key() + " Value : " + message.value());
			System.out.println("Likely to fail due to high tyre pressure " + TyrePressure[3]);
			System.out.println("------------------------");


		}





	}
}
