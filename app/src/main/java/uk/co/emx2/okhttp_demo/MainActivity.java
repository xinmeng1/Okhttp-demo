package uk.co.emx2.okhttp_demo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.user_id)
    EditText userId;
    @Bind(R.id.device_mac)
    EditText deviceMac;
    @Bind(R.id.device_name)
    EditText deviceName;
    @Bind(R.id.device_type)
    EditText deviceType;
    @Bind(R.id.device_uuid)
    EditText deviceUuid;
    @Bind(R.id.text_response)
    TextView textResponse;

    String userUUIDStr = null;
    String deviceNameStr = null;
    String deviceTypeStr = null;
    String deviceUUIDStr = null;
    String deviceMacStr = null;
    Handler mHandler = new Handler(Looper.getMainLooper());
    String mResponse  = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Logger.init("OK-HTTP-DEMO");
        //use ok-http to invoke the api
        //Get the device information
        final TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(this.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID uuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = uuid.toString();
        String deviceIdTest1 = deviceId + "_test1";
        deviceUuid.setText(deviceIdTest1);

        WifiInfo wifiInfo = ((WifiManager) this.getSystemService(Context.WIFI_SERVICE))
                .getConnectionInfo();
        deviceMac.setText(wifiInfo.getBSSID());
        deviceType.setText("MOBILE");
        userId.setText("xin.meng");
        deviceName.setText("api-test1");
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    void getStr(){
        this.userUUIDStr = userId.getText().toString();
        this.deviceNameStr = deviceName.getText().toString();
        this.deviceMacStr = deviceMac.getText().toString();
        this.deviceTypeStr = deviceType.getText().toString();
        this.deviceUUIDStr = deviceUuid.getText().toString();
    }

    @OnClick(R.id.register_sync)
    void OnClickRegisterSync(){
        getStr();
        PostApi example = new PostApi();
        String registerJson = example.bowlingRegisterJson(userUUIDStr, deviceNameStr
                , deviceTypeStr, deviceUUIDStr,  deviceMacStr);

        Response response = null;
        try {
            response = example.post(ApiUrl.RegisterUserDevice, registerJson).execute();
            mResponse = String.valueOf(response);
            textResponse.setText(String.valueOf(mResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_register)
    void OnClickRegister(){
        //send request to register.
        getStr();
        PostApi example = new PostApi();
        String registerJson = example.bowlingRegisterJson(userUUIDStr, deviceNameStr
                , deviceTypeStr, deviceUUIDStr,  deviceMacStr);

/*        String response = null;
        try {
            response = example.post(ApiUrl.RegisterUserDevice, registerJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.json(response);*/

        example.post(ApiUrl.RegisterUserDevice, registerJson).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.d(response.toString());
                mResponse = String.valueOf(response);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        textResponse.setText(String.valueOf(mResponse));
                    }
                });
            }
/*
            @Override
            public void onFailure(Request request, IOException e)
            {
            }

            @Override
            public void onResponse(final Response response) throws IOException
            {
                //String htmlStr =  response.body().string();
            }*/
        });

    }
}
