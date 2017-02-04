package cn.itcast.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.service.CustomerService;
import cn.itcast.vo.Customer;

public class AddCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��������
		request.setCharacterEncoding("utf-8");

		// beanutils��װ
		Customer customer = new Customer();
		try {
			BeanUtils.populate(customer, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// û����ɰ��÷�װ���Լ���װ
		String[] preference = request.getParameterValues("preference");
		// ���磺���� ���� ------ [����,����]
		String value = Arrays.toString(preference);
		value = value.substring(1, value.length() - 1);
		customer.setPreference(value);

		// �鿴��װЧ��
		CustomerService service = new CustomerService();
		service.addCustomer(customer);

		// ��תindex.jsp
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
