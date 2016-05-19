package com.yinyang.yy.utils;

//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileItemIterator;
//import org.apache.commons.fileupload.FileItemStream;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.fileupload.util.Streams;

/**
 * Servlet implementation class BillImageServlet
 * 接收enctype="multipart/form-data"的二进制流文件
 */

public class MultipartUploadUtils
{
    //private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultipartUploadUtils()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    /*@Override
    protected void doGet(Object request, Object response) throws IOException
    {
        // TODO Auto-generated method stub
        this.doPost(request, response);
    }

    *//**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     *//*
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(Object request, Object response) throws IOException
    {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		response.setCharacterEncoding("UTF-8");
        OutputStream os = response.getOutputStream();
        //File tmpDir = new File("D:/imagetest/temp"); //初始化上传文件临时存放目录,必须是绝对路径
        try
        {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart)
            {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload sfu = new ServletFileUpload(factory);

                List<FileItem> parseRequest = sfu.parseRequest(request);
                for (int i = 0; i < parseRequest.size(); i++)
                {
                    FileItem f = parseRequest.get(i);
                    //if是文本域，else为表单中文件域
                    if (f.isFormField())
                    {
                        System.out.println("普通文本表单：" + f.getFieldName() + "=" + f.getString());
                    }
                    else
                    {
                        String fileName = f.getName().substring(f.getName().lastIndexOf("."));// 获得上传文件的文件名 
                        //fileName = sdf.format("D:/imagetest/" + fileName);
                        File file = new File("D:/imagetest/" + sdf.format(new Date()) + fileName);
                        f.write(file);
                        System.out.println("文件接收完成，存放路径-->" + file.getPath());
                    }
                }
				os.write(new String("{\"result\":\"true\",\"msg\":\"处理文件成功\"}").getBytes());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
			os.write(new String("{\"result\":\"false\",\"msg\":\"处理文件失败\"}").getBytes());
        }
        try
        {
            this.before(request, response);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void before(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        //File tmpDir = new File("D:/imagetest/temp"); //初始化上传文件临时存放目录,必须是绝对路径
        try
        {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart)
            {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                //指定在内存中缓存数据大小,单位为byte,这里设为1Mb 
                //factory.setSizeThreshold(1 * 1024 * 1024);
                //设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
                //factory.setRepository(tmpDir);
                ServletFileUpload sfu = new ServletFileUpload(factory);
                //指定单个上传文件的最大尺寸,单位:字节，这里设为100Mb
                sfu.setFileSizeMax(100 * 1024 * 1024);
                sfu.setSizeMax(-1);
                sfu.setHeaderEncoding("UTF-8"); //设置编码utf-8
                FileItemIterator fii = sfu.getItemIterator(request);// 解析request请求
                String uploadPath = "D:/imagetest/"; //上传的目录
                if (!new File(uploadPath).isDirectory())
                {
                    new File(uploadPath).mkdirs();
                }
                int index = 0;
                while (fii.hasNext())
                {
                    FileItemStream fis = fii.next();
                    if (fis.isFormField())
                    {
                        String value = Streams.asString(fis.openStream(), "UTF-8");
                        System.out.println("普通文本表单：" + fis.getFieldName() + "=" + value);
                    }
                    else if (!fis.isFormField() && fis.getName().length() > 0) //过滤掉表单中非文件域
                    {
                        //如果文件域没有选择文件，则忽略处理
                        if (fis.openStream().available() != 0)
                        {
                            String fileName = fis.getName().substring(fis.getName().lastIndexOf("."));// 获得上传文件的文件名 
                            fileName = sdf.format(new Date()) + "-" + index + fileName;
                            BufferedInputStream in = new BufferedInputStream(fis.openStream());
                            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(uploadPath + "/" + fileName)));
                            Streams.copy(in, out, true);
                            index++;
                            out.flush();
                            in.close();
                        }
                    }
                }

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void morebefore(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        request.setCharacterEncoding("UTF-8");
        try
        {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart)
            {
                ServletFileUpload upload = new ServletFileUpload();
                upload.setSizeMax(-1);
                FileItemIterator iter = upload.getItemIterator(request);
                while (iter.hasNext())
                {
                    FileItemStream fis = iter.next();
                    String formName = fis.getFieldName();
                    InputStream is = fis.openStream();
                    if (fis.isFormField())
                    {
                        String value = Streams.asString(is, request.getCharacterEncoding());
                        System.out.println("这是一个普通文本表单：" + formName + "=" + value);
                    }
                    else
                    {
                        //如果文件域没有选择文件，则忽略处理
                        if (is.available() != 0)
                        {
                            String uploadFileName = fis.getName();
                            if (uploadFileName != null)
                            {
                                uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1, uploadFileName.length());
                            }
                            //String filePath = "E:/" + uploadFileName;
                            String filePath = "D:/imagetest/" + uploadFileName;
                            Streams.copy(is, new FileOutputStream(filePath), true);

                        }
                    }
                }//while()

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }*/

}
