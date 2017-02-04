package cn.itcast.dom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XPathTest {
	@Test
	public void demo3() throws Exception {
		// ��ѯ ������Harry��ʼ ������ ����

		// װ�� xml�ĵ�
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		// ��д xpath ���ʽ
		String expr = "//book[starts-with(title,'Harry')]";

		// DOM4j ִ�� Xpath���ʽ
		List<Element> books = document.selectNodes(expr);

		for (Element book : books) {
			System.out.println(book.elementText("author"));
		}
	}

	@Test
	public void demo2() throws Exception {
		// ��ѯ �췽ҹ̷ ����

		// װ�� xml�ĵ�
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		// ��д xpath ���ʽ
		String expr = "//book[title='�췽ҹ̷']";

		// DOM4j ִ�� Xpath���ʽ
		List<Element> books = document.selectNodes(expr);

		for (Element book : books) {
			System.out.println(book.elementText("author"));
		}
	}

	@Test
	public void demo1() throws Exception {
		// ��ѯ ���� lang���� Ϊen ��titleԪ������

		// װ�� xml�ĵ�
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		// ��д xpath ���ʽ
		String expr = "//title[@lang='en']";

		// DOM4j ִ�� Xpath���ʽ
		List<Element> titles = document.selectNodes(expr);

		for (Element title : titles) {
			System.out.println(title.getText());
		}
	}
}
