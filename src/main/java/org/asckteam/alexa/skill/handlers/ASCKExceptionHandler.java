package org.asckteam.alexa.skill.handlers;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
public class ASCKExceptionHandler implements ExceptionHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, Throwable throwable) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, Throwable throwable) {
        return handlerInput.getResponseBuilder().withSpeech(throwable.getMessage()).build();
    }
}
