package rpc.hadoop.v1.io;

import java.util.HashMap;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/16 14:09:28
 * @Description:
 */
public class WritableFactories {
    private static final HashMap CLASS_TO_FACTORY = new HashMap();

    private WritableFactories() {}                  // singleton

    /** Define a factory for a class. */
    public static synchronized void setFactory(Class c, WritableFactory factory) {
        CLASS_TO_FACTORY.put(c, factory);
    }

    /** Define a factory for a class. */
    public static synchronized WritableFactory getFactory(Class c) {
        return (WritableFactory)CLASS_TO_FACTORY.get(c);
    }

    /** Create a new instance of a class with a defined factory. */
    public static Writable newInstance(Class c) {
        WritableFactory factory = WritableFactories.getFactory(c);
        if (factory != null) {
            return factory.newInstance();
        } else {
            try {
                return (Writable)c.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
