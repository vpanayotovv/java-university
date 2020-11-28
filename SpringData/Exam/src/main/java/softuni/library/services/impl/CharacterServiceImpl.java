package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.CharacterSeedDto;
import softuni.library.models.dto.CharactersSeedRootDto;
import softuni.library.models.entity.Book;
import softuni.library.models.entity.Character;
import softuni.library.repositories.CharacterRepository;
import softuni.library.services.BookService;
import softuni.library.services.CharacterService;
import softuni.library.util.ValidationUtil;
import softuni.library.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final BookService bookService;
    private final String CHAR_PATH = "src/main/resources/files/xml/characters.xml";

    public CharacterServiceImpl(CharacterRepository characterRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser, BookService bookService) {
        this.characterRepository = characterRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.bookService = bookService;
    }

    @Override
    public boolean areImported() {
        return this.characterRepository.count() > 0;
    }

    @Override
    public String readCharactersFileContent() throws IOException {
        return Files.readString(Path.of(CHAR_PATH));
    }

    @Override
    public String importCharacters() throws IOException, JAXBException {
        StringBuilder result = new StringBuilder();

        CharactersSeedRootDto charactersSeedRootDto =
                this.xmlParser.parseXml(CharactersSeedRootDto.class, CHAR_PATH);

        for (CharacterSeedDto character : charactersSeedRootDto.getCharacterSeedDtos()) {
            if (this.validationUtil.isValid(character)){
                if (this.characterRepository.
                        findByFirstNameAndLastNameAndAge (
                                character.getFirstName(),
                                character.getLastName(),character.getAge()) == null){
                    Character mappedChar = this.modelMapper.map(character, Character.class);
                    Book book;
                    try {
                        book = this.bookService.getBookById(character.getBook().getId());
                        mappedChar.setBook(book);

                        this.characterRepository.saveAndFlush(mappedChar);

                        result.append(String.format(
                                "Successfully imported Character %s %s %s - %d",
                                mappedChar.getFirstName(),
                                mappedChar.getMiddleName(),
                                mappedChar.getLastName(),
                                mappedChar.getAge())).append(System.lineSeparator());

                    }catch (IllegalArgumentException ex){
                        result.append(ex.getMessage()).append(System.lineSeparator());
                    }

                }else {
                    result.append("Invalid Character").append(System.lineSeparator());
                }
            }else {
                result.append("Invalid Character").append(System.lineSeparator());
            }

        }

        return result.toString();
    }

    @Override
    public String findCharactersInBookOrderedByLastNameDescendingThenByAge() {
        StringBuilder result = new StringBuilder();

        List<Character> charactors = this.characterRepository.findAllByAgeIsGreaterThanEqualOrderByBookNameAscLastNameDescAge(32);

        for (Character charactor : charactors) {
            result.append(String.format("Character name %s %s %s, age %d, in book %s%n",charactor.getFirstName(),charactor.getMiddleName(),charactor.getLastName(),charactor.getAge(),charactor.getBook()));
        }

        return result.toString();
    }
}
