package company.rick.wirlessmanager;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.media.ToneGenerator;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.BoringLayout;
import android.util.Log;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    Context context;
    ConnectivityManager connectivityManager;
    CellularManage cellularManage;
    ToggleButton wifi_button;
    ToggleButton bt_button;
    ToggleButton cellular_button;
    ToggleButton all_button;

    BTManage btManage;
    WifiManage wifiManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        btManage = new BTManage(context);
        wifiManage = new  WifiManage(context);
        cellularManage = new CellularManage(context);

        wifi_button = (ToggleButton)findViewById(R.id.toggleButton);
        wifi_button.setChecked(wifiManage.WifiIsEnable());
        wifi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiManage.SetWifiPower(wifi_button.isChecked());
                RefreshCheckStatus();
            }
        });

        bt_button = (ToggleButton)findViewById(R.id.toggleButton2);
        bt_button.setChecked(btManage.BTIsEnable());
        bt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btManage.SetBTPower(bt_button.isChecked());
                RefreshCheckStatus();
            }
        });

        cellular_button = (ToggleButton)findViewById(R.id.toggleButton3);
        cellular_button.setChecked(cellularManage.GetCellularPower());
        cellular_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellularManage.SetCellularPower(cellular_button.isChecked());
                RefreshCheckStatus();
            }
        });

        all_button = (ToggleButton)findViewById(R.id.toggleButton4);
        all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = all_button.isChecked();
                wifi_button.setChecked(isChecked);
                cellular_button.setChecked(isChecked);
                bt_button.setChecked(isChecked);
                if(isChecked)
                {
                    if(!btManage.GetBTPower())
                        btManage.SetBTPower(true);
                    if(!cellularManage.GetCellularPower())
                        cellularManage.SetCellularPower(true);
                    if(!wifiManage.GetWifiPower())
                        wifiManage.SetWifiPower(true);
                }
                else
                {
                    if(btManage.GetBTPower())
                        btManage.SetBTPower(false);
                    if(cellularManage.GetCellularPower())
                        cellularManage.SetCellularPower(false);
                    if(wifiManage.GetWifiPower())
                        wifiManage.SetWifiPower(false);
                }
            }
        });

        if(wifi_button.isChecked() && bt_button.isChecked() && cellular_button.isChecked())
            all_button.setChecked(true);
        else
            all_button.setChecked(false);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.all);

        imageView = (ImageView)findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.wifi);

        imageView = (ImageView)findViewById(R.id.imageView3);
        imageView.setImageResource(R.drawable.bluetooth);

        imageView = (ImageView)findViewById(R.id.imageView4);
        imageView.setImageResource(R.drawable.data);
    }

    void RefreshCheckStatus()
    {
        if(wifi_button.isChecked() && cellular_button.isChecked() && bt_button.isChecked())
            all_button.setChecked(true);
        else
            all_button.setChecked(false);
    }
}
