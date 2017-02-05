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
				ac.getResponse().getWriter().println("文件上传成功！<br>");
				ac.dispatch("/upload");
			}

			@Override
			public void onError(AsyncEvent event) throws IOException {
				event.getAsyncContext().getResponse().getWriter().println("文件上传出错！<br>");
			}

			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
				event.getAsyncContext().getResponse().getWriter().println("开始上传文件...<br>");
			}

			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				event.getAsyncContext().getResponse().getWriter().println("文件上传超时！<br>");
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
				// 文件上传头
				Collection<String> headers=part.getHeaderNames();
				// 文件类型
				String fileType=part.getContentType();
				// 文件大小
				long fileSize=part.getSize();
				// 客户端上传的文件名
				String submitName=part.getSubmittedFileName();
				// 服务器保存的文件名
				String fileName=UUID.randomUUID().toString()+
						submitName.substring(submitName.lastIndexOf('.'));
				
				out.println("HEADER: "+"<br>");
				for(String header : headers)
					out.println(header+"="+part.getHeader(header)+"<br>");
				out.println("文件类型: "+fileType+"<br>");
				out.println("文件大小: "+fileSize+"<br>");
				out.println("客户端文件名: "+submitName+"<br>");
				out.println("服务端文件名: "+fileName+"<br>");
				
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
