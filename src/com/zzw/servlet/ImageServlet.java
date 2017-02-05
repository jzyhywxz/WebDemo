package com.zzw.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet(urlPatterns="/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imagePath="F:\\workspace\\javaEE\\WebDemo\\res\\image\\butterfly.jpg";
		FileInputStream in=new FileInputStream(imagePath);
		byte[] buffer=new byte[in.available()];
		in.read(buffer);
		in.close();
		
		response.setContentType("image/jpg");
		OutputStream out=response.getOutputStream();
		out.write(buffer);
		out.flush();
		out.close();
	}

}
