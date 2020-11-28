package softuni.library.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.library.util.ValidationUtil;
import softuni.library.util.ValidationUtilImpl;
import softuni.library.util.XmlParser;
import softuni.library.util.XmlParserImpl;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    }
                })
                .create();
    }

    @Bean
    public ValidationUtil validatorUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
