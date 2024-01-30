package kr.bit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileGetController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String filename = request.getParameter("filename");  // 전달된 파일명 요청하여 받아오기(request)
		System.out.println(filename);  // 파일명 인코딩
		
		String UPLOAD_DIR = "file_repo";
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;  // 실제 물리적인 경로
		File f = new File(uploadPath + "\\" + filename);  // 디렉터리 내의 모든 정보를 파일 객체로 가져와야 함
		
		// 클라이언트로부터 넘어오는 파일명에 한글이 있는 경우 깨지지 않도록 인코딩
		filename = URLEncoder.encode(filename, "UTF-8");
		filename = filename.replace("+", " ");
		
		// 서버에서 클라이언트에게 다운로드 준비를 시키는 부분 (다운로드 창을 띄움)
		response.setContentLength((int)f.length());  // length()는 long형 (다운로드할 파일크기 설정)
		response.setContentType("application/x-msdownload;charset=utf-8");  // 컨텐츠타입 설정 (다운로드용)
		response.setHeader("Content-Disposition", "attachment;filename=" + filename + ";");  // 브라우저가 다운로드 창을 표시하도록 파일이름을 지정
		response.setHeader("Content-Transfer-Encoding", "binary");  // binary data 전송하는 것을 지정
		response.setHeader("Pragma", "no-cache");  // 캐싱 방지
		response.setHeader("Expires", "0");  // 리소스 만료시간이 지나자마자 캐시 만료 (헤더값이 0)
		
		// 실제 다운로드하는 부분
		FileInputStream in = new FileInputStream(f);    // 파일 읽기 준비
		OutputStream out = response.getOutputStream();  // 출력
		byte[] buffer = new byte[104];
		while(true) {		// 더이상 파일이 없을때까지
			int count = in.read(buffer);  // read() : 버퍼크기만큼 데이터 읽어와 실제 읽은 바이트수를 반환
			if(count==-1) {
				break;
			}
			// 다운로드(0% ... 100%)
			out.write(buffer, 0, count);  // 버퍼에 있는 데이터를 0번째부터 시작하여 읽어들인 데이터 수만큼 write
		}  // _while_
		in.close();
		out.close();
		
		return null;
	}
}