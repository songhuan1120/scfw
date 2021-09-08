package cn.tjhyyt.user.anotation;

import org.springframework.context.annotation.Conditional;

@Conditional(SecurityEnvCondition.class)
public @interface ShiroEnv {
}
