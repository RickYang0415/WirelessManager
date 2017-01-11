package company.rick.wirlessmanager;

import android.content.Context;
import android.net.ConnectivityManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by rick.yang on 2016/5/9.
 */
public class CellularManage {
    ConnectivityManager conman;
    public  CellularManage(Context context)
    {
        conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    public boolean SetCellularPower(boolean enabled)
    {
        try {
            Class conmanClass = Class.forName(conman.getClass().getName());
            Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
            iConnectivityManagerField.setAccessible(true);
            Object iConnectivityManager = iConnectivityManagerField.get(conman);
            Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
            Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);
            setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
            return true;
        }
        catch (Exception ex) {
        }
        return  false;
    }

    public boolean GetCellularPower()
    {
        Class ownerClass = conman.getClass();
        try {
            Method method = ownerClass.getMethod("getMobileDataEnabled");
            return (boolean)method.invoke(conman);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
}
