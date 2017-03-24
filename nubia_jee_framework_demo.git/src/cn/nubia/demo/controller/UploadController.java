package cn.nubia.demo.controller;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nubia.framework.entity.Path;
import cn.nubia.framework.mvc.upload.FileAlreadyExistsException;
import cn.nubia.framework.mvc.upload.FileSliceException;
import cn.nubia.framework.mvc.upload.FileTooLargeException;
import cn.nubia.framework.mvc.upload.FileTypeNotSupportedException;
import cn.nubia.framework.mvc.upload.IllegalRequestException;
import cn.nubia.framework.mvc.upload.Upload;
import cn.nubia.framework.mvc.upload.UploadException;
import cn.nubia.framework.mvc.upload.UploadFile;
import cn.nubia.framework.mvc.upload.Uploader;

@Controller
@RequestMapping("/upload")
//@Scope("prototype")
public class UploadController {
	
	private static final String UPLOAD_PATH = Path.APP_PATH + "temp";

	@RequestMapping("/upload_single_file")
	@ResponseBody
	public String uploadSingleFile(HttpServletRequest request) throws FileTooLargeException, IllegalRequestException, FileTypeNotSupportedException, FileAlreadyExistsException, FileSliceException, UploadException {
		Uploader uploader = new Upload(request);
		uploader.setAutoRenameFile(true);
		uploader.save(UPLOAD_PATH, 102400);
		System.out.println("Single File Uploading...");
		UploadFile file = uploader.getUploadFile("Filedata"); // 单个文件
		System.out.println("Filename:"+ file.getFilename());
		Map<String, String> params = uploader.getParameters();
		if (!params.isEmpty()) {
			System.out.println("username:"+params.get("username"));
			System.out.println("password:"+params.get("password"));
		}
		return file.getFile().getName();


	}

	@RequestMapping("/upload_multi_file")
	@ResponseBody
	public String uploadMultiFile(HttpServletRequest request) throws FileTooLargeException, IllegalRequestException, FileTypeNotSupportedException, FileAlreadyExistsException, FileSliceException, UploadException {
		Uploader uploader = new Upload(request);
		uploader.setAutoRenameFile(true);
		uploader.save(UPLOAD_PATH,102400);
		System.out.println("Multi File Uploading...");
		Map<String,UploadFile> files = uploader.getUploadFiles();// 多个文件
		StringBuilder sb = new StringBuilder();
		for(String name: files.keySet()){
			UploadFile file = files.get(name);
			//String fullfilename = file.getUploadingAbsoluteFileName();
			//String filename = new File(fullfilename).getName();
			sb.append(file.getFile().getName()).append(',');
			System.out.println("Filename:" +file.getFile().getName());
		}
		Map<String, String> params = uploader.getParameters();
		if (!params.isEmpty()) {
			System.out.println("username:"+params.get("username"));
			System.out.println("password:"+params.get("password"));
		}
		
		if(sb.length()>1)sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	@RequestMapping("/upload_files_by_flash")
	@ResponseBody
	public String uploadFilesByFlash(HttpServletRequest request) throws FileTooLargeException, IllegalRequestException, FileTypeNotSupportedException, FileAlreadyExistsException, FileSliceException, UploadException {
		Uploader uploader = new Upload(request);
		uploader.setAutoRenameFile(true);
		uploader.save(UPLOAD_PATH, 102400);
		
		System.out.println("Multi File Uploading...");
		System.out.println("username:"+request.getParameter("username"));
		System.out.println("password:"+request.getParameter("password"));

		Map<String,UploadFile> files = uploader.getUploadFiles();// 多个文件
		StringBuilder sb = new StringBuilder();
		for(String name: files.keySet()){
			UploadFile file = files.get(name);
			//String fullfilename = file.getUploadingAbsoluteFileName();
			//String filename = new File(fullfilename).getName();
			sb.append(file.getFile().getName()).append(',');
			System.out.println("Filename:" +file.getFile().getName());
		}
		Map<String, String> params = uploader.getParameters();
		if (!params.isEmpty()) {
			System.out.println("username:"+params.get("username"));
			System.out.println("password:"+params.get("password"));
		}
		
		if(sb.length()>1)sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	@RequestMapping("/upload_file_by_html5")
	@ResponseBody
	public String uploadFileByHtml5(HttpServletRequest request) throws FileTooLargeException, IllegalRequestException, FileTypeNotSupportedException, FileAlreadyExistsException, FileSliceException, UploadException {
		System.out.println("html5 header..........");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + " : " + headerValue);
		}
		System.out.println("Html5 Uploading...");
		
		Uploader uploader = new Upload(request);
		uploader.setAutoRenameFile(true);
		uploader.save(UPLOAD_PATH, 102400);
		
		Map<String,UploadFile> files = uploader.getUploadFiles();// 多个文件
		StringBuilder sb = new StringBuilder();
		for(String name: files.keySet()){
			UploadFile file = files.get(name);
			//String fullfilename = file.getUploadingAbsoluteFileName();
			//String filename = new File(fullfilename).getName();
			sb.append(file.getFile().getName()).append(',');
			System.out.println("Filename:" +file.getFile().getName());
		}
		Map<String, String> params = uploader.getParameters();
		if (!params.isEmpty()) {
			System.out.println("username:"+params.get("username"));
			System.out.println("password:"+params.get("password"));
		}
		
		if(sb.length()>1)sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	@RequestMapping("/upload_file_by_slice")
	@ResponseBody
	public String uploadFileBySlice(HttpServletRequest request) throws FileTooLargeException, IllegalRequestException, FileTypeNotSupportedException, FileAlreadyExistsException, FileSliceException, UploadException {
		System.out.println("slice header..........");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + " : " + headerValue);
		}
		System.out.println("Slice Uploading...");
		
