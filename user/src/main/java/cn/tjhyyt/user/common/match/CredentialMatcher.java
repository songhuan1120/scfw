package cn.tjhyyt.user.common.match;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

@Slf4j
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        try{
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            String password = new String(usernamePasswordToken.getPassword());
            String dbPassword = (String) info.getCredentials();
            if(StringUtils.isEmpty(password) || StringUtils.isEmpty(dbPassword)){
                return false;
            }
            return this.equals(password, dbPassword);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }
}
