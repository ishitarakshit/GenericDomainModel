package com.concepts.domain.support;

import java.util.Collection;


/**
 * This class provides simple assertion framework. The primary use for this is intended to be in guard code to protect
 * the class invariants. Assertions should be used in places where it makes no sense to proceed if the assertion fails
 * and no default value can be used instead. Ideally {@link Assert} must be used to validate arguments and catch them as
 * early as possible.
 * <p>
 * If the passed argument is invalid then an {@link IllegalArgumentException} is thrown.
 * 
 * @author ishitarakshit
 * @version 1.0, 04/01/2010
 * @since 1.5
 */
public final class Assert {

    private Assert() {
    }

    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the argument must be null");
    }

    public static void isNull(Object object, String message) {
        if (object != null) { throw new IllegalArgumentException(message); }
    }

    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument must not be null");
    }

    public static void notNull(Object object, String message) {
        if (object == null) { throw new IllegalArgumentException(message); }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) { throw new IllegalArgumentException(message); }
    }

    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "[Assertion failed] - this collection must contain at least 1 element");
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) { throw new IllegalArgumentException(message); }
    }

    public static void isInstanceOf(Class<?> type, Object object) {
        isInstanceOf(type, object, "");
    }

    public static void isInstanceOf(Class<?> type, Object object, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(object)) {
            if (message == null || "".equals(message)) {
                message = "[Assertion failed] - Object of class "
                        + (object != null ? object.getClass().getName() : "null") + " must be an instance of " + type;
            }
            throw new IllegalArgumentException(message);
        }
    }

}
