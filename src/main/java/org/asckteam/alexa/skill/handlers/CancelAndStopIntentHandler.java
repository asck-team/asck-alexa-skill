package org.asckteam.alexa.skill.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

@SuppressWarnings("unused")
public class CancelAndStopIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("AMAZON.StopIntent").or(Predicates.intentName("AMAZON.CancelIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Goodbye";
        return handlerInput.getResponseBuilder().withSpeech(speechText).withSimpleCard("Hello World", speechText).build();
    }
}
