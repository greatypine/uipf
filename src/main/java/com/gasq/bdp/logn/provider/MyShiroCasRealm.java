package com.gasq.bdp.logn.provider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysPermission;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.service.TSysPermissionService;
import com.gasq.bdp.logn.service.TSysRoleService;
import com.gasq.bdp.logn.service.TSysUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by hdwang on 2017/6/20.
 * 安全数据源
 */
@Slf4j
public class MyShiroCasRealm extends CasRealm{

    @Value("${shiro.cas}") String casServerUrlPrefix;
    @Value("${shiro.server}") String shiroServerUrlPrefix;
    @Value("${shiro.cas.casFilterUrlPattern}") String casFilterUrlPattern;
    @Autowired
    private TSysUserService userService; 
    @Autowired
    private TSysRoleService roleService;
    @Autowired
    private TSysPermissionService permissionService;
    @Autowired TCompanyMapper companymapper;

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
        SystemUser su = SystemUserInfo.getSystemUser();
        if(su==null) su = SystemUserInfo.getInstance();
        TSysUser tu = new TSysUser();
        tu.setUsername(loginName);
        tu.setIsvalid(true);
        List<TSysUser> users =userService.selectByExample(tu);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        TSysUser user = null;
        if(users!=null && users.size()>0){
        	user = users.get(0);
        	su.setCompany(companymapper.selectByPrimaryKey(user.getCompanyid()));
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //用户的角色集合
            List<TSysRole> urs = roleService.selectRolesByUserId(user.getId());
            su.setRole(urs);
            Set<String> setrole = new HashSet<String>();
            Set<String> setpermission = new HashSet<String>();
            List<TSysPermission> permissionsall = new ArrayList<TSysPermission>();
            Object[] rids = urs.stream().map(r->r.getId().intValue()).toArray();
            String roleids = StringUtils.join(rids,",");
            user.setRoleids(roleids);
            su.setUser(user);
            for (TSysRole tSysRole : urs) {
            	setrole.add(tSysRole.getRoleSign());
            	List<TSysPermission> permissions = permissionService.selectPermissionsByRoleId(tSysRole.getId());
            	permissionsall.addAll(permissions);
            	for (TSysPermission permission : permissions) {
            		setpermission.add(permission.getPermissionSign());
				}
			}
            su.setPromissions(permissionsall);
            info.setRoles(setrole);
            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
            info.addStringPermissions(setpermission);
            // 或者按下面这样添加
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色    
//            simpleAuthorInfo.addRole("admin");  
            //添加权限  
//            simpleAuthorInfo.addStringPermission("admin:manage");  
//            log.info("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
            SystemUserInfo.setSystemUser(su);
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

}