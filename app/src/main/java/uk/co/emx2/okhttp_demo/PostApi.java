package uk.co.emx2.okhttp_demo;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Xin Meng on 14/02/2016.
 */
public class PostApi {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    /**
     * {"userUUID":"xin.meng",
     * "userDevice":{
     * "userUUID":"xin.meng",
     * "deviceName":"home-test-1",
     * "deviceType":"MOBILE",
     * "deviceUUID":"home-test-1",
     * "deviceMAC":"mac"}}:
     * @return
     */



/*    "    {\"userUUID\":\"xin.meng\",\n"+
            "\"userDevice\":{\n"+
            "\"userUUID\":\"xin.meng\",\n"+
            "\"deviceName\":\"home-test-1\",\n"+
            "\"deviceType\":\"MOBILE\",\n"+
            "\"deviceUUID\":\"home-test-1\",\n"+
            "\"deviceMAC\":\"" +
            "mac" +
            "\"}}:"*/


    String bowlingRegisterJson(String userUUID, String deviceName
            , String deviceType, String deviceUUID, String deviceMac){
/*        RequestBody body = new FormEncodingBuilder()
                .add("data", data).add("token", getToken(data))
                .add("system", "sample").build();*/
/*
   return "{'winCondition':'HIGH_SCORE',"
        + "'name':'Bowling',"
        + "'round':4,"
        + "'lastSaved':1367702411696,"
        + "'dateStarted':1367702378785,"
        + "'players':["
        + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
        + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
        + "]}";
* */
        return "    {\"userUUID\":\"" +
                userUUID +
                "\",\n"+
                "         \"userDevice\":{\n"+
                "         \"userUUID\":\"" +
                userUUID +
                "\",\n"+
                "         \"deviceName\":\"" +
                deviceName +
                "\",\n"+
                "         \"deviceType\":\"" +
                deviceType +
                "\",\n"+
                "         \"deviceUUID\":\"" +
                deviceUUID +
                "\",\n"+
                "         \"deviceMAC\":\"\"" +
                deviceMac +
                "\"}}:";

    }
//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//
//        } catch (IOException e) {
//            Logger.e("io exception", e);
//        }
//
//        assert response != null;
//        return response.body().string();
//    }
    Call post(String url, String json){
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return client.newCall(request);
    }
}
