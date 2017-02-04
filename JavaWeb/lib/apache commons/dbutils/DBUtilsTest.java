package cn.itcast.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.Test;

import cn.itcast.utils.JDBCUtils;
import cn.itcast.vo.User;

public class DBUtilsTest {
	@Test
	public void demo4() throws SQLException {
		// ����account һ����¼
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into account values(null,?,?)";
		queryRunner.update(sql, "С��", 10000);
	}

	@Test
	public void demo3() throws SQLException {
		// ת�� С�� ת�� С��1000 ----- �ֶ���������
		QueryRunner queryRunner = new QueryRunner();// ���������ӳظ����
		Connection conn = JDBCUtils.getConnection();
		// �������񣬽������Զ��ύ�ر�
		conn.setAutoCommit(false);
		try {
			queryRunner.update(conn,
					"update account set money = money-1000 where name='С��'");
			int d = 1 / 0;
			queryRunner.update(conn,
					"update account set money = money+1000 where name='С��'");
			// �������� �ύ
			System.out.println("�����ύ��");
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ع�");
			conn.rollback();
		} finally {
			// �ر�����
			DbUtils.closeQuietly(conn);
		}
	}

	@Test
	public void demo2() throws SQLException {
		// ת�� С�� ת�� С��1000
		// ������������һ��sql һ������
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		queryRunner
				.update("update account set money = money-1000 where name='С��'");
		int d = 1 / 0;
		queryRunner
				.update("update account set money = money+1000 where name='С��'");
	}

	@Test
	public void demo1() throws SQLException {
		// ׼���������Ӧ��,��ѯ����user��Ϣ
		ResultSetHandler<List<User>> rsHandler = new ResultSetHandler<List<User>>() {
			@Override
			public List<User> handle(ResultSet rs) throws SQLException {
				List<User> users = new ArrayList<User>();
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setBirthday(rs.getDate("birthday"));

					users.add(user);
				}
				return users;
			}
		};

		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

		List<User> users = queryRunner.query("select * from user", rsHandler);

		for (User user : users) {
			System.out.println(user.getId() + "," + user.getName() + ","
					+ user.getBirthday());
		}
	}
}
