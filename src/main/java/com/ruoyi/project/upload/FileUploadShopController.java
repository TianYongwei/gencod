package com.ruoyi.project.upload;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.config.RuoYiConfig;



/***
 * 上传文件
 * 
 * @author gooniee450
 *
 */
@Controller
@RequestMapping(value = "/fileUploadShop")
public class FileUploadShopController {
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadShopController.class);
	
	private String prefix = "uploads";
	// 上传文件会自动绑定到MultipartFile中
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject upload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") MultipartFile file) throws Exception {
		response.setContentType("application/json;charset=utf-8");
		JSONObject obj = new JSONObject();
		// 如果文件不为空，写入上传路径
		if (!file.isEmpty()) {
			// 上传文件路径
			String path = request.getSession().getServletContext()
					.getRealPath("/uploads/");
			File f1 = new File(path);
			if (!f1.exists()) {
				f1.mkdirs();
			}
			// 上传文件名
			String filename = file.getOriginalFilename();
			File filepath = new File(path, filename);
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			String originalFilename = UUID.randomUUID().toString()
					.replaceAll("-", "");
			originalFilename += file.getOriginalFilename().substring(
					file.getOriginalFilename().indexOf("."));
			String savepath = RuoYiConfig.getProfile();// 需要配置虚拟目录

			String[] pathTemp = FolderUtil.getDir(savepath);
			logger.info("文件原名: " + file.getOriginalFilename());
			logger.info("文件名称: " + file.getName());
			logger.info("文件长度: " + file.getSize());
			logger.info("文件类型: " + file.getContentType());
			logger.info("文件新名:" + originalFilename);
			logger.info("文件路径:" + pathTemp[0]);
			logger.info("文件路径:" + pathTemp[1]);

			file.transferTo(new File(pathTemp[0] + originalFilename));
			// 将上传文件保存到一个目标文件当中
			// file.transferTo(new File(path + File.separator + filename));
			System.out.println("---上传地址------" + pathTemp[0] + originalFilename);
			System.out.println("---上传地址------" + RuoYiConfig.getSystemUrlAddress()+pathTemp[1] + originalFilename);

			obj.put("imgPath", pathTemp[1] + originalFilename);
			obj.put("imgHttpPath", RuoYiConfig.getSystemUrlAddress()+pathTemp[1] + originalFilename);
			obj.put("success", "true");
			obj.put("flag", "true");
			return obj;
		}
		return null;
	}
	
	
	// 上传文件会自动绑定到MultipartFile中
		@RequestMapping(value = "/upload1", method = RequestMethod.POST)
		@ResponseBody
		public JSONObject upload1(HttpServletRequest request,
				HttpServletResponse response,
				@RequestParam("file1") MultipartFile file) throws Exception {
			response.setContentType("application/json;charset=utf-8");
			JSONObject obj = new JSONObject();
			// 如果文件不为空，写入上传路径
			if (!file.isEmpty()) {
				// 上传文件路径
				String path = request.getSession().getServletContext()
						.getRealPath("/uploads/");
				File f1 = new File(path);
				if (!f1.exists()) {
					f1.mkdirs();
				}
				// 上传文件名
				String filename = file.getOriginalFilename();
				File filepath = new File(path, filename);
				// 判断路径是否存在，如果不存在就创建一个
				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				String originalFilename = UUID.randomUUID().toString()
						.replaceAll("-", "");
				originalFilename += file.getOriginalFilename().substring(
						file.getOriginalFilename().indexOf("."));
				String savepath = RuoYiConfig.getProfile();// 需要配置虚拟目录

				String[] pathTemp = FolderUtil.getDir(savepath);
				logger.info("文件原名: " + file.getOriginalFilename());
				logger.info("文件名称: " + file.getName());
				logger.info("文件长度: " + file.getSize());
				logger.info("文件类型: " + file.getContentType());
				logger.info("文件新名:" + originalFilename);
				logger.info("文件路径:" + pathTemp[0]);
				logger.info("文件路径:" + pathTemp[1]);

				file.transferTo(new File(pathTemp[0] + originalFilename));
				// 将上传文件保存到一个目标文件当中
				// file.transferTo(new File(path + File.separator + filename));
				System.out.println("---上传地址------" + pathTemp[0] + originalFilename);
				System.out.println("---上传地址------" + RuoYiConfig.getSystemUrlAddress()+pathTemp[1] + originalFilename);

				obj.put("imgPath", pathTemp[1] + originalFilename);
				obj.put("imgHttpPath", RuoYiConfig.getSystemUrlAddress()+pathTemp[1] + originalFilename);
				obj.put("success", "true");
				obj.put("flag", "true");
				return obj;
			}
			return null;
		}

	@RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadImg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String originalFilename = UUID.randomUUID().toString()
				.replaceAll("-", "");
		originalFilename += ".jpg";
		String savepath = RuoYiConfig.getProfile();// 需要配置虚拟目录
		File f = new File(savepath);
		if (!f.exists()) {
			f.mkdirs();
		}
		String[] pathTemp = FolderUtil.getDir(savepath);

		logger.info("文件新名:" + originalFilename);
		logger.info("文件路径:" + pathTemp[0]);
		logger.info("文件路径:" + pathTemp[1]);

		FileWriter out = new FileWriter(
				new File(pathTemp[0] + originalFilename));
		out.write(request.getParameter("content"));
		out.flush();
		// 将上传文件保存到一个目标文件当中
		// file.transferTo(new File(path + File.separator + filename));
		System.out.println("---上传地址------" + pathTemp[0] + originalFilename);

		Map<String, String> map = new HashMap<String, String>();
		map.put("imgPath", pathTemp[1] + originalFilename);
		map.put("success", "true");
		// response.getWriter().print(obj.toString());
		return map;
	}

	// 上传文件会自动绑定到MultipartFile中
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(HttpServletRequest request,
			HttpServletResponse response, String name) throws Exception {
		String savepath = RuoYiConfig.getProfile();// 需要配置虚拟目录
		Map<String, String> map = new HashMap<String, String>();
		try {
			File f = new File(savepath + "/" + name);
			f.delete();
			map.put("rel", "1");
		} catch (Exception e) {
			map.put("rel", "0");
			e.printStackTrace();
		}

		return map;
	}
	
    /**
     * 上传图片
     */
    @GetMapping("/pic/{id}")
    public String avatar1(@PathVariable("id") String id,ModelMap mmap)
    {
    	mmap.put("form_id", id);//前端js
    	return prefix + "/fileupload";
    }

    /**
     * 上传图片 40*40
     */
    @GetMapping("/pic2/{id}")
    public String avatar2(@PathVariable("id") String id,ModelMap mmap)
    {
    	mmap.put("form_id", id);//前端js
    	return prefix + "/fileupload2";
    }
    /**
     * 保存头像
     */
    @PostMapping("/updateAvatar1")
    @ResponseBody
    public String updateAvatar1( @RequestParam("file") MultipartFile file)
    {
    	try
    	{
    		if (!file.isEmpty())
    		{
    			String avatar = FileUploadUtils.upload_dir(RuoYiConfig.getProfile(), file);
    			 return avatar;
    		}
    	}
    	catch (Exception e)
    	{
    		logger.error("修改头像失败！", e);
    	}
    	return "";
    }
}
