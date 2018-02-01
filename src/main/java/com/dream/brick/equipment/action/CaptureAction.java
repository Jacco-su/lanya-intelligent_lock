package com.dream.brick.equipment.action;

import com.dream.framework.dao.Pager;
import com.dream.util.extend.FtpUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static javax.xml.crypto.dsig.Transform.BASE64;


@Controller
@Scope("prototype")
@RequestMapping("/capture")
public class CaptureAction {
    private static final String JPG = ".jpg";
//    private FtpUtils readFTP;

    private String GIF;

    private Logger log = Logger.getLogger(this.getClass());
    public FtpUtil ftpUtil;


    @RequestMapping("/iList")

    public String iList(String id, ModelMap model, String path) {

//                    List list = readFTP.getFileList(path);
//            if ( ) {
//
//                List<FtpUtil> list = ftpUtil.connectFtp();
//            }else {
//                ftpUtil.connectFtp();
//                File<FtpUtil> file = ftpUtil.downloadFile();
//            }
//            ftpUtil.closeFtp();
//        model.addAttribute("dissList", ftp.downloadFile());
//
//            java.util.List<Imaging> fileList = new ArrayList<Imaging>();
//            try{
//                public static Properties p;
//                fileList = FileUtil.readFile(p.getProperty("upload_path"));
//                setAttr("fileList",fileList);
//                render("file_list.html");
//            }catch(Exception e){
//                e.printStackTrace();
//            }

//            BufferedImage srcImg = ImageIO.read(srcFile);// 读取原图
//            ImageIO.write(srcImg );// 用指定格式输出到指定文件


//            在servlet 或 action中用IO流读取图片，输出到html中，


//            FileUtil.readFile("**/webapp/uploads");

        // 获取上传文件的目录
//                ServletContext sc = request.getSession().getServletContext();
//                // 上传位置
//                String uploadFilePath = sc.getRealPath("/uploads/workticket") + "/"; // 设定文件保存的目录
//                // 存储要下载的文件名
//                Map<String, String> fileNameMap = new HashMap<String, String>();
//                // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
////                listfile(new File(uploadFilePath), fileNameMap);// File既可以代表一个文件也可以代表一个目录
//                // 将Map集合发送到listfile.jsp页面进行显示
//                request.setAttribute("fileNameMap", fileNameMap);


        return "capture/ilist";
    }


    @RequestMapping(value = "/icture", method = {RequestMethod.POST})
//    @ResponseBody
//    public String list(int page, int rows, Pager pager,HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        pager.setCurrentPage(page);
//        pager.setPageSize(rows);
//        JSONObject datas = new JSONObject();
//        List<FtpUtil> list = ftpUtil.findCollectorList(pager);
//        datas.put("total", pager.getTotalRow());
//        datas.put("rows", list);
//        return datas.toString();

//        FileInputStream hFile = new FileInputStream("E://192.168.2.2"); // 以byte流的方式打开文件
//        int i = hFile.available(); // 得到文件大小
//        byte data[] = new byte[i];
//        hFile.read(data); // 读数据
//        hFile.close();
//        response.setContentType("image/*"); // 设置返回的文件类型
//        OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
//        toClient.write(data); // 输出数据
//        toClient.close();
//        return null;


//            String path = "/uploads/icture/";//换成自己的
//            File folder = new File(path);
//            File temp[] = folder.listFiles();
//            String[] picNames = new String[temp.length];
//            for (int i = 0; i < temp.length; i++) {
//                picNames[i] = temp[i].getName();
//            }
//            request.setAttribute("picNames", picNames);
//            return picNames;
//    }

    protected ArrayList<String> CalculateGeoServlet(HttpServletRequest request,
                                                    HttpServletResponse response, String params) throws ServletException, IOException,
            MalformedURLException {
        ArrayList<String> fileList = new ArrayList<String>();
        fileList = getFiles(params);
        request.setAttribute("fileList", fileList);
//        datas.put("rows", fileList);
        response.sendRedirect("view/capture/ilist.jsp");
        request.getRequestDispatcher("ilist.jsp").forward(request, response);
        return fileList;
    }

    /**
     * 通过递归得到某一路径下所有的目录及其文件
     *
     * @param filePath 文件路径
     * @return
     */
//    @RequestMapping("/z")
//    @ResponseBody
    public static ArrayList<String> getFiles(String filePath) {
        ArrayList<String> fileList = new ArrayList<String>();
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                /*
                 * 递归调用
                 */
                getFiles(file.getAbsolutePath());
                fileList.add(file.getAbsolutePath());
            } else {
                String picPathStr = file.getAbsolutePath();
//              String picPathStr = file.getAbsolutePath().replaceAll("\\\\","//");
                fileList.add(picPathStr);
            }
        }
