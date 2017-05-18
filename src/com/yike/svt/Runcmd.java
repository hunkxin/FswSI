package com.yike.svt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yike.utility.JsonOp;

/**
 * Servlet implementation class Runcmd
 */
@WebServlet("/Runcmd")
public class Runcmd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Runcmd() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		String datas = request.getParameter("p");
		PrintWriter out = response.getWriter();
		out.print(JsonOp.byjson(datas));
		out.flush();
		out.close();
	}

}
