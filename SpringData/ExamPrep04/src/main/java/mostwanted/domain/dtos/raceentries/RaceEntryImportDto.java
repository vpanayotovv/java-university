package mostwanted.domain.dtos.raceentries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportDto {

    @XmlAttribute(name = "has-finished")
    private boolean hasFinished;

    @XmlAttribute(name = "finish-time")
    private Double finishTime;

    @XmlAttribute(name = "car-id")
    private Integer car;

    @XmlElement
    private String racer;

}
