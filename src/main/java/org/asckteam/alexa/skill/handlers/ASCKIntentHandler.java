package org.asckteam.alexa.skill.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import org.asckteam.alexa.skill.model.ASCKEvent;
import org.asckteam.alexa.skill.model.ASCKUser;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ASCKIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("ASCKIntent"));
    }


    protected ASCKUser getASCKUser(String email) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://survey.asck-team.org/v1/feedback/user").path(email);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        javax.ws.rs.core.Response response = invocationBuilder.get();
        if (response.getStatusInfo().getFamily() == javax.ws.rs.core.Response.Status.Family.REDIRECTION) {
            target = client.target(response.getLocation());
            invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        }
        return invocationBuilder.get(ASCKUser.class);
    }

    protected List<ASCKEvent> getEventsForUser(String email) {
        ASCKUser asckUser = getASCKUser(email);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://survey.asck-team.org/v1/feedback/events/ownedBy").path("" + asckUser.getId());
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        javax.ws.rs.core.Response response = invocationBuilder.get();
        if (response.getStatusInfo().getFamily() == javax.ws.rs.core.Response.Status.Family.REDIRECTION) {
            target = client.target(response.getLocation());
            response = target.request(MediaType.APPLICATION_JSON).get();
        }
        return response.readEntity(new GenericType<List<ASCKEvent>>() {});
    }


    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Hello World";


        List<ASCKEvent> events = getEventsForUser("constantinidis@web.de");

        String eventsString = events.stream().map(ASCKEvent::getName).collect(Collectors.joining(","));

        return handlerInput.getResponseBuilder().withSpeech(eventsString).withSimpleCard("Hello World", eventsString).build();
    }
}
