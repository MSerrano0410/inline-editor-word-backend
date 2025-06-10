package com.springpractice.wordtohtmlrest.customobjects;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

public class ParagraphNodeVisitor implements NodeVisitor {
	
	String nodeName;
	boolean needNewRun;
	int paragraphCount;
	XWPFParagraph paragraph;
	XWPFRun run;
	
	public ParagraphNodeVisitor(XWPFParagraph paragraph, int paragraphCount) {
		this.paragraph = paragraph;
		this.paragraphCount = paragraphCount;
		this.run = paragraph.createRun();
		this.nodeName = "";
	}
	
	@Override
	public void head(Node node, int depth) {
		nodeName = node.nodeName();
		needNewRun = false;
		
		if ("#text".equals(nodeName)) {
			run.setText(((TextNode)node).text());
			needNewRun = true;
		} else if ("em".equals(nodeName)) {
			run.setItalic(true);
		} else if ("br".equals(nodeName)) {
			run.addBreak();
		} else if ("p".equals(nodeName) && paragraphCount > 0) {
			run.addBreak();
		} else if ("strong".equals(nodeName)) {
			run.setBold(true);
		} else if ("u".equals(nodeName)) {
			run.setUnderline(UnderlinePatterns.SINGLE);
		}
		
		if (needNewRun) {
			this.run = paragraph.createRun();
		}
	}
	
	@Override
	public void tail(Node node, int depth) {
		if ("em".equals(nodeName)) {
			run.setItalic(false);
		} else if ("strong".equals(nodeName)) {
			run.setBold(false);
		} else if ("u".equals(nodeName)) {
			run.setUnderline(UnderlinePatterns.NONE);
		}
		
		if (needNewRun) {
			this.run = paragraph.createRun();
		}
	}
}
