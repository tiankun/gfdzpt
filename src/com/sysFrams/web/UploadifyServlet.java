package com.sysFrams.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.map.Sys_uppic;
import com.sysFrams.db.DataBaseControl;

public class UploadifyServlet extends HttpServlet {

	DataBaseControl dataBaseControl=DataBaseControl.getInstance();
	private static final long serialVersionUID = 1L;

	//上传文件的保存路径
	protected String configPath = "uploads";
//
//	protected String dirTemp = "uploads/temp/";
	
	protected String dirName = "file";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 this.doPost(request, response);
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String folder = request.getParameter("folder")==null?"":request.getParameter("folder");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//文件保存目录路径
		String savePath = this.getServletContext().getRealPath("/") + configPath +"/" + folder;
		String f_id = request.getParameter("f_id");
		String flag = request.getParameter("flag");
		String oldname = request.getParameter("oldname")==null?"":request.getParameter("oldname");
		
		// 临时文件目录 
//		String tempPath = this.getServletContext().getRealPath("/") + dirTemp;
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		String ymd = sdf.format(new Date());
//		savePath += "/" + ymd + "/";
		//创建文件夹
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
//		tempPath += "/" + ymd + "/";
//		//创建临时文件夹
//		File dirTempFile = new File(tempPath);
//		if (!dirTempFile.exists()) {
//			dirTempFile.mkdirs();
//		}
		
		DiskFileItemFactory  factory = new DiskFileItemFactory();
		factory.setSizeThreshold(20 * 1024 * 1024); //设定使用内存超过5M时，将产生临时文件并存储于临时目录中。   
//		factory.setRepository(new File(tempPath)); //设定存储临时文件的目录。   

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		
		try {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				if(fileName!=null&&!fileName.endsWith(".jsp")) {
					long fileSize = item.getSize();
					if (!item.isFormField()) {
						String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						
						SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
						String newFileName="";
							newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
						try{
							File uploadedFile = new File(savePath, newFileName);
		                    OutputStream os = new FileOutputStream(uploadedFile);
		                    InputStream is = item.getInputStream();
		                    byte buf[] = new byte[1024];//可以修改 1024 以提高读取速度
		                    int length = 0;  
		                    while( (length = is.read(buf)) > 0 ){  
		                        os.write(buf, 0, length);  
		                    }  
		                    //关闭流  
		                    os.flush();
		                    os.close();  
		                    is.close();  
		                    
		                    Sys_uppic sys_uppic = new Sys_uppic();
		                    sys_uppic.setF_id(BigDecimal.valueOf(Double.parseDouble(f_id)));
		                    sys_uppic.setFlag(new String(flag));
		                    sys_uppic.setPic(newFileName);
		                    sys_uppic.setExt(fileExt);
		                    sys_uppic.setName(fileName);
		                    dataBaseControl.insertByBean(sys_uppic);
		                    out.print(newFileName);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				else {}			
			} 
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
