package cn.itcast.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import cn.itcast.utils.JDBCUtils;
import cn.itcast.vo.Account;

public class JDBCTemplateTest {
	@Test
	public void demo5() {
		// ʹ��RowMapper��װ�����
		// ʹ��spring�����ļ����DataSource, ���spring ����
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"spring-jdbc.xml");

		// ͨ��������ö���
		DataSource dataSource = (DataSource) factory.getBean("dataSource");

		// ����JDBCTemplate����
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from account";
		// rowMapper ��װÿ�е�ʲô����
		List<Account> accounts = jdbcTemplate.query(sql,
				new RowMapper<Account>() {

					@Override
					public Account mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Account account = new Account();
						account.setId(rs.getInt("id"));
						account.setName(rs.getString("name"));
						account.setMoney(rs.getDouble("money"));
						return account;
					}

				});

		for (Account account : accounts) {
			System.out.println(account.getId() + "," + account.getName() + ","
					+ account.getMoney());
		}
	}

	@Test
	public void demo4() {
		// ʹ��ResultSetExtractor ��װ�����
		// ʹ��spring�����ļ����DataSource, ���spring ����
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"spring-jdbc.xml");

		// ͨ��������ö���
		DataSource dataSource = (DataSource) factory.getBean("dataSource");

		// ����JDBCTemplate����
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from account";
		// ��֪�������rs ת����ʲô����
		List<Account> accounts = jdbcTemplate.query(sql,
				new ResultSetExtractor<List<Account>>() {

					@Override
					public List<Account> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<Account> accounts = new ArrayList<Account>();
						while (rs.next()) {
							Account account = new Account();
							account.setId(rs.getInt("id"));
							account.setName(rs.getString("name"));
							account.setMoney(rs.getDouble("money"));
							accounts.add(account);
						}
						return accounts;
					}

				});

		for (Account account : accounts) {
			System.out.println(account.getId() + "," + account.getName() + ","
					+ account.getMoney());
		}
	}

	@Test
	public void demo3() {
		// ͨ��JDBC���� update ��� ���� �޸� ɾ��
		// ʹ��spring�����ļ����DataSource, ���spring ����
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"spring-jdbc.xml");

		// ͨ��������ö���
		DataSource dataSource = (DataSource) factory.getBean("dataSource");

		// ����JDBCTemplate����
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// ɾ�� ���ݼ�¼5��6
		String sql = "delete from account where id in(?,?)";

		jdbcTemplate.update(sql, 5, 6);
	}

	@Test
	public void demo2() {
		// �κ����ݿ��� ����ͨ�����ӳ����������ݿ����ӵ�

		// ʹ��spring�����ļ����DataSource, ���spring ����
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"spring-jdbc.xml");

		// ͨ��������ö���
		DataSource dataSource = (DataSource) factory.getBean("dataSource");

		// ����JDBCTemplate����
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "insert into account values(null,?,?)";
		jdbcTemplate.update(sql, "�ϴ�", 9000);
	}

	@Test
	public void demo1() {
		// �κ����ݿ��� ����ͨ�����ӳ����������ݿ����ӵ�

		// ʹ��JDBCTemplate��һ�� ���ӳ�
		DataSource dataSource = JDBCUtils.getDataSource();

		// ����JDBCTemplate����
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "insert into account values(null,?,?)";
		jdbcTemplate.update(sql, "����", 5000);
	}
}
