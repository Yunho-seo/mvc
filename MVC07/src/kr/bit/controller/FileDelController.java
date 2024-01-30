package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class FileDelController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
		String filename = request.getParameter("filename");
		int num = Integer.parseInt(request.getParameter("num"));
		
		filename = URLEncoder.encode(filename, "UTF-8");  // 한글안깨지게
		
		filename = filename.replace("+", " ");  // 파일명의 +을 공백으로 대체
		
		String UPLOAD_DIR = "file_repo";  // 업로드하는 실제경로
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;  // 실제 물리적인 파일의 경로 
		File file = new File(uploadPath + "\\" + filename);
		if (file.exists()) {
			file.delete();  // 파일이 있으면 삭제 처리
			System.out.println("디렉터리에서 파일 삭제");
		}
		// 테이블에서도 파일명을 NULL 처리해야함 (update절)
		MemberDAO dao = new MemberDAO();	
		dao.memberDeleteFile(num);
		
		return "redirect:"+ctx+"/memberContent.do?num="+num;
	}

}
