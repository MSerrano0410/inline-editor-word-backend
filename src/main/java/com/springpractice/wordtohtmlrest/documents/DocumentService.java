package com.springpractice.wordtohtmlrest.documents;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class DocumentService {
    //TODO: Make abstract and "impl" class, constructor w/ directory, utils class with new file name creator
    //TODO: Make endpoint to call from react
    public String uploadDoc(MultipartFile multipartFile) throws IOException {
		return null;
    	//return status message
        //todo: use Files.copy(multipartFile.getInputStream(), directory.resolve(targetFile)), targetFile is new file name, directory is string w/ folder locally
    }

    public Resource downloadDoc(String fileName) {
		return null;
        //todo: Path path = docDirectory.resolve(fileName), UrlResource(path.toUri()), return file
    }
}
