package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gasq.bdp.logn.component.ActiveManager;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.TCustomerImages;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TSysUserExt;
import com.gasq.bdp.logn.provider.EnableDetail;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.CustomerSubscribeService;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.service.TCustomerImagesService;
import com.gasq.bdp.logn.service.TSysMenuService;
import com.gasq.bdp.logn.service.TSysUserService;
import com.gasq.bdp.logn.utils.ActiveMQUtil;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.UserthreadLocal;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

@Controller
public class IndexController {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TSysMenuService menuService;
	@Autowired TSysUserService userService;
	@Autowired ActiveManager activeManager;
	@Autowired EmailManager emailService;
	@Autowired TCustomerImagesService customerImagesService;
	@Autowired CustomerSubscribeService customerSubscribeService;
	// 错误信息
	Map<String, Object> paramMap = new HashMap<String, Object>();

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index")
	public String homepage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		model.addAttribute("content", request.getContextPath());
		return "ltn/login";
	}
	
	@Ilogger(value="添加或更新设备配置信息",flag=EnableDetail.CLOSE)
	@RequestMapping("/gotimmer")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String geTimmer(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "cron_timmer";
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
	@Ilogger(value="进入工作流管理界面！",flag=EnableDetail.CLOSE)
	@RequestMapping("/goworkflow")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goworkflow(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "workflow";
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String index(@Valid TSysUser user, BindingResult bindingResult, ModelMap mmap, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "ltn/login";
		}
		String username = user.getUsername();
		SystemUser systemUser = userService.queryFullUser(user);
		if(systemUser.getUser()==null) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已不可用");
			redirectAttributes.addFlashAttribute("message", "账户已不可用");
			return "redirect:/index";
		}
		boolean rememberMe = ServletRequestUtils.getBooleanParameter(request, "rememberMe", false);
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),CommonUtils.change2MD5(user.getPassword()),rememberMe);
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addFlashAttribute("message", "未知账户");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			redirectAttributes.addFlashAttribute("message", "密码不正确");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			redirectAttributes.addFlashAttribute("message", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			request.getSession().setAttribute("user", systemUser);
			mmap.addAttribute("user", systemUser);
			String mess = "用户："+systemUser.getUser().getNickname()+" 在"+DateUtil.getAllCurrentDate()+"登录成功!";
			SecurityUtils.getSubject().getSession().setAttribute("user", systemUser);
			logger.info(mess);
			if(WorkFlowUtil.hasNoAnyRoles(RoleSign.SADMIN)){
				activeManager.sendBack(ActiveMQUtil.getTopicDestination(systemUser.getUser().getCompanyid()+InitProperties.Moniter_USER), mess);
			}
			return "redirect:/homepage";
		} else {
			token.clear();
			return "redirect:/index";//
		}
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
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "index";
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
	@Ilogger(value="进入客户消费管理界面！",flag=EnableDetail.CLOSE)
	@RequestMapping("/goCustomerConsumptonAmountMaintenance")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goCustomerConsumptonAmountMaintenance(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "customerConsumptonAmountMaintenance";
	}
	@Ilogger(value="进入消费统计界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goCount")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goCount(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "countConSumptionInfo";
	}
	@Ilogger(value="进入员工订单统计界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goEmployeeTreatCount")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goEmployeeTreatCount(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "countEmployeeTreat";
	}
	@Ilogger(value="进入积分兑换界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goScoreExchange")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goScoreExchange(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "score_exchange";
	}
	@Ilogger(value="进入微信用户管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goWechatUser")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goWechatUser(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "wechat_user";
	}
	@Ilogger(value="进入项目管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goProjects")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goProjects(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "projects";
	}
	@Ilogger(value="进入权限管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goPermission")
	@RequiresRoles(value = { RoleSign.SADMIN})
	public String goPermission(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "permission";
	}
	@Ilogger(value="进入客户预约管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/customerSubscribe")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String customerSubscribe(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "customerSubscribe";
	}
	@Ilogger(value="进入商品库存管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goInventory")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goInventory(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "inventory";
	}
	@Ilogger(value="进入商品库存日志管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goInventoryLog")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goInventoryLog(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "inventorylog";
	}
	@Ilogger(value="进入预约日志管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goSubscribeLog")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION,RoleSign.H_ADMIN }, logical = Logical.OR)
	public String goSubscribeLog(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "subscribelog";
	}
	@Ilogger(value="进入会员用户管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goVipCustomer")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION,RoleSign.H_ADMIN,RoleSign.H_OPTION  }, logical = Logical.OR)
	public String goVipCustomer(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "vipcustomer";
	}
	@Ilogger(value="进入会员用户查询管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/vipcQueryConsumpton")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String vipcQueryConsumpton(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "vipcustomerquery";
	}
	@Ilogger(value="进入预约订单统计界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goCountBackEployeeOrder")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test }, logical = Logical.OR)
	public String goCountBackEployeeOrder(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "countBackEmployee";
	}
	@Ilogger(value="进入产品库存统计界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goCountInventory")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR }, logical = Logical.OR)
	public String goCountInventory(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "countInventory";
	}
	@Ilogger(value="进入设备管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goEquipment")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goEquipment(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "equipment";
	}
	@Ilogger(value="进入公司管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goCompany")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goCompany(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "company";
	}
	@Ilogger(value="进入消息管理界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goMessage")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_ADMIN,RoleSign.H_OPTION,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goMessage(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "message";
	}
	@Ilogger(value="进入渠道统计界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goRootInCount")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_ADMIN,RoleSign.H_OPTION }, logical = Logical.OR)
	public String goRootInCount(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "countBusinessAnalysis";
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
	@Ilogger(value="进入【排班管理】界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goWrokforceManagement")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER}, logical = Logical.OR)
	public String goWorkforceManagement(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "workforceManagement";
	}
	@Ilogger(value="进入【排班查询】界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goWrokforceManagementQuery")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Q_OPTION}, logical = Logical.OR)
	public String goWrokforceManagementQuery(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "workforceManagementQuery";
	}
	@Ilogger(value="进入【视图管理】界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goViewManager")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Q_OPTION,RoleSign.H_ADMIN,RoleSign.H_OPTION}, logical = Logical.OR)
	public String goViewManager(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "viewManager";
	}
	@Ilogger(value="进入用户产品日志",flag=EnableDetail.CLOSE)
	@RequestMapping("/customerprojectlog")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goCustomerprojectlog(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "customerprojectlog";
	}
	@Ilogger(value="进入治疗师预约查询界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goTherapistTreatmentTimeQuery")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goTherapistTreatmentTime(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "therapistTreatmentTimeQuery";
	}
	@Ilogger(value="进入项目类型消费统计界面",flag=EnableDetail.CLOSE)
	@RequestMapping("/goProjecttype")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_OPTION }, logical = Logical.OR)
	public String goProjecttype(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "countProjectType";
	}
}
