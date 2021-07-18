import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.*;

import java.util.Date;
import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
        String clientId = "my-producer";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");
        props.put("client.id", clientId);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        String topic = "numbers";

        int numOfRecords  = 100;

        // Example 1 - Numbers as strings
        for (int i = 0; i < numOfRecords; i++) {
            System.out.println("Message " + i + " was just produced");
            producer.send(new ProducerRecord<>(topic, Integer.toString(i), Integer.toString(i)));
        }

        // Example 2 - Formatted string as message and messages with 300 ms delay
        /*
        try {
            for (int i = 0; i < numOfRecords; i++) {
                String message = String.format("Producer %s has sent message %s as %s", clientId, i, new Date());

                System.out.println("Message " + i + " was just produced");
                producer.send(new ProducerRecord<>(topic, Integer.toString(i), message));
                Thread.sleep(300);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        */


        producer.close();
    }
}
