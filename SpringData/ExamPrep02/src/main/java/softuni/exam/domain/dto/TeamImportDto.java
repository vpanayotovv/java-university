package softuni.exam.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import softuni.exam.domain.entity.Picture;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamImportDto {

    @XmlElement
    @NotNull
    @Length(min = 3,max = 20)
    private String name;

    @NotNull
    @XmlElement(name = "picture")
    private Picture picture;
}
