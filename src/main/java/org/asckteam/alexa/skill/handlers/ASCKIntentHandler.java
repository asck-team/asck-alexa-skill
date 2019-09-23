package org.asckteam.alexa.skill.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import lombok.extern.log4j.Log4j2;
import org.asckteam.alexa.skill.model.ASCKEvent;
import org.asckteam.alexa.skill.model.ASCKUser;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@SuppressWarnings("unused")
public class ASCKIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("ASCKIntent"));
    }


    protected ASCKUser getASCKUser(String email) {
        Client client = ClientBuilder.newClient();
        client.register(JacksonJsonProvider.class);
        WebTarget target = client.target("https://asck-survey-test.herokuapp.com/v1/feedback/user").path(email).path("/");
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.get(ASCKUser.class);
    }

    protected List<ASCKEvent> getEventsForUser(String email) {
        ASCKUser asckUser = getASCKUser(email);
        Client client = ClientBuilder.newClient();
        client.register(JacksonJsonProvider.class);
        WebTarget target = client.target("https://asck-survey-test.herokuapp.com/v1/feedback/events/ownedBy").path("" + asckUser.getId());
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.get(new GenericType<List<ASCKEvent>>() {});
    }


    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        log.info("handle: " + handlerInput.getRequestEnvelope().getVersion() );
        String speechText = "Hello World";


        log.info("bevor call getEvents4ForUser");
        List<ASCKEvent> events = getEventsForUser("andrei.sava@gmail.com");

        String eventsString = events.stream().map(ASCKEvent::getName).collect(Collectors.joining(","));

        return handlerInput.getResponseBuilder().withSpeech(eventsString).withSimpleCard("Hello World", speechText).build();
    }
}
