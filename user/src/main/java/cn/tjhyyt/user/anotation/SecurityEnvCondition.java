package cn.tjhyyt.user.anotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
@Configuration
public class SecurityEnvCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if ("1".equals(context.getEnvironment().getProperty("security.env"))) {
            return true;
        }
        return false;
    }
}
