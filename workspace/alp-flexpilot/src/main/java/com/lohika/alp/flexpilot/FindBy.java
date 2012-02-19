//Copyright 2011-2012 Lohika .  This file is part of ALP.
//
//    ALP is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    ALP is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with ALP.  If not, see <http://www.gnu.org/licenses/>.
package com.lohika.alp.flexpilot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * The Interface FindBy.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FindBy {
  
  /**
   * How.
   *
   * @return the how
   */
  How how() default How.ID;

  /**
   * Using.
   *
   * @return the string
   */
  String using() default "";
  
  /**
   * Id.
   *
   * @return the string
   */
  String id() default "";

  /**
   * Name.
   *
   * @return the string
   */
  String name() default "";

  /**
   * Link text.
   *
   * @return the string
   */
  String linkText() default "";

  /**
   * Chain.
   *
   * @return the string
   */
  String chain() default "";
 
  /**
   * Rootobj.
   *
   * @return the string
   */
  String rootobj() default "";
  
  /**
   * Maxdepth.
   *
   * @return the string
   */
  String maxdepth() default "";
}