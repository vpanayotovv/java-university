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
@XmlRootElement(name = "libraries")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibrarySeedRootDto {

    @XmlElement(name = "library")
    List<LibrarySeedDto> librarySeedDtoSet;
}
