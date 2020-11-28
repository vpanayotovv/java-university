package softuni.library.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibrarySeedDto {

    @XmlElement
    @NotNull
    @Length(min = 3)
    private String name;

    @XmlElement
    @NotNull
    @Length(min = 5)
    private String location;

    @XmlElement
    @Min(1)
    @Max(10)
    private int rating;

    @XmlElement
    private BookXmlSeedDto book;
}
