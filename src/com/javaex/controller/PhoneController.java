package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {

	/**
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("pbc호출 완료");

		// parameter action값 읽기
		String action = request.getParameter("action");

		if ("list".equals(action)) {
			System.out.println("리스트");
			// list
			PhoneDao phoneDao = new PhoneDao();

			List<PersonVo> personList = phoneDao.getPersonList();

			System.out.println("controller에서 출력 : " + personList);

			// 데이터 넣기 -->request 어트리뷰트에 데이터를 넣어준다.
			request.setAttribute("pList", personList); // List형식
			//request.setAttribute("name", "장재영"); // String형식
			//request.setAttribute("num", 26); // int형식
			// 데이터를 전해줄 때는 request 안에 넣어서 전해주어야한다.(attribute)

			// html작업은 .jsp에게 할당 ==> forword(포워드)
			RequestDispatcher rd = request.getRequestDispatcher(/* 1.작업을 할당할 담당자설정 */"/WEB-INF/list.jsp");// 파일 경로를 WEB-INF파일 하위에 jsp파일을 넣어놓으면 직접 주소로(url방식으로) 접근할 수 없게끔 한다.
			rd.forward(request, response);

		}else if("wform".equals(action)) {
			//writeForm
			System.out.println("글쓰기폼");
			
			//writeForm.jsp에게 포워드 --> 넘겨 줄 데이터 없음
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			
		}else if("insert".equals(action)) {
			System.out.println("저장");
			
			//dao --> 저장
			//parameter 꺼내기 --> (name,hp,company) --> writeForm.jsp에서 받은 parameter값 꺼내기
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//Vo로 묶어주기
			PersonVo personVo = new PersonVo(name,hp,company);
			System.out.println(personVo);
			
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.personInsert(personVo);
			
			//Redirect
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}
		else if("uform".equals(action)) {
			//updateForm
			System.out.println("업데이트폼");
			
			int iNo = Integer.parseInt(request.getParameter("id"));
			
			PhoneDao phoneDao = new PhoneDao();
			PersonVo personVo = phoneDao.getPerson(iNo);
			
			request.setAttribute("pVo", personVo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response);
			
		}else if("update".equals(action)) {
			//update
			System.out.println("업데이트");
			
			int personId = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo personVo = new PersonVo(personId,name,hp,company);
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personUpdate(personVo);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		}else if("delete".equals(action)) {
			//delete
			System.out.println("삭제");
			
			int iNo = Integer.parseInt(request.getParameter("id"));
			
			//System.out.println(iNo);
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(iNo);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
