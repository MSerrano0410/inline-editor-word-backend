package com.springpractice.wordtohtmlrest.customobjects;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class CustomMultipartFile implements MultipartFile {
	private byte[] input;
	private String fileName;
	private String contentType;
	
	public CustomMultipartFile(byte[] input, String fileName, String contentType) {
		this.input = input;
		this.fileName = fileName;
		this.contentType = contentType;
	}
	
	@Override
	public boolean isEmpty() {
		return input == null || input.length == 0;
	}
	
	@Override
	public byte[] getBytes() throws IOException {
		return input;
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(input);
	}
	
	@Override
	public String getName() {
		return this.fileName;
	}
	
	@Override
	public String getOriginalFilename() {
		return this.fileName;
	}
	
	@Override
	public String getContentType() {
		return this.contentType;
	}
	
	@Override
	public long getSize() {
		return this.input.length;
	}
	
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		try (FileOutputStream fos = new FileOutputStream(dest)) {
			fos.write(input);
		} //TODO: make catch block
	}
}
