package jiansk.ieg.other;

/**
 * Created by jiansk on 17-7-7.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldInfo {

    String name() default "";

    String comment() default "";
}
