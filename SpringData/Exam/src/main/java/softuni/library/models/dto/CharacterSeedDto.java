package softuni.library.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import softuni.library.config.LocalDateAdapter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "character")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterSeedDto {

    @NotNull
    @Length(min = 3)
    @XmlElement(name = "first-name")
    private String firstName;

    @NotNull
    @XmlElement(name = "middle-name")
    @Length(max = 1,min = 1)
    private String middleName;

    @NotNull
    @Length(min = 3)
    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement
    @Min(10)
    @Max(66)
    @NotNull
    private int age;

    @XmlElement
    @NotNull
    @Length(min = 5)
    private String role;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull
    private LocalDate birthday;

    @XmlElement
    private BookXmlSeedDto book;

}
