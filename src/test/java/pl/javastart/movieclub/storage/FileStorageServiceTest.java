package pl.javastart.movieclub.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;


class FileStorageServiceTest {

    @Mock
    private MultipartFile file;

    @Mock
    private FileInputStream fileInputStreamMock;

    private FileStorageService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldCreateImageFilePath() throws IOException {
        //given
        String fileName = "poster.png";
        String imageStorageLocation = "./uploads/";

        underTest = new FileStorageService(imageStorageLocation);
        //when
        given(file.getOriginalFilename()).willReturn(fileName);
        given(file.getInputStream()).willReturn(fileInputStreamMock);
        String savedFileName = underTest.saveImage(file);

        //then
        assertThat(savedFileName)
                .isNotEmpty()
                .contains("poster");

        System.out.println(savedFileName);
    }

    @Test
    void itShouldCreateFilePath() throws IOException {
        //given
        String fileName = "poster.pdf";
        String imageStorageLocation = "./uploads/";

        underTest = new FileStorageService(imageStorageLocation);
        //when
        given(file.getOriginalFilename()).willReturn(fileName);
        given(file.getInputStream()).willReturn(fileInputStreamMock);
        String savedFileName = underTest.saveFile(file);

        //then
        assertThat(savedFileName)
                .isNotEmpty()
                .contains("poster");

        System.out.println(savedFileName);
    }

    @Test
    void itShouldThrowExceptionWhenDirectoryDoesNotExist() throws IOException {
        //given
        String fileName = "poster.pdf";
        String imageStorageLocation = "./anydirectory/";

        //when //then

        assertThatThrownBy(() -> underTest = new FileStorageService(imageStorageLocation))
                .isInstanceOf(FileNotFoundException.class);
    }

    @Test
    void itShouldThrowExceptionWhenCopyingFile() throws IOException {
        //given
        String fileName = "poster.pdf";
        String imageStorageLocation = "./uploads/";

        underTest = new FileStorageService(imageStorageLocation);

        given(file.getOriginalFilename()).willReturn(fileName);
        given(file.getInputStream()).willThrow(IOException.class);

        //when //then

        assertThatThrownBy(() -> underTest.saveFile(file))
                .isInstanceOf(UncheckedIOException.class);
    }
}