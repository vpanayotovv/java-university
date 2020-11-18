package softuni.jsonexercise.gson;

import com.google.gson.*;
import softuni.jsonexercise.domain.dtos.CustomerSeedDto;

import java.lang.reflect.Type;

public class GsonDeserializer implements JsonSerializer<CustomerSeedDto> {
    @Override
    public JsonElement serialize(CustomerSeedDto customerSeedDto, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
