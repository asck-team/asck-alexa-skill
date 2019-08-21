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
        String speechText = "Welcome to Alexa Skills Kit, you can say hello";
        return handlerInput.getResponseBuilder().withSpeech(speechText).withSimpleCard("Hello World", speechText).withReprompt(speechText).build();
    }
}
