package cn.itcast.javamail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;

public class SendMailAPITest {
	@Test
	public void demo2() throws AddressException, UnsupportedEncodingException,
			MessagingException {
		// ��������ʼ�
		// �����������ʼ�
		Properties props = new Properties();// �����ʼ��������ʼ�
		props.setProperty("mail.smtp.host", "localhost");
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getDefaultInstance(props);

		Message message = createMessage(session);

		// �����ʼ�
		Transport transport = session.getTransport();
		transport.connect("aaa", "123");
		transport.sendMessage(message, message.getAllRecipients());

	}

	public Message createMessage(Session session) throws AddressException,
			MessagingException, UnsupportedEncodingException {

		// ��дǶ��ͼƬ���ʼ�
		Message message = new MimeMessage(session);

		// �����ʼ�������Ϣ
		message.setFrom(new InternetAddress("aaa@estore.com"));
		message.setRecipient(RecipientType.TO, new InternetAddress(
				"bbb@estore.com"));
		message.setSubject("����һ������������Ƕ��ͼƬ���ʼ�");

		// ��д�ʼ�����
		MimeBodyPart content = new MimeBodyPart();
		content.setContent("���ʼ�����ͼƬ ���и�����ͼƬ<img src='cid:mypic'/>",
				"text/html;charset=utf-8");

		MimeBodyPart pic = new MimeBodyPart();
		DataHandler dh1 = new DataHandler(new FileDataSource("mm.jpg"));
		pic.setDataHandler(dh1);
		pic.setContentID("mypic");

		MimeBodyPart attachment = new MimeBodyPart();
		DataHandler dh2 = new DataHandler(new FileDataSource("love.mp3"));
		attachment.setDataHandler(dh2);
		attachment.setFileName(MimeUtility.encodeText("��Ϊ����.mp3"));

		// ����
		MimeMultipart m1 = new MimeMultipart();
		m1.addBodyPart(content);
		m1.addBodyPart(pic);
		m1.setSubType("related");

		MimeBodyPart temp = new MimeBodyPart();
		temp.setContent(m1);

		// ��������
		MimeMultipart m2 = new MimeMultipart();
		m2.addBodyPart(temp);
		m2.addBodyPart(attachment);
		m2.setSubType("mixed");

		message.setContent(m2);
		return message;
	}

	@Test
	public void demo1() throws AddressException, MessagingException {
		// �����ʼ� ��������
		// 1��׼��session ���� 2��׼���ʼ� Message���� 3������Transport
		Properties props = new Properties();
		// props.setProperty("mail.host", "localhost"); // �����ʼ��� �����ʼ���������
		props.setProperty("mail.smtp.host", "localhost");
		props.setProperty("mail.transport.protocol", "smtp");
		// Ҫ�봴���ʼ�����Ҫһ���ʼ��Ự������smtp
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);// �򿪿���̨��־
		// ��д��һ��javamail �ʼ�
		Message message = new MimeMessage(session);
		// ���÷�����
		message.setFrom(new InternetAddress("aaa@estore.com"));
		// �����ռ���
		message.setRecipient(RecipientType.TO, new InternetAddress(
				"bbb@estore.com"));
		// �����ʼ�����
		message.setSubject("javamail ���ʼ�");
		// �����ʼ�����
		message.setText("��һ����ʼ�");

		// �����ʼ�
		Transport transport = session.getTransport();
		transport.connect("aaa", "123");
		transport.sendMessage(message, message.getAllRecipients());
	}
}
