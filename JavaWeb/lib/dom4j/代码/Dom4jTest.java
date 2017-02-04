package cn.itcast.dom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Dom4jTest {

	@Test
	public void demo3() throws Exception {
		// 遍历 book节点所有属性
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore2.xml"));

		// 获得根
		Element root = document.getRootElement();
		List<Element> books = root.elements("book");

		for (Element book : books) {
			List<Attribute> attributes = book.attributes();
			for (Attribute attribute : attributes) {
				System.out.println(attribute.getName() + ":"
						+ attribute.getText() + "," + attribute.getValue());
			}
		}

	}

	@Test
	public void demo2() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore2.xml"));

		// 遍历每本书 哪本书标题children
		Element root = document.getRootElement();// 获得根节点

		List<Element> books = root.elements("book"); // 获得所有book子节点
		for (Element book : books) {
			// 获得 book 标题属性 attributeValue
			String categoryValue = book.attributeValue("category");
			if ("CHILDREN".equals(categoryValue)) {
				// 当前 book 就是要找的书
				Element title = book.element("title");
				System.out.println(title.getText()); // title.getFirstChild().getNodeValue()

				System.out.println(book.elementText("title"));
			}
		}
	}

	@Test
	public void demo1() throws DocumentException {
		// 使用DOM 解析时 ---- 第一步 加载xml文档，获得Document对象
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore2.xml"));

		Element bookstore = document.getRootElement();// 获得根元素
		System.out.println(bookstore.getName());

		List<Element> list = bookstore.elements();
		System.out.println(list.size());
		for (Element e : list) {
			System.out.println(e.getName());
			System.out.println(e.attributeValue("category"));
			System.out.println(e.attribute("category").getValue());
		}
	}
}
