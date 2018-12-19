package com.gasq.bdp.logn.provider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.TSysPermission;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.service.TSysPermissionService;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author princejwg
 * @时间 2018年11月22日下午2:30:24
 * @项目路径 com.gasq.bdp.logn.provider
 * @描述
 */
@Slf4j
public class MyShiroCasRealm extends CasRealm{

    @Value("${shiro.cas}") String casServerUrlPrefix;
    @Value("${shiro.server}") String shiroServerUrlPrefix;
    @Value("${shiro.cas.casFilterUrlPattern}") String casFilterUrlPattern;
    
    @Autowired CommonService commonService;
    @Autowired TSysPermissionService permissionService;
    
	@Override
	public boolean supports(AuthenticationToken token) {
		return true;
	}
	@PostConstruct
    public void initProperty(){
        setCasServerUrlPrefix(casServerUrlPrefix);
        // 客户端回调地址
        setCasService(shiroServerUrlPrefix + casFilterUrlPattern);
    }
	
    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * @see 经测试：本例中该方法的调用时机为需授权资源被访问时
     * @see 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	log.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection); 
        //到数据库查是否有此对象
        Subject currentUser = SecurityUtils.getSubject();
        Object sessionuser = currentUser.getSession().getAttribute("user");
		SystemUser systemUser = null;
		if(sessionuser==null) {
			systemUser = commonService.getCurrentUserInfo();
			if(systemUser==null) {
				log.info("对用户[" + loginName + "]获取信息失败！");
				return null;
			}
			SecurityUtils.getSubject().getSession().setAttribute("user", systemUser);
		}else {
			systemUser = (SystemUser) sessionuser;
		}
        TSysUser user = systemUser.getUser();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Set<String> setrole = new HashSet<String>();
        setrole.add(user.getGroupName());
        TSysRole role = new TSysRole();
        role.setRoleName(user.getCardId());
        role.setRoleSign(user.getGroupName());
        List<TSysRole> rols = new ArrayList<TSysRole>();
        rols.add(role);
        systemUser.setRole(rols);
        Set<String> setpermission = new HashSet<String>();
        List<TSysPermission> permissions = null;
        if(systemUser.getPromissions()!=null) {
        	permissions = systemUser.getPromissions();
        }else {
        	TSysPermission bean = new TSysPermission();
        	bean.setPermissionSign(user.getGroupName());
        	permissions = permissionService.selectByExample(bean);
        	systemUser.setPromissions(permissions);
        }
        for (TSysPermission permission : permissions) {
        	setpermission.add(permission.getPermissionSign());
        }
        info.setRoles(setrole);
        info.addStringPermissions(setpermission);
        return info;
    }

}