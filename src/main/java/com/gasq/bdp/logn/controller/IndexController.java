package com.gasq.bdp.logn.controller;

import java.util.HashMap;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gasq.bdp.logn.component.ActiveManager;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.service.TSysMenuService;
import com.gasq.bdp.logn.service.TSysTimerJobconfigService;
import com.gasq.bdp.logn.service.TSysUserService;
import com.gasq.bdp.logn.utils.ActiveMQUtil;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.UserthreadLocal;

@Controller
public class IndexController {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	TSysMenuService menuService;
	@Autowired
	TSysUserService userService;
	@Autowired
    TSysTimerJobconfigService jobconfigService;
	@Autowired ActiveManager activeManager;
	@Autowired EmailManager emailService;
	// 错误信息
	Map<String, Object> paramMap = new HashMap<String, Object>();

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String homepage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		model.addAttribute("content", request.getContextPath());
		return "login";
	}

	@RequestMapping("/gotimmer")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String geTimmer(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "cron_timmer";
	}
	
	@RequestMapping("/goRole")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goRole(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "role";
	}
	
	@RequestMapping("/goUser")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goUser(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "systemuser";
	}
	
	@RequestMapping("/goMenu")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goMenu(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "menu";
	}

	@RequestMapping("/goworkflow")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goworkflow(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
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
			return "login";
		}
		String username = user.getUsername();
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
			SystemUser systemUser = userService.queryFullUser(user);
			request.getSession().setAttribute("user", systemUser);
			mmap.addAttribute("user", systemUser);
			String mess = "用户："+systemUser.getUser().getNickname()+" 在"+DateUtil.getAllCurrentDate()+"登录成功!";
			SecurityUtils.getSubject().getSession().setAttribute("user", systemUser);
			logger.info(mess);
//			emailService.sendSimpleEmail(InitProperties.EMAIL_SENDER, InitProperties.EMAIL_TARGET, "痘卫士-登录信息",mess);
			activeManager.sendBack(ActiveMQUtil.getTopicDestination(systemUser.getUser().getCompanyid()+InitProperties.Moniter_USER), mess);
			return "redirect:/homepage";
		} else {
			token.clear();
			return "redirect:/index";
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
	@RequestMapping(value = "/logout")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		UserthreadLocal.remove();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
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
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String getDefaultMap(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		return "map_areadata";
	}

	@RequestMapping(value = "/goelastic")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goelastic(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "elastic";
	}

	@RequestMapping(value = "/shell")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String shell(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "shell";
	}

	@RequestMapping(value = "/hive")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String hive(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "hive";
	}

	@RequestMapping(value = "/sqoop")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String sqoop(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "sqoop";
	}

	@RequestMapping(value = "/sftp")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String sfpt(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "sftp";
	}

	@RequestMapping(value = "/sqlQuery")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String sqlQuery(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "sqlQuery";
	}

	@RequestMapping(value = "/sql")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String sql(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "sql";
	}

	@RequestMapping(value = "/sqlInsertOrUpdate")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String sqlInsertOrUpdate(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "sqlInsertOrUpdate";
	}

	@RequestMapping(value = "/db")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String db(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "database";
	}

	@RequestMapping(value = "/godata")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String godata(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "data";
	}

	@RequestMapping("/goDataExport")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goDataExport(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "data_export";
	}
	
	@RequestMapping("/goDataImport")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goDataImport(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "data_import";
	}
	
	@RequestMapping(value = "/queryJobList",method=RequestMethod.GET)
	public String queryMenus(int wfid,Model map,HttpServletRequest request,RedirectAttributes attr) {
		map.addAttribute("path", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
		map.addAttribute("content", request.getContextPath());
		Map<String, Object> result = jobconfigService.queryJobViewList(wfid);
		map.addAttribute("viewdata", CommonUtils.BeanToJSON(result));
		map.addAttribute("wfid", wfid);
		return "flow";
	 }
	
	@RequestMapping("/goMongoDBCfg")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goMongoDBCfg(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "mongodatabase";
	}
	
	@RequestMapping("/goMongoQuery")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goMongoQuery(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "mongoQuery";
	}
	
	@RequestMapping("/goCustomerConsumptonAmountMaintenance")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN, RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION, RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goCustomerConsumptonAmountMaintenance(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "customerConsumptonAmountMaintenance";
	}
	
	@RequestMapping("/goCount")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goCount(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "countConSumptionInfo";
	}
	
	@RequestMapping("/goEmployeeTreatCount")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goEmployeeTreatCount(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "countEmployeeTreat";
	}
	
	@RequestMapping("/goScoreExchange")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goScoreExchange(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "score_exchange";
	}
	
	@RequestMapping("/goWechatUser")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goWechatUser(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "wechat_user";
	}
	
	@RequestMapping("/goProjects")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goProjects(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "projects";
	}
	@RequestMapping("/goPermission")
	@RequiresRoles(value = { RoleSign.SADMIN})
	public String goPermission(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "permission";
	}
	@RequestMapping("/customerSubscribe")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String customerSubscribe(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "customerSubscribe";
	}
	
	@RequestMapping("/goInventory")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goInventory(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "inventory";
	}
	
	@RequestMapping("/goInventoryLog")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goInventoryLog(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "inventorylog";
	}
	
	@RequestMapping("/goSubscribeLog")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST,RoleSign.H_ADMIN }, logical = Logical.OR)
	public String goSubscribeLog(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "subscribelog";
	}
	
	@RequestMapping("/goVipCustomer")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String goVipCustomer(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "vipcustomer";
	}
	
	@RequestMapping("/vipcQueryConsumpton")
	@RequiresRoles(value = { RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY, RoleSign.Test,RoleSign.Q_RECEPTIONIST }, logical = Logical.OR)
	public String vipcQueryConsumpton(HttpServletRequest request, ModelMap mmap, RedirectAttributes attr) {
		mmap.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		mmap.addAttribute("content", request.getContextPath());
		return "vipcustomerquery";
	}
}
