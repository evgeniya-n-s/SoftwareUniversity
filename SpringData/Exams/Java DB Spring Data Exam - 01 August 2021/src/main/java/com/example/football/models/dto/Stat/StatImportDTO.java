package com.example.football.models.dto.Stat;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatImportDTO {
    @XmlElement
    @Positive
    private float shooting;
    @XmlElement
    @Positive
    private float passing;
    @XmlElement
    @Positive
    private float endurance;

    public StatImportDTO() {
    }

    public float getShooting() {
        return shooting;
    }

    public float getPassing() {
        return passing;
    }

    public float getEndurance() {
        return endurance;
    }
}
