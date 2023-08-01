package com.example.parkingmanagementconsumer.ktable;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KTableImplementation {

	private static final Serde<String> STRING_SERDE = Serdes.String();

    private KTable<String, String> parkingSpaceKTable;

    @Autowired
    
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
                .stream("live_avl_free_space", Consumed.with(STRING_SERDE, STRING_SERDE));

        parkingSpaceKTable = messageStream.toTable(Materialized.as("parking_space_1"));

        messageStream.filter((k, v) -> Integer.parseInt(v) < 100).to("live_avl_empty_space_warning");

    }

    public KTable<String, String> getParkingSpaceKTable() {

        return parkingSpaceKTable;
    }
}
