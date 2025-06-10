package com.springpractice.wordtohtmlrest.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.springframework.xml.XmlException;

import com.springpractice.wordtohtmlrest.customobjects.ParagraphNodeVisitor;

public class DocumentServiceUtils {
	public static XWPFDocument emptyXWPFDocument(XWPFDocument doc) {
		List<XWPFParagraph> paragraphs = doc.getParagraphs();
		List<XWPFParagraph> record = new ArrayList<XWPFParagraph>();
		for (XWPFParagraph paragraph : paragraphs) {
			for (int i = 0; i < paragraph.getRuns().size(); i++) {
				paragraph.removeRun(i);
				record.add(paragraph);
			}
		}
		
		for (int i = 0; i < record.size(); i++) {
			doc.removeBodyElement(doc.getPosOfParagraph(record.get(i)));
		}
		
		return doc;
	}
	
	//org.jsoup.nodes.Document
	public static XWPFDocument processXWPFDocumentFromHTML(XWPFDocument template, Document htmlDoc) throws XmlException, IOException {
		//parse paragraphs and elements within each paragraph using NodeTraversor
		XWPFParagraph paragraph = template.createParagraph();
		Elements htmlParagraphs = htmlDoc.select("p");
		int paragraphCount = 0;
		for (Element htmlParagraph : htmlParagraphs) {
			ParagraphNodeVisitor nodeVisitor = new ParagraphNodeVisitor(paragraph, paragraphCount);
			NodeTraversor.traverse(nodeVisitor, htmlParagraph);
			paragraphCount++;
		}
		
		return template;
	}
}
