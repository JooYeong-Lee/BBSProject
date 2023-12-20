package com.bbs.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bbs.DB.bbsDB;
import com.bbs.Repository.bbsRepository;

@Service
public class bbsServiceImpl implements bbsService {
	@Autowired
	private bbsRepository bbsrepository;
	
	public void insertbbs(String category, String content, String date, String id, String title, String fontsize, int filecount) {
		bbsDB bbsdb = new bbsDB();
		bbsdb.setCategory(category);
		bbsdb.setContent(content);
		bbsdb.setDate(date);
		bbsdb.setId(id);
		bbsdb.setTitle(title);
		bbsdb.setFontsize(fontsize);
		bbsdb.setFilecount(filecount);
		bbsrepository.save(bbsdb);
	}
	
	public Page<bbsDB> getAllBbs(Pageable pageable) {
        return bbsrepository.findAll(pageable);
    }
	
	public bbsDB getByID(Long id) {
	    return bbsrepository.findById(id).orElseThrow();
	}
	
	public void ChangeUserId(String beforeid, String afterid) {
		List<bbsDB> bbslist = bbsrepository.findById(beforeid);
		
		for(bbsDB bbs : bbslist) {
			bbs.setId(afterid);
		}
		bbsrepository.saveAll(bbslist);
	}
	
	//게시글 이미지 등록
	public void SaveImg(String id, String title, MultipartFile file, String filename) {
		//디렉토리 경로 생성
        String userDirPath = "src/main/resources/static/bbs/" + id;
        File userDir = new File(userDirPath);
        if (!userDir.exists()) {
            userDir.mkdirs(); // 디렉토리 없으면 생성
        }

        String bbsDirPath = userDirPath + "/" + title;
        File bbsDir = new File(bbsDirPath);
        if (!bbsDir.exists()) {
            bbsDir.mkdirs();
        }
        
        //파일의 확장자 추출
        String originalFilename = file.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFilename);

        //확장자 유지하고 파일 이름 변경
        String newFilename = filename + "." + fileExtension;
        String filePath = bbsDirPath + "/" + newFilename;
        
        Path path = Paths.get(filePath);
        // 파일 저장
        try {
			Files.write(path, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public List<String> getImgExtension(bbsDB bbsdb, int filecount){
		List<String> fileExtensions = new ArrayList<>();
		
		String filePath = "src/main/resources/static/bbs/" + bbsdb.getId() + "/" + bbsdb.getTitle();
		File folder = new File(filePath);
        
		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null) {
				
                for (File file : files) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        String extension = getFileExtension(fileName);
                        fileExtensions.add(extension);
                    }
                }
            }
		}
		return fileExtensions;
	}
	
	public String getImgExtensionString(bbsDB bbsdb, int filecount) {
		String filePath = "src/main/resources/static/bbs/" + bbsdb.getId() + "/" + bbsdb.getTitle();
		File folder = new File(filePath);
		String extension = null;
        
		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null && files.length > 0) {
		        File firstFile = files[0]; // 첫 번째 파일 가져오기

		        if (firstFile.isFile()) {
		            String fileName = firstFile.getName();
		            extension = getFileExtension(fileName);
		        }
            }
		}
		return extension;
	}
	
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";  // 확장자가 없는 경우 빈 문자열을 반환합니다.
    }

	public Page<bbsDB> findByTitle(String keyword,Pageable pageable){
		return bbsrepository.findByTitleContaining(keyword,pageable);
	}
	public Page<bbsDB> findfree(Pageable pageable){
       return bbsrepository.findByCategory("free", pageable);
    }

	public Page<bbsDB> findworries(Pageable pageable) {
		return bbsrepository.findByCategory("worries", pageable);
	}

	public Page<bbsDB> findsecret(Pageable pageable) {
		return bbsrepository.findByCategory("secret", pageable);
	}

	public Page<bbsDB> findpromotion(Pageable pageable) {
		return bbsrepository.findByCategory("promotion", pageable);
	}
}
