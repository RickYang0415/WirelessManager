package company.rick.wirlessmanager;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;

/**
 * Created by rick.yang on 2016/5/9.
 */
public class BTManage {
    BluetoothManager bluetoothManager;
    public  BTManage(Context context)
    {
        bluetoothManager = (BluetoothManager)context.getSystemService(Context.BLUETOOTH_SERVICE);
    }

    boolean GetBTPower()
    {
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        if(adapter == null)
            return false;
        else
            return adapter.isEnabled();
    }

    public void SetBTPower(boolean state)
    {
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        if(adapter != null)
        {
            if(state)
                adapter.enable();
            else
                adapter.disable();
        }
    }

    boolean BTIsEnable()
    {
        return this.GetBTPower();
    }
}
