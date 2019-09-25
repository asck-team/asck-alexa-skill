package org.asckteam.alexa.skill.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

@SuppressWarnings("unused")
public class LaunchRequestHandler implements RequestHandler {


    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Willkommen zu ASCK Umfragen, du kannst mich fragen welche Umfragen dir zur Verfügung stehen.";
        return handlerInput.getResponseBuilder().withSpeech(speechText).withSimpleCard("ASCK Umfragen", speechText).withReprompt(speechText).build();
    }
}
