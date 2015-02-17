package ua.artcode.week2.refl_serial;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MySerial {

    // primitive, String, enum - immutable
    String desc() default "";

    int limit();


}
