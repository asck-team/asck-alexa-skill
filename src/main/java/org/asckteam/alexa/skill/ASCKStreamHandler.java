package org.asckteam.alexa.skill;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import org.asckteam.alexa.skill.handlers.*;

@SuppressWarnings("unused")
public class ASCKStreamHandler extends SkillStreamHandler {


    private static Skill getSkill() {
        return Skills.standard().addRequestHandlers(new ASCKIntentHandler(), new CancelAndStopIntentHandler(), new FallbackIntentHandler(), new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler()).addExceptionHandler(new ASCKExceptionHandler()).withSkillId("amzn1.ask.skill.8093cb49-747c-4553-817f-a7bd32da063e").build();
    }

    public ASCKStreamHandler() {
        super(getSkill());
    }

}
