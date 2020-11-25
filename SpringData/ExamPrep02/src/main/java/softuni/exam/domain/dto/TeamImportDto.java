package softuni.exam.domain.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamImportDto {

    @Expose
    @XmlElement
    @NotNull
    @Length(min = 3,max = 20)
    private String name;

    @Expose
    @NotNull
    @XmlElement(name = "picture")
    private PictureImportDto picture;
}
