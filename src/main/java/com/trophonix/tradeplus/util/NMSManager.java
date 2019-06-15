package com.trophonix.tradeplus.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Created by PhilipsNostrum
 * 
 * Cleaned up & useNewVersion-added by Gecolay
 * 
**/

public class NMSManager {
	
    public static final Map<Class<?>, Class<?>> CORRESPONDING_TYPES = new HashMap<Class<?>, Class<?>>();
    
    public static Class<?> getPrimitiveType(Class<?> Class) { return CORRESPONDING_TYPES.containsKey(Class) ? CORRESPONDING_TYPES.get(Class) : Class; }
    
    public static Class<?>[] toPrimitiveTypeArray(Class<?>[] Classes) {
        int L = Classes != null ? Classes.length : 0;
        Class<?>[] T = new Class<?>[L];
        for(int i = 0; i < L; i++) T[i] = getPrimitiveType(Classes[i]);
        return T;
    }
    
    public static boolean equalsTypeArray(Class<?>[] Value1, Class<?>[] Value2) {
        if(Value1.length != Value2.length) return false;
        for(int i = 0; i < Value1.length; i++) if(!Value1[i].equals(Value2[i]) && !Value1[i].isAssignableFrom(Value2[i])) return false;
        return true;
    }
    
    public static boolean classListEqual(Class<?>[] Value1, Class<?>[] Value2) {
        if(Value1.length != Value2.length) return false;
        for(int i = 0; i < Value1.length; i++) if(Value1[i] != Value2[i]) return false;
        return true;
    }
    
    public static String getVersion() {
        String V = Bukkit.getServer().getClass().getPackage().getName();
        return V.substring(V.lastIndexOf('.') + 1) + ".";
    }
    
    public static boolean useNewVersion() {
    	try {
    		Class.forName("net.minecraft.server." + getVersion() + "ContainerAccess");
    		return true;
    	} catch (Exception e) { return false; }
    }
    
    public static Field getField(Class<?> Class, String Field) {
        try {
            Field F = Class.getDeclaredField(Field);
            F.setAccessible(true);
            return F;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Class<?> getNMSClass(String ClassName) {
        Class<?> C = null;
        try { return Class.forName("net.minecraft.server." + getVersion() + ClassName); } catch (Exception e) { e.printStackTrace(); }
        return C;
    }
    
    public static Method getMethod(Class<?> Class, String ClassName, Class<?>... Parameters) {
        for(Method M : Class.getMethods()) if(M.getName().equals(ClassName) && (Parameters.length == 0 || classListEqual(Parameters, M.getParameterTypes()))) {
        	M.setAccessible(true);
        	return M;
        }
        return null;
    }
    
    public static Method getMethod(String MethodName, Class<?> Class, Class<?>... Parameters) {
        Class<?>[] T = toPrimitiveTypeArray(Parameters);
        for(Method M : Class.getMethods()) if(M.getName().equals(MethodName) && equalsTypeArray(toPrimitiveTypeArray(M.getParameterTypes()), T)) return M;
        return null;
    }
    
    public static Object getHandle(Object Object) {
        try { return getMethod("getHandle", Object.getClass()).invoke(Object); }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object getPlayerField(Player Player, String Field) throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Object P = Player.getClass().getMethod("getHandle").invoke(Player);
        return P.getClass().getField(Field).get(P);
    }
    
    public static Object invokeMethod(String MethodName, Object Parameter) {
        try { return getMethod(MethodName, Parameter.getClass()).invoke(Parameter); }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object invokeMethodWithArgs(String MethodName, Object Object, Object... Parameters) {
        try { return getMethod(MethodName, Object.getClass()).invoke(Object, Parameters); }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean set(Object Object, String Field, Object Value) {
        Class<?> C = Object.getClass();
        while(C != null) {
            try {
                Field F = C.getDeclaredField(Field);
                F.setAccessible(true);
                F.set(Object, Value);
                return true;
            } catch (NoSuchFieldException e) { C = C.getSuperclass(); } catch (Exception e) { throw new IllegalStateException(e); }
        }
        return false;
    }
    
}