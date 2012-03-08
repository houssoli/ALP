package com.lohika.alp.utils.datagen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author "Anton Smorodsky"
 * Annotation that will allow automatically generate data for tests
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Datagen {

    /**
     * Length of generated string.
     *
     * @return the int
     */
    public int length() default 10;

    /**
     * Chars used for String generation.
     *
     * @return the string
     */
    public String charset() default "qwerty";

    /**
     * Enum that represents supported types of generation. (e.g. DataType.mail will generate not just String but "RandomString@domain.com" )
     *
     * @return the data type
     */
    public DataType type() default DataType.string;
}
