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
		// 建立Session
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "localhost");
		properties.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getDefaultInstance(properties);
		// 编写Message
		Message message = new MimeMessage(session);
		try {
			// 设置发件人
			message.setFrom(new InternetAddress("aaa@estore.com"));
			// 设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(user
					.getEmail()));
			// 设置邮件主题
			message.setSubject("estore商城激活邮件");
			// 设置内容
			message
					.setContent(
							"<h4>欢迎使用estore商城系统，您的用户名是"
									+ user.getUsername()
									+ "，您的密码是"
									+ user.getPassword()
									+ "，请妥善保管。</h4><h4>请于"
									+ user.getExpiresActiveDate()
									+ "之前点击下面链接激活账号，否则账号将被删除！</h4><h3><a href='http://localhost/day20_mail/active?activeCode="
									+ user.getActiveCode()
									+ "'>http://localhost/day20_mail/active?activeCode="
									+ user.getActiveCode() + "</a></h3>",
							"text/html;charset=utf-8");
			// 通过Transport发送
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
