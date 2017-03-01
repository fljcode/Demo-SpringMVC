package cn.jxufe.web.common.authority;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义realm可以在这里实现权限的判断依据
 * 例如当访问被@RequiresPermissions注解方法时
 * 会执行MyRealm.doGetAuthorizationInfo进行授权，即相当于checkPermission
 * @author fanlingjie
 *
 */
public class MyRealm extends AuthorizingRealm {
	/**
	 * 添加自定义权限
	 * subject.checkPermission调用
	 * 断点调试看进哪个
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username =(String) super.getAvailablePrincipal(principals);
		System.out.println(">>>>>>>>>>>>>>>执行MyRealm doGetAuthorizationInfo 进入权限用户的名称：    " + username);
		// TODO Auto-generated method stub
		Set<String> permissions = new HashSet<String>();
		//手动添加，暂未数据库实现，正常这里注入一个服务，用于通过用户名字获取用户相应的权限字符标识
		permissions.add("fan:first");
		permissions.add("fan:second");
		permissions.add("fan:third");
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//基于Permission的权限信息
		if(permissions != null)
			simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
		
	}
	//subject.login调用
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		System.out.println(">>>>>>>>>>>>>>>执行MyRealm doGetAuthenticationInfo 进入权限用户的名称：    " + (String)authcToken.getUsername());
		//用户名，密码，这里如果拦截器中login那password为空，这里也应该为空，那里不为空，这里也要有
		return new SimpleAuthenticationInfo(authcToken.getUsername(), authcToken.getPassword(), getName());
		
		
	}

}
