package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gasq.bdp.logn.component.ActiveManager;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.TCustomerImages;
import com.gasq.bdp.logn.model.TSysUserExt;
import com.gasq.bdp.logn.provider.EnableDetail;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.service.TCustomerImagesService;
import com.gasq.bdp.logn.service.TSysMenuService;
import com.gasq.bdp.logn.service.TSysUserService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.UserthreadLocal;

import io.swagger.annotations.Api;

@Controller
@Api(value="跳转controller",tags={"系统页面跳转"})
public class IndexController {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TSysMenuService menuService;
	@Autowired TSysUserService userService;
	@Autowired ActiveManager activeManager;
	@Autowired EmailManager emailService;
	@Autowired TCustomerImagesService customerImagesService;
	@Autowired CommonService commonService;
	
	@Value("${shiro.cas.login}")
	private String loginUrl;
	
	@Value("${shiro.cas.logout}")
	private String logoutUrl;
	
	// 错误信息
	Map<String, Object> paramMap = new HashMap<String, Object>();

	/**
	 * 首页
	 * @return
	 */
	@Ilogger(value="进入首页！",flag=EnableDetail.CLOSE)
	@RequestMapping("/index")
	public String homepage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		model.addAttribute("content", request.getContextPath());
		return "redirect:/homepage";
	}
	
	@Ilogger(value="进入角色管理界面！",flag=EnableDetail.CLOSE)
	@RequestMapping("/goRole")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goRole(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "role";
	}
	@Ilogger(value="进入用户管理界面！",flag=EnableDetail.CLOSE)
	@RequestMapping("/goUser")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goUser(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "systemuser";
	}
	@Ilogger(value="进入菜单管理界面！",flag=EnableDetail.CLOSE)
	@RequestMapping("/goMenu")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goMenu(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "menu";
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/homepage")
	public String homepage(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()) {
			mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
			mmap.addAttribute("content", request.getContextPath());
			Object sessionuser = SecurityUtils.getSubject().getSession().getAttribute("user");
			SystemUser systemUser = null;
			if(sessionuser==null) {
				systemUser = commonService.getCurrentUserInfo();
				if(systemUser==null) {
					logger.info("对用户[" + currentUser.toString() + "]进行登录验证失败！");
					return loginUrl;
				}
				request.getSession().setAttribute("user", systemUser);
			}else {
				systemUser = (SystemUser) sessionuser;
			}
			String mess = "用户："+systemUser.getUser().getNickname()+" 在"+DateUtil.getAllCurrentDate()+"登录成功!";
			SecurityUtils.getSubject().getSession().setAttribute("user", systemUser);
			logger.info(mess);
//			if(CommonUtils.hasNoAnyRoles(RoleSign.SADMIN)){
//				activeManager.sendBack(ActiveMQUtil.getTopicDestination(systemUser.getUser().getCompanyid()+InitProperties.Moniter_USER), mess);
//			}
			return "projects";
		}
		return logoutUrl;
	}
	
	/**
	 * 用户登出
	 * 
	 * @param session
	 * @return
	 */
	@Ilogger(value="安全退出",flag=EnableDetail.CLOSE)
	@RequestMapping(value = "/logout")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		SecurityUtils.getSubject().logout();
		UserthreadLocal.remove();
		return "redirect:/index";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		logger.info("------没有权限-------");
		return "403";
	}

	/**
	 * 加载菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryMenus")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String getDefaultMap(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		return "map_areadata";
	}
	
	@Ilogger(value="进入权限管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goPermission")
	@RequiresRoles(value = { RoleSign.SADMIN})
	public String goPermission(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "permission";
	}
	
	@Ilogger(value="进入公司管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goCompany")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goCompany(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "company";
	}

	@Ilogger(value="进入就诊图片预览界面",flag=EnableDetail.CLOSE)
	@GetMapping("/goViewImages")
	public String goViewImages(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr,Integer orderId,String customerPhone) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		TCustomerImages bean = new TCustomerImages();
		bean.setOrderId(orderId);
		bean.setCustomerPhone(customerPhone);
		List<TCustomerImages> list = customerImagesService.selectByExample(bean);
		mmap.addAttribute("images", list);
		return "viewimages";
	}
	@Ilogger(value="进入就诊图片预览界面",flag=EnableDetail.CLOSE)
	@GetMapping("/goUserImage")
	public String goUserImage(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr,Integer id) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		TSysUserExt userExt = userService.getSysUserExtInfo(id.longValue());
		mmap.addAttribute("image", userExt.getImagePath());
		return "viewimages";
	}
	
	@Ilogger(value="进入缓存管理",flag=EnableDetail.CLOSE)
	@RequestMapping("/goCacheOption")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goCacheOption(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "cacheoption";
	}
	
	@Ilogger(value="进入缓存管理",flag=EnableDetail.CLOSE)
	@RequestMapping("/goProject")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goProject(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "projects";
	}
}
