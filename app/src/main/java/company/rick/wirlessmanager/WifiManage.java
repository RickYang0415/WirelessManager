package company.rick.wirlessmanager;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Created by rick.yang on 2016/5/9.
 */
public class WifiManage {
    WifiManager wifiManager;
    public  WifiManage(Context context)
    {
        wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
    }

    boolean GetWifiPower()
    {
        int state = wifiManager.getWifiState();
        if(state == WifiManager.WIFI_STATE_ENABLED || state == WifiManager.WIFI_STATE_ENABLED)
            return true;
        else
            return false;
    }
    void SetWifiPower(boolean state)
    {
        wifiManager.setWifiEnabled(state);
    }

    public boolean WifiIsEnable()
    {
        return GetWifiPower();
    }
}