//        for (String str : fileList) {
//          System.out.println(str);
//
//
//        }


        return fileList;

    }


//    @RequestMapping("/bmpShow")
//    public void bmpShow(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//        String imagePath = Global.getUserfilesBaseDir("D://logo.jpg")+path;
////        String imagePath = Global.getFileList()+path;
//        response.reset();
//        OutputStream output = response.getOutputStream();// 得到输出流
//        if (imagePath.toLowerCase().endsWith(".jpg"))// 使用编码处理文件流的情况：
//        {
//            response.setContentType(JPG);// 设定输出的类型
//            // 得到图片的真实路径
//
//            // 得到图片的文件流
//            InputStream imageIn = new FileInputStream(new File(imagePath));
//            // 得到输入的编码器，将文件流进行jpg格式编码
//            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
//            // 得到编码后的图片对象
//            BufferedImage image = decoder.decodeAsBufferedImage();
//            // 得到输出的编码器
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
//            encoder.encode(image);// 对图片进行输出编码
//            imageIn.close();// 关闭文件流
//        }
//        if (imagePath.toLowerCase().endsWith(".gif"))// 不使用编码处理文件流的情况：
//        {
//            response.setContentType(GIF);
//            ServletContext context  = RequestContextUtils.getWebApplicationContext(request).getServletContext();// 得到背景对象
//            InputStream imageIn = context.getResourceAsStream(imagePath);// 文件流
//            BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
//            BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
//            byte data[] = new byte[4096];// 缓冲字节数
//            int size = 0;
//            size = bis.read(data);
//            while (size != -1) {
//                bos.write(data, 0, size);
//                size = bis.read(data);
//            }
//            bis.close();
//            bos.flush();// 清空输出缓冲流
//            bos.close();
//        }
//        output.close();
//    }


//    @RequestMapping("/iList")
//    @ResponseBody
//    public String icList(){
////        JSONObject datas = new JSONObject();
////        List List = readFTP.getFileList(path);
//
//
////        下面是在一个方法中调用ftp下载的过程
//
////定义本地下载文件名称(文件名自己定义)
//        String filename = PropertiesUtil.get("MERCHANTNO_ABC_BMP")+"."+chargedate+".jpg";
////为Ftp实体赋值（根据自己的ftp实际值赋予，自己定义）
//        Ftp f = new Ftp();
//        f.setIpAddr(PropertiesUtil.get("ABC_BMP_FTP_IP"));
//        f.setUserName(PropertiesUtil.get("ABC_BMP_FTP_USERNAME"));
//        f.setPwd(PropertiesUtil.get("ABC_BMP_FTP_PASSWORD"));
//        f.setPath(PropertiesUtil.get("ABC_BMP_FTP_FILEURL"));
//
//        try {
////判断本地文件是否存在，不存在就往下执行
//            if(!FileUtil.ifExistFile(PropertiesUtil.get("ABC_BMP_FILEURL") + filename)){
//// 从FTP中下载文件 参数 （ftp对象，本地地址，文件名）
//                if (FtpUtil.downfile(f, PropertiesUtil.get("ABC_BMP_FILEURL"), filename)){
////持久化文件
//                    saveEpayFundEXY(PropertiesUtil.get("ABC_BMP_FILEURL")+filename, chargedate);
//// 自动比较
//                    payEBankDao.checkEpayment(chargedate, Constants.DataSource_autoDevice, Constants.BusiType_government,
//                            PropertiesUtil.get("Bankcode"));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return datas.toString();
//    }


    @RequestMapping("/picture")
    public String pList(ModelMap model) {

//        @RequestMapping(value="/picture",method= RequestMethod.POST)
//        @ResponseBody
//        public String uploadifyImg(HttpServletRequest request, HttpServletResponse response,String path){
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            MultipartFile file = multipartRequest.getFile("file");//获取文件对象
//            JSONObject json = new JSONObject();
//            try {
//                if(file != null){
//                    Dimension dimension = FileUtil.getFileSizes(file.getBytes());
//                    int width = (int) dimension.getWidth() ;
//                    int height = (int) dimension.getHeight();
//                    String imageSize = width + "x" + height;
//                    json.put("imageSize", imageSize);
//                    String originalFilename = file.getOriginalFilename(); //文件名称
//                    String suffix = originalFilename.indexOf(".") != -1 ? originalFilename.substring(
//                            originalFilename.lastIndexOf("."), originalFilename.length()) : "";
//                    //图片存放的临时目录
//                    String tempDir = request.getSession().getServletContext().getRealPath("/") + path;
//                    File dir = new File(tempDir);//判断目录是否存在
//                    if (!dir.exists()) {
//                        dir.mkdirs();
//                    }
//                    //uuid + .jpg 组成上传FTP文件名
//                    String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
//                    String img_name = new StringBuffer(uuid).append(suffix).toString();
//                    json.put("img_name", img_name);
//                    String imgPath = new StringBuffer(tempDir).append(img_name).toString();//文件上传的临时目录
//                    file.transferTo(new File(imgPath));//把文件放入临时目录
//                    String img_url = new StringBuffer(path).append(img_name).toString();//前台展示未见的路径
//                    json.put("img_url",img_url);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return json.toString();
//        }}
        return "capture/plist";
    }

