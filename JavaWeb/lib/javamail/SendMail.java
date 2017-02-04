package cn.itcast.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.itcast.vo.User;

public class SendMail implements Runnable {

	private User user;

	public SendMail(User user) {
		this.user = user;
	}

	@Override
	public void run() {
		// ����Session
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "localhost");
		properties.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getDefaultInstance(properties);
		// ��дMessage
		Message message = new MimeMessage(session);
		try {
			// ���÷�����
			message.setFrom(new InternetAddress("aaa@estore.com"));
			// �����ռ���
			message.setRecipient(RecipientType.TO, new InternetAddress(user
					.getEmail()));
			// �����ʼ�����
			message.setSubject("estore�̳Ǽ����ʼ�");
			// ��������
			message
					.setContent(
							"<h4>��ӭʹ��estore�̳�ϵͳ�������û�����"
									+ user.getUsername()
									+ "������������"
									+ user.getPassword()
									+ "�������Ʊ��ܡ�</h4><h4>����"
									+ user.getExpiresActiveDate()
									+ "֮ǰ����������Ӽ����˺ţ������˺Ž���ɾ����</h4><h3><a href='http://localhost/day20_mail/active?activeCode="
									+ user.getActiveCode()
									+ "'>http://localhost/day20_mail/active?activeCode="
									+ user.getActiveCode() + "</a></h3>",
							"text/html;charset=utf-8");
			// ͨ��Transport����
			Transport transport = session.getTransport();
			transport.connect("aaa", "123");
			transport.sendMessage(message, message.getAllRecipients());
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
