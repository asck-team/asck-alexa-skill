package org.asckteam.alexa.skill.handlers;

import org.asckteam.alexa.skill.model.ASCKUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@Disabled
class ASCKIntentHandlerTest {

    @Test
    void getASCKUser() {
        ASCKUser asckUser = new ASCKIntentHandler().getASCKUser("constantinidis@web.de");
        assertNotNull(asckUser);
    }
}