//    <span style="white-space:pre">    </span>

    /**
     * 获取图片
     *
     * @param request
     * @return
     */
//    @RequestMapping(value = "/biz/itemmanager/bizitembomimg/getBomImg/{itemCode}", method = RequestMethod.GET)
//    public ResponseEntity<byte[]>
//    getBomImg(HttpServletRequest request, HttpServletResponse response, @PathVariable String itemCode) {
//        HttpHeaders he = new HttpHeaders();
//        byte[] picture =  bizitembomimgService.getImg(itemCode);
//        try {
//            FileType imgType = FileTypeUtil.getType(picture);
//            switch(imgType.name()){
//                case "PNG":
//                    he.setContentType(MediaType.IMAGE_PNG);
//                    break;
//                case "JPG":
//                    he.setContentType(MediaType.IMAGE_JPEG);
//                    break;
//                case "JPEG":
//                    he.setContentType(MediaType.IMAGE_JPEG);
//                    break;
//                case "GIF":
//                    he.setContentType(MediaType.IMAGE_GIF);
//                    break;
//                case "BMP":
//                    he.setContentType(MediaType.valueOf("image/bmp"));
//                    break;
//                default:
//                    he.setContentType(MediaType.IMAGE_JPEG);
//                    break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(picture,he,HttpStatus.OK);
//    }
//    @RequestMapping(value = "/seekExperts")
//    @ResponseBody
//    public String createFolw(HttpServletRequest request,
//                             HttpServletResponse response, Model model) {
////         response.setContentType("image/*");
//        FileInputStream fis = null;
//        OutputStream os = null;
//        //消息提示
//        String message = "";
//        try {
////            File file = this.pictureBox2.Image=Image.FromFile("D://001.jpg");;
//            fis = new FileInputStream("uploads/workticket/");
//            os = response.getOutputStream();
//            int count = 0;
//            byte[] buffer = new byte[1024 * 8];
//            while ((count = fis.read(buffer)) != -1) {
//                os.write(buffer, 0, count);
//                os.flush();
//                message = "图片显示！";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            fis.close();
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return message;
//    }






    @RequestMapping(value = "/toShowPic/{file}/{fileName}/{id}")
    public ModelAndView toShowPic(HttpServletRequest request, HttpServletResponse response, @PathVariable String
            file, @PathVariable String fileName, @PathVariable String id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("id", id);
        mv.addObject("file", file);
        mv.addObject("fileName", fileName);
        mv.setViewName("/capture/qlist");
        return mv;
    }


    @RequestMapping(value = "/showPic/{file}/{fileName}/{id}")
    public void showPic(HttpServletRequest request, HttpServletResponse response, @PathVariable String file, @PathVariable String fileName, @PathVariable String id) {
        String uploadUrl = String.valueOf(PropertyUtils.getPropertyDescriptors("upload_reward_rule_path"));
//        String uploadUrl =
        byte[] b = new byte[20];
        try {
            b = BASE64.getBytes(fileName);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String str = new String(b);
        String[] strArr = str.split("/");

        String fileUrl = uploadUrl + "/" + file + "/" + strArr[0] + "." + strArr[1];
        try {
            File filePath = new File(fileUrl);
            if (filePath.exists()) {
                //读图片
                FileInputStream inputStream = new FileInputStream(filePath);
                int available = inputStream.available();
                byte[] data = new byte[available];
                inputStream.read(data);
                inputStream.close();
                //写图片
                response.setContentType("image/" + strArr[1]);
                response.setCharacterEncoding("UTF-8");
                OutputStream stream = new BufferedOutputStream(response.getOutputStream());
                stream.write(data);
                stream.flush();
                stream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping("/eList")

    public String List(Pager pager) {

//        listFile("/");
//        File file=files.get[0];
//        sout(file.ftpFile.getTimestamp().toString());
        return "/capture/elist";
    }


}