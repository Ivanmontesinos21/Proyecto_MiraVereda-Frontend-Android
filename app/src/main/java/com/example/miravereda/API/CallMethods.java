package com.example.miravereda.API;

import com.example.miravereda.base.Parameters;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class CallMethods <T>{


    private Retrofit retrofit=new Retrofit.Builder().baseUrl(Parameters.URL).build();

    private ApiService service=retrofit.create(ApiService.class);

    private static CallMethods callMethods;


    public static CallMethods getCallMethods(){
        if (callMethods==null)
            callMethods=new CallMethods();
        return callMethods;
    }

    /**
     *
     * @param url la ip a la que realizamos las llamadas
     * @return un string
     */
    public String get(String url){
        Call<ResponseBody> call= service.getCall(url);
        try {
            return call.execute().body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param url la ip a la que lo mandamos
     * @param data la información que enviamos
     * @return un string
     */

    public String post(String url, RequestBody data){
        Call<ResponseBody> call= service.postCall(url,data);
        try {
            return call.execute().body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param url la ip a  la que se lo mandamos
     * @param data la informacion que actualizamos  envia
     * @return una respuesta de string
     */

    public String put(String url,RequestBody data){
        Call<ResponseBody> call=service.putCall(url,data);
        try {
            return call.execute().body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param url la ip  a la que hacemos las llamadas
     * @param data la info que utilizamos
     * Como su nombre indica sin return.
     */

    public void putWithoutReturn(String url,RequestBody data){
        Call<ResponseBody> call=service.putCall(url,data);
        try {
            call.execute();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param url la ip donde hacemos las llamadas
     * @return una respuesta en formato string
     */
    public String delete(String url){
        Call<ResponseBody> call=service.deleteCall(url);
        try {
            return call.execute().body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }

    public String deleteWithBody(String url, RequestBody data){
        Call<ResponseBody> call=service.deleteWithBodyCall(url, data);
        try {
            return call.execute().body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }
}
