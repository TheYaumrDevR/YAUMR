package de.ethasia.yaumr.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reflections.Reflections;

/**
 * An instance of this class can be used to register implementations to interfaces
 * and resolve the implementations at a later time in different places where
 * they are needed.
 * 
 * @author Drawig
 */
public class ClassInstanceContainer {
    
    //<editor-fold defaultstate="collapsed" desc="Private Fields">
    
    private HashMap<Class, Class> registeredInstances;
    private HashMap<Class, Object> singletonInstances;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public ClassInstanceContainer() {
        registeredInstances = new HashMap<Class, Class>();
        singletonInstances = new HashMap<Class, Object>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public <TParent> void registerImplementation(Class<TParent> parentClass, Class<? extends TParent> childClass) {
        if (!registeredInstances.containsKey(parentClass)) {
            registeredInstances.put(parentClass, childClass);
        }
    }
    
    public <TParent> void removeRegisteredImplementation(Class<TParent> parentClass) {
        if (registeredInstances.containsKey(parentClass)) {
            registeredInstances.remove(parentClass);
        }      
    }
    
    public <TParent> TParent getImplementationInstance(Class<TParent> parentClass) {
        Class<? extends TParent> childClass = registeredInstances.get(parentClass);
        
        try {
            if (null != childClass) {
                Constructor<? extends TParent> ctor = childClass.getConstructor();
                int publicModifier = Modifier.PUBLIC;
                
                if ((ctor.getModifiers() & publicModifier) == publicModifier) {
                    return (TParent)ctor.newInstance();    
                }          
            }
        } catch (InstantiationException instEx) {
            throw new IllegalArgumentException("Could not instantiate " + parentClass.getName() + ". Please make sure that an instance of this class can be created using a default constructor.");
        } catch (InvocationTargetException invocEx) {
            throw new IllegalArgumentException("Could not instantiate " + parentClass.getName() + ". Please make sure that its default constructor does not throw exceptions.");
        } catch (NoSuchMethodException noDefCtorEx) {
            throw new IllegalArgumentException("Could not instantiate " + parentClass.getName() + ". Please make sure that a default contructor exists and that the class to instantiate is not embedded into another class.");
        } catch (IllegalAccessException illAccessEx) {
            // Should not happen. There is an access check.
        }
                
        return null;
    }
    
    public <TParent, TInstance extends TParent> void registerSingletonInstance(Class<TParent> parentClass, TInstance singletonInstance) {
        if (!singletonInstances.containsKey(parentClass)) {
            singletonInstances.put(parentClass, singletonInstance);
        }        
    }
    
    public <TParent> void removeSingletonInstance(Class<TParent> parentClass) {
        singletonInstances.remove(parentClass);     
    }    
    
    public <TParent> TParent getSingletonInstance(Class<TParent> parentClass) {
        return (TParent)singletonInstances.get(parentClass);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    void performAutoWiring() {
        Reflections rootPackage = new Reflections("de.ethasia.yaumr");
        Set<Class<?>> classesAnnotatedWithWiring = rootPackage.getTypesAnnotatedWith(AutowiringClass.class);
        
        for (Class<?> autoWiringClass : classesAnnotatedWithWiring) {
            Method[] methods = autoWiringClass.getDeclaredMethods();
            
            for (Method method : methods) {
                if (method.isAnnotationPresent(AutowiringMethod.class)) {
                    if ((method.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC) {
                        if (method.getParameterCount() == 0) {
                            try {
                                Constructor<?> ctor = autoWiringClass.getConstructor();
                                int publicModifier = Modifier.PUBLIC;
                
                                if ((ctor.getModifiers() & publicModifier) == publicModifier) {
                                    method.invoke(ctor.newInstance()); 
                                }
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                Logger.getLogger(ClassInstanceContainer.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoSuchMethodException ex) {
                                Logger.getLogger(ClassInstanceContainer.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SecurityException ex) {
                                Logger.getLogger(ClassInstanceContainer.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InstantiationException ex) {
                                Logger.getLogger(ClassInstanceContainer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
    }
    
    //</editor-fold>    
}
