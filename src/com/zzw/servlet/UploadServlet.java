package com.zzw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(
		urlPatterns = { "/upload" }, 
		initParams = { 
				@WebInitParam(name = "basepath", value = "F:\\workspace\\javaEE\\WebDemo\\res\\")
		}, 
		asyncSupported = true)
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private String basePath;
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		basePath=config.getInitParameter("basepath");
	}
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AsyncContext ac=request.startAsync(request, response);
		ac.setTimeout(10*60*1000);
		ac.addListener(new AsyncListener() {
			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				AsyncContext ac=event.getAsyncContext();
				ac.getResponse().getWriter().println("�ļ��ϴ��ɹ���<br>");
				ac.dispatch("/upload");
			}

			@Override
			public void onError(AsyncEvent event) throws IOException {
				event.getAsyncContext().getResponse().getWriter().println("�ļ��ϴ�����<br>");
			}

			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
				event.getAsyncContext().getResponse().getWriter().println("��ʼ�ϴ��ļ�...<br>");
			}

			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				event.getAsyncContext().getResponse().getWriter().println("�ļ��ϴ���ʱ��<br>");
			}
		});
		ac.start(new UploadTask(ac, basePath));
	}

	public static class UploadTask implements Runnable {
		private AsyncContext ac;
		private String basePath;
		
		public UploadTask(AsyncContext a, String bp) {
			ac=a;
			basePath=bp;
		}

		@Override
		public void run() {
			HttpServletRequest request=(HttpServletRequest) ac.getRequest();
			HttpServletResponse response=(HttpServletResponse) ac.getResponse();
			
			response.setContentType("text/html;charset=GBK");
			PrintWriter out;
			try {
				out = response.getWriter();
				Part part=request.getPart("file");
				// �ļ��ϴ�ͷ
				Collection<String> headers=part.getHeaderNames();
				// �ļ�����
				String fileType=part.getContentType();
				// �ļ���С
				long fileSize=part.getSize();
				// �ͻ����ϴ����ļ���
				String submitName=part.getSubmittedFileName();
				// ������������ļ���
				String fileName=UUID.randomUUID().toString()+
						submitName.substring(submitName.lastIndexOf('.'));
				
				out.println("HEADER: "+"<br>");
				for(String header : headers)
					out.println(header+"="+part.getHeader(header)+"<br>");
				out.println("�ļ�����: "+fileType+"<br>");
				out.println("�ļ���С: "+fileSize+"<br>");
				out.println("�ͻ����ļ���: "+submitName+"<br>");
				out.println("������ļ���: "+fileName+"<br>");
				
				if(fileType.startsWith("image"))
					part.write(basePath+"image\\"+fileName);
				else if(fileType.startsWith("audio"))
					part.write(basePath+"audio\\"+fileName);
				else if(fileType.startsWith("video"))
					part.write(basePath+"video\\"+fileName);
				else
					part.write(basePath+"file\\"+fileName);
				
				ac.complete();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}
}
