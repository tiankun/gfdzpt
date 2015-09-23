<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.json.simple.*" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONString" %>
<%@ page language="java" import="java.awt.*"%>
<%@ page language="java" import="java.awt.image.*"%>
<%@ page language="java" import="com.sun.image.codec.jpeg.*"%>
<%

/**
 * KindEditor JSP
 * 
 * ��JSP��������ʾ���򣬽��鲻Ҫֱ����ʵ����Ŀ��ʹ�á�
 * �����ȷ��ֱ��ʹ�ñ�����ʹ��֮ǰ����ϸȷ����ذ�ȫ���á�
 * 
 */

//�ļ�����Ŀ¼·��
String savePath = pageContext.getServletContext().getRealPath("/") + "upFile/gm/info/";

//�ļ�����Ŀ¼URL
String saveUrl  = request.getContextPath() + "/upFile/gm/info/";

//���������ϴ����ļ���չ��
HashMap<String, String> extMap = new HashMap<String, String>();
extMap.put("image", "gif,jpg,jpeg,png,bmp");
extMap.put("flash", "swf,flv");
extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");

//����ļ���С
long maxSize = 20971520;

response.setContentType("text/html; charset=GBK");

if(!ServletFileUpload.isMultipartContent(request)){
	out.println(getError("��ѡ���ļ���"));
	return;
}
//���Ŀ¼
File uploadDir = new File(savePath);
if(!uploadDir.isDirectory()){
	uploadDir.mkdirs();
	//out.println(getError("�ϴ�Ŀ¼�����ڡ�"));
	//return;
}
//���Ŀ¼дȨ��
if(!uploadDir.canWrite()){
	out.println(getError("�ϴ�Ŀ¼û��дȨ�ޡ�"));
	return;
}

String dirName = request.getParameter("dir");
if (dirName == null) {
	dirName = "image";
}
if(!extMap.containsKey(dirName)){
	out.println(getError("Ŀ¼������ȷ��"));
	return;
}
//�����ļ���
savePath += dirName + "/";
saveUrl += dirName + "/";
File saveDirFile = new File(savePath);
if (!saveDirFile.exists()) {
	saveDirFile.mkdirs();
}
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
String ymd = sdf.format(new Date());


File dirFile = new File(savePath);
if (!dirFile.exists()) {
	dirFile.mkdirs();
}

FileItemFactory factory = new DiskFileItemFactory();
ServletFileUpload upload = new ServletFileUpload(factory);
upload.setHeaderEncoding("GBK");
java.util.List items = upload.parseRequest(request);
Iterator itr = items.iterator();
while (itr.hasNext()) {
	FileItem item = (FileItem) itr.next();
	String fileName = item.getName();
	long fileSize = item.getSize();
	if (!item.isFormField()) {
		//����ļ���С
		if(item.getSize() > maxSize){
			out.println(getError("�ϴ��ļ���С�������ơ�"));
			return;
		}
		//�����չ��
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
			out.println(getError("�ϴ��ļ���չ���ǲ��������չ����\nֻ����" + extMap.get(dirName) + "��ʽ��"));
			return;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		try{
			File uploadedFile = new File(savePath, newFileName);
			item.write(uploadedFile);
			
		}catch(Exception e){
			out.println(getError("�ϴ��ļ�ʧ�ܡ�"));
			return;
		}

		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", saveUrl + newFileName);
		out.println(obj.toString());
	}
}
%>
<%!
private String getError(String message) {
	JSONObject obj = new JSONObject();
	obj.put("error", 1);
	obj.put("message", message);
	return obj.toString();
}
%>