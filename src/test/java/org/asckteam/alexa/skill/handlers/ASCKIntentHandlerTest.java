package org.asckteam.alexa.skill.handlers;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.asckteam.alexa.skill.model.ASCKEvent;
import org.asckteam.alexa.skill.model.ASCKUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ASCKIntentHandlerTest {

    @Test
    void getASCKUser() {
        ASCKUser asckUser = new ASCKIntentHandler().getASCKUser("andrei.alexandru.sava@gmail.com");
        assertNotNull(asckUser);
    }

    @Test
    void getEventsForUser() {
        List<ASCKEvent> events = new ASCKIntentHandler().getEventsForUser("andrei.alexandru.sava@gmail.com");
        assertNotNull(events);
        assertFalse(events.isEmpty());
    }
}