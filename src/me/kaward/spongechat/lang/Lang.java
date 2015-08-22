package me.kaward.spongechat.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Lang
{

	public String name() default "Unknown";

	public int id() default 0;

	public int group() default 0;

	public String[]keywords() default {};

}
