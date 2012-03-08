package com.lohika.alp.utils.datagen;

import java.lang.reflect.Field;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 
 * @author "Anton Smorodsky"
 * 
 * Parent bean all DataBeans should extends this one.
 * Feature - implements '@Datagen' annotation.
 *
 */
public class ALPBeanObject {

    private final String mailDomain = "@alpCorp.com";

    public ALPBeanObject() {

	Field[] fields = getClass().getDeclaredFields();
	for (Field field : fields) {
	    Datagen annotation = field.getAnnotation(Datagen.class);
	    if (annotation == null)
		continue;
	    // TODO : Check if field is String
	    // if (!field.getDeclaringClass().isInstance(String.class))
	    // continue;
	    try {
		String str = "";
		switch (annotation.type()) {
		case string:
		    str = RandomStringUtils.random(annotation.length(),
			    annotation.charset());
		    break;
		case mail:
		    str = RandomStringUtils.random(annotation.length(),
			    annotation.charset());
		    str += mailDomain;
		    break;
		default:
		    break;
		}
		field.set(this, str);
	    } catch (IllegalArgumentException e) {
		e.printStackTrace();
	    } catch (IllegalAccessException e) {
		e.printStackTrace();
	    }
	}
    }

}
