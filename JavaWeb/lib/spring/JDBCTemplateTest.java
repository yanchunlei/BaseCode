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
		// 使用RowMapper封装结果集
		// 使用spring配置文件获得DataSource, 获得spring 工厂
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"spring-jdbc.xml");

		// 通过工厂获得对象
		DataSource dataSource = (DataSource) factory.getBean("dataSource");

		// 创建JDBCTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from account";
		// rowMapper 封装每行到什么对象
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
		// 使用ResultSetExtractor 封装结果集
		// 使用spring配置文件获得DataSource, 获得spring 工厂
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"spring-jdbc.xml");

		// 通过工厂获得对象
		DataSource dataSource = (DataSource) factory.getBean("dataSource");

		// 创建JDBCTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from account";
		// 不知道你想把rs 转换成什么类型
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
		// 通过JDBC摸吧 update 完成 增加 修改 删除
		// 使用spring配置文件获得DataSource, 获得spring 工厂
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"spring-jdbc.xml");

		// 通过工厂获得对象
		DataSource dataSource = (DataSource) factory.getBean("dataSource");

		// 创建JDBCTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// 删除 数据记录5、6
		String sql = "delete from account where id in(?,?)";

		jdbcTemplate.update(sql, 5, 6);
	}

	@Test
	public void demo2() {
		// 任何数据库框架 都是通过连接池来管理数据库连接的

		// 使用spring配置文件获得DataSource, 获得spring 工厂
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"spring-jdbc.xml");

		// 通过工厂获得对象
		DataSource dataSource = (DataSource) factory.getBean("dataSource");

		// 创建JDBCTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "insert into account values(null,?,?)";
		jdbcTemplate.update(sql, "聪聪", 9000);
	}

	@Test
	public void demo1() {
		// 任何数据库框架 都是通过连接池来管理数据库连接的

		// 使用JDBCTemplate第一步 连接池
		DataSource dataSource = JDBCUtils.getDataSource();

		// 创建JDBCTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "insert into account values(null,?,?)";
		jdbcTemplate.update(sql, "丽丽", 5000);
	}
}
