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
		// 查询 标题以Harry开始 所有书 作者

		// 装载 xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		// 编写 xpath 表达式
		String expr = "//book[starts-with(title,'Harry')]";

		// DOM4j 执行 Xpath表达式
		List<Element> books = document.selectNodes(expr);

		for (Element book : books) {
			System.out.println(book.elementText("author"));
		}
	}

	@Test
	public void demo2() throws Exception {
		// 查询 天方夜谭 作者

		// 装载 xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		// 编写 xpath 表达式
		String expr = "//book[title='天方夜谭']";

		// DOM4j 执行 Xpath表达式
		List<Element> books = document.selectNodes(expr);

		for (Element book : books) {
			System.out.println(book.elementText("author"));
		}
	}

	@Test
	public void demo1() throws Exception {
		// 查询 所有 lang属性 为en 的title元素内容

		// 装载 xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		// 编写 xpath 表达式
		String expr = "//title[@lang='en']";

		// DOM4j 执行 Xpath表达式
		List<Element> titles = document.selectNodes(expr);

		for (Element title : titles) {
			System.out.println(title.getText());
		}
	}
}
