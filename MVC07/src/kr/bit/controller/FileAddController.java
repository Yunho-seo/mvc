package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileAddController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파일 데이터 처리하기
		
		String UPLOAD_DIR = "file_repo";  // 업로드될 파일을 저장할 폴더			
		// 업로드된 파일이 저장될 실제 디렉터리의 전체 경로
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		File currentDirPath = new File(uploadPath);  // 실제 파일 업로드할 경로를 File객체로 지정
		
		// 경로에 디렉터리가 있는지 여부 검사
		if(!currentDirPath.exists()) {  // 없으면 디렉터리 생성
			currentDirPath.mkdir();
		}
		
		// 클라이언트가 서버로 파일업로드 요청 시 서버는 중간에 임시 메모리를 만든다.
		// 이후, 임시로 저장할 임시 경로를 만듦 (임시 디렉터리 or 실제 디렉터리)
		// 메모리에 직접 저장하기 부담스러워 임시 디렉터리를 생성한다.
		
		// 파일업로드 시 필요한 API -> commons-fileupload, commons-io
		// 파일을 업로드할 때 저장될 임시 경로 설정
		DiskFileItemFactory factory = new DiskFileItemFactory();  // 파일 아이템 생성하는 객체
		factory.setRepository(currentDirPath);  // 업로드 파일의 임시 저장소
		factory.setSizeThreshold(1024*1024);  // 디렉터리 저장 시 용량
		
		String fileName = null;  // 파일명
		
		// 파일 업로드 객체가 실제 파일을 factory에 업로드
		ServletFileUpload upload = new ServletFileUpload(factory);
		// request에 저장된 파일을 읽어와 factory에 넣어줌
		// upload로 읽어와야함
		try {
			// request내의 여러개 파일이 업로드된 경우 List로 (items에는 파일 정보들이 들어있음)
			// items -> [ ], [ ], [ ] (파일 배열, 파일 아이템)
			List<FileItem> items = upload.parseRequest(request);  //  request에 들어있는 파일정보를 쉽게 읽어올 수 있도록 변환
			for (int i=0; i<items.size(); i++) {
				FileItem fileItem = items.get(i);
				if (fileItem.isFormField()) { // 폼필드이면 (파라미터이면, 텍스트 필드)
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString("utf-8")); 
				} else {  // 파일이면
					if (fileItem.getSize() > 0) {  // 파일사이즈 0보다 클 때 업로드
						// 정상적으로 업로드 됨, 임시 저장소에 업로드 -> 파일명 불러오기
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {  // 리눅스 시스템일 때
							idx = fileItem.getName().lastIndexOf("/");
						}
						fileName = fileItem.getName().substring(idx+1);  // 파일명 추출
						// 임시 디렉터리에 파일을 실제로 만들어야 함
						File uploadFile = new File(currentDirPath+"\\"+fileName);
						if (uploadFile.exists()) {  // 파일 중복체크 - 중복되면 이름 바꾸기
							fileName = System.currentTimeMillis()+"_"+fileName;
							uploadFile = new File(currentDirPath+"\\"+fileName);
						}
						fileItem.write(uploadFile);  // 임시 경로에서 새 경로에 파일 쓰기(저장)
					}
				}
			} // _for_
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 업로드된 최종 파일명을 $.ajax()쪽으로 전송시켜준다.
		response.setContentType("text/html;charset=euc-kr");
		response.getWriter().print(fileName);
		
		return null;
	}
}