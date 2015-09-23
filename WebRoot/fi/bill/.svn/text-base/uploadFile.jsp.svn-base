<%@page contentType="text/html;charset=UTF-8"%><%@page import="java.util.*"%><%@page import="java.io.*"%><%@page import="org.apache.commons.fileupload.*"%><%
try{
String myFileNmae="";
File upPath=new File(application.getRealPath("/upFile/fi/bill"));
if(!upPath.isDirectory())
upPath.mkdirs();
DiskFileUpload fu = new DiskFileUpload();
//设置最大文件尺寸，这里是200kb
fu.setSizeMax(1024*1024);
//设置缓冲区大小，这里是4kb
fu.setSizeThreshold(4096);
//设置临时目录：
File tempPath=new File(getServletContext().getRealPath("/temp/"));
if(!tempPath.isDirectory())
tempPath.mkdirs();
fu.setRepositoryPath(getServletContext().getRealPath("/temp/"));
//得到所有的文件：
List fileItems = fu.parseRequest(request);
Iterator i = fileItems.iterator();
//依次处理每一个文件：
while(i.hasNext()){
FileItem fi = (FileItem)i.next();
if(!fi.isFormField()){
//获得文件名，这个文件名包括路径：
String fileName = fi.getName();

//String [] filede = fileName.split("\\.");
//System.out.println("aaaaaaaa +++  "+filede[1]);


if(fileName!=null) {
//在这里可以记录用户和文件信息
//写入文件a.txt，你也可以从fileName中提取文件名:
File myFile=new File(fileName);
String extname = fileName.substring(fileName.lastIndexOf(".")+1);
fileName =""+(new Date()).getTime()+"."+extname;
fi.write(new File(getServletContext().getRealPath("/upFile/fi/bill")+"/"+ fileName));
out.print(fileName);
}
}
}
}catch(Exception e){
out.print(e.getMessage());
}
%>