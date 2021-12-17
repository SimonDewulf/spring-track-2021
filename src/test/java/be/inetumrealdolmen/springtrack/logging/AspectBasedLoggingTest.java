package be.inetumrealdolmen.springtrack.logging;

import be.inetumrealdolmen.springtrack.aspect.RepositoryLoggingAspect;
import be.inetumrealdolmen.springtrack.service.GameService;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AspectBasedLoggingTest {

    @Autowired
    private GameService gameService;

    @Test
    void gameRepository() {
        var appender = getListAppender();
        gameService.createGame();

        assertAll(
                () -> assertEquals(1, appender.list.size()),
                () -> assertTrue(appender.list.get(0).getMessage().contains("CrudRepository.save(..)"))
        );
    }

    private ListAppender<ILoggingEvent> getListAppender() {
        Logger logger = (Logger) LoggerFactory.getLogger(RepositoryLoggingAspect.class);
        ListAppender<ILoggingEvent> loggingEventListAppender = new ListAppender<>();
        loggingEventListAppender.start();
        logger.addAppender(loggingEventListAppender);
        return loggingEventListAppender;
    }
}
