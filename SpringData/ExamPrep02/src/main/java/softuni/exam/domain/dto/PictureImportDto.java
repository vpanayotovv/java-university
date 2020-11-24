package softuni.exam.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "picture")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureImportDto {

    @XmlElement
    @NotNull
    private String url;
}
