package softuni.jsonexercise.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.jsonexercise.utils.ValidationUtil;
import softuni.jsonexercise.utils.ValidationUtilImpl;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class AppBeanConfiguration {

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    }
                })
                .create();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
