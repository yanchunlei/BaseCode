package cn.itcast.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class DOM4jCURD {

	// 添加复杂元素 book
	@Test
	public void demo7() throws Exception {
		// DOM 回写 加载xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		Element bookstore = document.getRootElement();

		// 方式一
		// // 创建 book 元素
		// Element book = DocumentHelper.createElement("book");
		// book.addAttribute("category", "CHILDREN");
		// // 创建 title元素
		// Element title = DocumentHelper.createElement("title");
		// title.addAttribute("lang", "zh");
		// title.setText("天方夜谭");
		// // 创建 author
		// Element author = DocumentHelper.createElement("author");
		// author.setText("老黎");
		//
		// book.add(title);
		// book.add(author);
		// bookstore.add(book);

		// 方式二
		String xmlpart = "<book category=\"CHILDREN\"><title lang=\"zh\">天方夜谭</title><author>老黎</author></book>";
		Document bookDocument = DocumentHelper.parseText(xmlpart);
		bookstore.add(bookDocument.getRootElement());

		// 回写XML 默认输出 编码集 UTF-8
		XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
		writer.write(document);
		writer.close();
	}

	// 删除 bid 属性
	@Test
	public void demo6() throws Exception {
		// DOM 回写 加载xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		List<Element> books = document.getRootElement().elements("book");
		for (Element book : books) {
			Attribute bid = book.attribute("bid");
			book.remove(bid);
		}

		// 回写XML 默认输出 编码集 UTF-8
		XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
		writer.write(document);
		writer.close();
	}

	// 删除 number 子节点
	@Test
	public void demo5() throws Exception {
		// DOM 回写 加载xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		List<Element> books = document.getRootElement().elements("book");
		for (Element book : books) {
			Element number = book.element("number");
			number.getParent().remove(number);
		}

		// 回写XML 默认输出 编码集 UTF-8
		XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
		writer.write(document);
		writer.close();
	}

	// 为每本图书 添加 bid 属性 值为1000
	@Test
	public void demo4() throws Exception {
		// 修改 中文 回写
		// DOM 回写 加载xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		List<Element> books = document.getRootElement().elements("book");
		for (Element book : books) {
			book.addAttribute("bid", "1000");
		}

		// 回写XML 默认输出 编码集 UTF-8
		XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
		writer.write(document);
		writer.close();
	}

	// 为每本图书 添加 子元素 number 值为100
	@Test
	public void demo3() throws Exception {
		// 修改 中文 回写
		// DOM 回写 加载xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		List<Element> books = document.getRootElement().elements("book");
		for (Element book : books) {
			// 添加元素 前 必先创建元素
			Element number = DocumentHelper.createElement("number");
			number.setText("100");

			// appendChild
			// book.add(number);

			// 添加number 到author 后面 (第三个位置)
			List<Element> children = book.elements();
			children.add(2, number);

		}

		// 回写XML 默认输出 编码集 UTF-8
		OutputFormat format = OutputFormat.createPrettyPrint();// 指定XML编码
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(
				new FileOutputStream("bookstore3.xml"), "utf-8"),format);
		writer.write(document);
		writer.close();

	}

	@Test
	public void demo2() throws Exception {
		// 修改 中文 回写
		// DOM 回写 加载xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));

		// 修改 分类为 Children 图书 作者为 迪士尼
		List<Element> books = document.getRootElement().elements("book");
		for (Element book : books) {
			if ("CHILDREN".equals(book.attributeValue("category"))) {
				book.element("author").setText("迪士尼");
			}
		}

		// 回写XML 默认输出 编码集 UTF-8
		// XMLWriter writer = new XMLWriter(new
		// FileOutputStream("bookstore3.xml"));
		// writer.write(document);
		// writer.close();

		OutputFormat format = OutputFormat.createPrettyPrint();// 指定XML编码
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(
				new FileOutputStream("bookstore3.xml"), "utf-8"),format);
		writer.write(document);
		writer.close();
	}

	// 修改回写
	@Test
	public void demo1() throws Exception {
		// DOM 回写 加载xml文档
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("bookstore3.xml"));
		// 内存中修改 DOM模型数据
		// 需求 每本书 价格+100
		Element root = document.getRootElement();
		List<Element> books = root.elements("book");
		for (Element book : books) {
			String value = book.elementText("price");
			value = String.valueOf(Double.parseDouble(value) + 100);
			// value = Double.parseDouble(value)+100+"";
			book.element("price").setText(value);
		}
		// 回写XML
		XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
		writer.write(document);
		writer.close();
	}
}
