package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.LibrarySeedDto;
import softuni.library.models.dto.LibrarySeedRootDto;
import softuni.library.models.entity.Book;
import softuni.library.models.entity.Library;
import softuni.library.repositories.LibraryRepository;
import softuni.library.services.BookService;
import softuni.library.services.LibraryService;
import softuni.library.util.ValidationUtil;
import softuni.library.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final BookService bookService;
    private final String LIBRARY_PATH = "src/main/resources/files/xml/libraries.xml";

    public LibraryServiceImpl(LibraryRepository libraryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser, BookService bookService) {
        this.libraryRepository = libraryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.bookService = bookService;
    }

    @Override
    public boolean areImported() {
        return this.libraryRepository.count() > 0;
    }

    @Override
    public String readLibrariesFileContent() throws IOException {
       return Files.readString(Path.of(LIBRARY_PATH));
    }

    @Override
    public String importLibraries() throws JAXBException, FileNotFoundException {
        StringBuilder result = new StringBuilder();
        LibrarySeedRootDto librarySeedRootDto = this.xmlParser.parseXml(LibrarySeedRootDto.class, LIBRARY_PATH);
        for (LibrarySeedDto library : librarySeedRootDto.getLibrarySeedDtoSet()) {
            if (this.validationUtil.isValid(library)){
                Set<Book> books = new HashSet<>();
                if (this.libraryRepository.findByName(library.getName()) == null){
                    Library mappedLibrary = this.modelMapper.map(library, Library.class);
                    try {
                        Book book = this.bookService.getBookById(library.getBook().getId());
                        books.add(book);
                        mappedLibrary.setBooks(books);

                        this.libraryRepository.saveAndFlush(mappedLibrary);

                        result.append(String.format("Successfully added Library: %s  - %s",mappedLibrary.getName(),mappedLibrary.getLocation())).append(System.lineSeparator());

                    }catch (IllegalArgumentException ex ) {
                        result.append(ex.getMessage()).append(System.lineSeparator());
                    }

                }else {
                    result.append("Invalid Library").append(System.lineSeparator());
                }
            }else {
                result.append("Invalid Library").append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
