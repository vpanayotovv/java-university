package softuni.jsonexercise.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.jsonexercise.utils.ValidationUtil;
import softuni.jsonexercise.utils.ValidationUtilImpl;

@Configuration
public class AppBeanConfiguration {

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
