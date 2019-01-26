package hello;

import Utilities.Network;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Network network = new Network(6659);
        UpdateInfo updateInfo = new UpdateInfo(network);
        updateInfo.startListening();
    }
}