		Uploader uploader = new Upload(request);
		uploader.setAutoRenameFile(true);
		uploader.save(UPLOAD_PATH, 102400);
		
		Map<String,UploadFile> files = uploader.getUploadFiles();// 多个文件
		StringBuilder sb = new StringBuilder();
		for(String name: files.keySet()){
			UploadFile file = files.get(name);
			//String fullfilename = file.getUploadingAbsoluteFileName();
			//String filename = new File(fullfilename).getName();
			sb.append(file.getFile().getName()).append(',');
			System.out.println("Filename:" +file.getFile().getName());
		}
		Map<String, String> params = uploader.getParameters();
		if (!params.isEmpty()) {
			System.out.println("username:"+params.get("username"));
			System.out.println("password:"+params.get("password"));
		}
		
		if(sb.length()>1)sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	@RequestMapping("/uploader_files_by_html5_slice")
	@ResponseBody
	public  String uploaderFileByHtml5Slice(HttpServletRequest request) throws FileTooLargeException, IllegalRequestException, FileTypeNotSupportedException, FileAlreadyExistsException, FileSliceException, UploadException {
		/*System.out.println("slice header..........");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + " : " + headerValue);
		}
		System.out.println("Slice Uploading...");
		*/
		
		Uploader uploader = new Upload(request);
		uploader.setAutoRenameFile(true);
		uploader.save(UPLOAD_PATH, 2048);
		Map<String,UploadFile> files = uploader.getUploadFiles();
		StringBuilder sb = new StringBuilder();
		for(String name: files.keySet()){
			UploadFile file = files.get(name);
			//String fullfilename = file.getUploadingAbsoluteFileName();
			//String filename = new File(fullfilename).getName();
			sb.append(file.getFile().getName()).append(',');
			System.out.println("Filename:" +file.getFile().getName());
		}
		/*
		Map<String, String> params = upload.getParameters();
		if (!params.isEmpty()) {
			System.out.println("username:"+params.get("username"));
			System.out.println("password:"+params.get("password"));
		} */
		if(sb.length()>1)sb.deleteCharAt(sb.length()-1);
		System.out.println("result:"+sb.toString());
		System.out.println();
		return sb.toString();
	}
}
