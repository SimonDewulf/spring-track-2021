package be.inetumrealdolmen.springtrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SpringTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTrackApplication.class, args);
    }

}
