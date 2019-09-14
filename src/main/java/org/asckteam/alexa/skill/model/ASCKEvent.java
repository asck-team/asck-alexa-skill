package org.asckteam.alexa.skill.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ASCKEvent {

    private Long id;
    private String name;


}
