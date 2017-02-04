package cn.itcast.web.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.service.MyFileItemService;
import cn.itcast.utils.UploadUtils;
import cn.itcast.vo.MyFileItem;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1��������� 2�������ļ���Ӳ�� 3�����������Ϣ�����ݿ�
		// ����javabean����
		MyFileItem myFileItem = new MyFileItem();
		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(
					diskFileItemFactory);
			// �����ϴ��ļ������봦��
			servletFileUpload.setHeaderEncoding("utf-8");
			try {
				List<FileItem> fileItems = servletFileUpload
						.parseRequest(request);
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) {
						// ��ͨ����
						// ���name��value
						String name = fileItem.getFieldName();
						String value = fileItem.getString("utf-8");
						System.out.println(name + "===" + value);
						if (name.equals("realname")) {
							myFileItem.setRealname(value);
						} else if (name.equals("description")) {
							myFileItem.setDescription(value);
						}
					} else {
						// �ļ��ϴ���
						String name = fileItem.getName();
						String contentType = fileItem.getContentType();
						InputStream in = new BufferedInputStream(fileItem
								.getInputStream());

						// ����Ψһ�ļ���
						String uuidname = UploadUtils
								.generateRandonFileName(name);

						// ����Ŀ¼
						String dir = UploadUtils.generateRandomDir(uuidname);

						File dirFile = new File(getServletContext()
								.getRealPath("/WEB-INF/upload")
								+ dir);
						// ��ʱĿ¼��һ������
						dirFile.mkdirs();

						File outputFile = new File(dirFile, uuidname);
						OutputStream out = new BufferedOutputStream(
								new FileOutputStream(outputFile));

						int temp;
						while ((temp = in.read()) != -1) {
							out.write(temp);
						}
						in.close();
						out.close();

						// ɾ����ʱ�ļ�
						fileItem.delete();

						myFileItem.setSavepath(dir); // /8/9
						myFileItem.setUuidname(uuidname);
						myFileItem.setUploadtime(new Date(System
								.currentTimeMillis()));

						// �������ݿⱣ��
						MyFileItemService service = new MyFileItemService();
						service.addFileItem(myFileItem);

						response.getWriter().println("upload success!");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
