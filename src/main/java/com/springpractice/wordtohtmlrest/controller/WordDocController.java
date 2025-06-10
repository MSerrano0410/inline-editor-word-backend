package com.springpractice.wordtohtmlrest.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.xml.XmlException;

import com.springpractice.wordtohtmlrest.customobjects.CustomMultipartFile;
import com.springpractice.wordtohtmlrest.documents.DocumentService;
import com.springpractice.wordtohtmlrest.utils.DocumentServiceUtils;

public class WordDocController {
	public DocumentService documentService;
	@PostMapping(path = "inline-word-editor")//CHANGE THIS
	public ResponseEntity<String> saveDoc(
		@RequestParam("inlineEditorHtml") String inlineEditorHtml,
		@RequestParam("filename") String filename,
		Principal principal) throws IOException, XmlException {
		Resource originalRes = documentService.downloadDoc(filename);
		XWPFDocument template = new XWPFDocument(originalRes.getInputStream());
		template = DocumentServiceUtils.emptyXWPFDocument(template);
		
		//parse HTML (org.jsoup.nodes.Document
		Document htmlDoc = Jsoup.parse(inlineEditorHtml);
		XWPFDocument doc = DocumentServiceUtils.processXWPFDocumentFromHTML(template, htmlDoc);
		
		//convert to Byte array
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		doc.write(out);
		out.close();
		byte[] xwpfDocumentBytes = out.toByteArray();
		
		//set up uploadDoc call (CustomMultipartFile is in this same package
		CustomMultipartFile customFile = new CustomMultipartFile(xwpfDocumentBytes, filename, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		String response = documentService.uploadDoc(customFile);
		
		//TODO: Add error handling here
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
