package softuni.library.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "characters")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharactersSeedRootDto {

    @XmlElement(name = "character")
    private List<CharacterSeedDto> characterSeedDtos;

}
