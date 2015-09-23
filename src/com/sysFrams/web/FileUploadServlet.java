package com.sysFrams.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.sysFrams.util.DateUtil;

public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = -7744625344830285257L;
	private ServletContext sc;
	private String savePath;
	private int fileMaxSize;
	private String fileType;
	private java.util.Random r=new java.util.Random();

	public void init(ServletConfig config) {
		// 在web.xml中设置的一个初始化参数
		savePath = config.getInitParameter("savePath");
		fileMaxSize=Integer.parseInt(config.getInitParameter("fileMazSize"))*1024*1024;
		fileType=config.getInitParameter("fileType");
		sc = config.getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		HttpSession session = request.getSession(false);
		if (session == null){write(response, "<script> parent.err('上传失败。');</script>"); return ;}//需要登录才可访问
		//获取get参数
		String del_f=request.getParameter("del_filename");
		if(!com.sysFrams.util.StrUtils.isBlank(del_f))
		{
			if(delFile(del_f)) write(response, "<script> parent.delFile('" + del_f + "');</script>");
			write(response, "<script> parent.err('删除失败。');</script>");
			return;
		}
		upload(request, response);
	}
	
	private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		//显示上传进度
//		upload.setProgressListener(new ProgressListener() {
//			long temp = -1;
//			public void update(long pBytesRead, long pContentLength, int pItems) {
//				long size = pBytesRead / 1024 * 1024 * 10;
//				if(temp == size)return;
//				temp = size;
//				if(pBytesRead != -1)System.out.println("已上传：" + pBytesRead + "总大小：" + pContentLength);
//				else System.out.println("上传完成！");
//			}
//		});
		String msg ="";
		try {
			List items = upload.parseRequest(request);
			if(items.isEmpty()) return;
			String path = buildDir(savePath + getClientSavePath(items));
			if(path==null)
			{write(response, "<script> alert('路径错误，上传失败。');</script> "); return;}
			
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (!item.isFormField()) 
					if (item.getName() != null && !item.getName().equals("")) {
						if(item.getSize()>fileMaxSize){msg=msg+"文件过大。";continue;}
						String ext_name =item.getName().substring(item.getName().lastIndexOf(".")+1); 
						if(fileType.indexOf(ext_name)==-1){msg=msg+"不允许上传该类型文件。";continue;}

						String fileName = buildFileName(item.getName());
						// 上传文件的保存路径
						File file = new File(sc.getRealPath("/") + path,fileName);
						item.write(file);
						msg = msg +"<script> parent.addFile('" + path+"/"+fileName + "');</script> ";
					} else {
						msg = msg+ "没有选择上传文件！";
					}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			msg = msg+ "上传失败！";
		}
		write(response, msg);
	}
	
	private void write(HttpServletResponse response,String msg) throws IOException
	{
		response.setCharacterEncoding("UTF_8");
		response.setHeader("Content-type","text/html;charset=UTF-8");
		response.getWriter().write(msg);
		response.getWriter().close();
	}
	private boolean checkDir(String path)
	{
		boolean bool = true;
		File dir = new File(path);
		if(!dir.exists()) bool = dir.mkdir();
		return bool;
	}
	private String buildDir(String path)
	{
		if(checkDir(sc.getRealPath("/") + path)==false) return null;
		String dir = DateUtil.getFormatDate(DateUtil._yyyyMMdd).replace("-", "");
		path = path +"/" + dir;
		if(checkDir(sc.getRealPath("/") + path)==false) return null;
		return path;
	}
	//获取页面上传的保存路径
	private String getClientSavePath(List items)
	{
		String path = "";
		Iterator itr = items.iterator();
		try{
			while(itr.hasNext())
			{
				FileItem item = (FileItem)itr.next();
				if(item.isFormField())
					if(item.getFieldName().equals("fileUPpath"))
						{
							path = item.getString("UTF-8");
							if(!path.equals(""))path = "/" +path ;
						}
			}
		}catch(UnsupportedEncodingException e){e.printStackTrace();}
		return path;
	}
	private String buildFileName(String fileName)
	{
		Integer i = r.nextInt(100000); 
		String name = i.toString() + fileName.substring(fileName.lastIndexOf("."));
		return name;
	}
	//filename使用文件全名，即‘路径+名称’
	private Boolean delFile(String filename){
        File file=new File(sc.getRealPath("/")+filename);
        if(file.exists()&&file.isFile())
        {
    		file.delete();
    		return true;
        }
        return false;
    }

}