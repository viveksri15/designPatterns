package in.viveksrivastava.designPatterns.creationalPatterns;

import java.io.Serializable;

/**
 * Created by vivek on 13-06-2014.
 */
public class Singleton implements Serializable {
    private static Singleton singleton = null;

    private Singleton() {

    }

    public static Singleton getInstance() {

        //Lazy instantiation using double locking mechanism.
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    //to ensure de-serialization does not create two different objects
    protected Object readResolve() {
        return getInstance();
    }

    //to ensure multiple classLoaders do not create multiple instances
    //this method can be used instead of Class.forName
    private static Class getClass(String className) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = Singleton.class.getClassLoader();
        }
        return (classLoader.loadClass(className));
    }

    //To prevent cloning

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton cannot be cloned");
    }
}