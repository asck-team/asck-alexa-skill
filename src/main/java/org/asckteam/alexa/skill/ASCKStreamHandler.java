package org.asckteam.alexa.skill;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

public class ASCKStreamHandler extends SkillStreamHandler {


    private static Skill getSkill() {
        return Skills.standard().addRequestHandlers().build();
    }

    public ASCKStreamHandler() {
        super(getSkill());
    }

}
