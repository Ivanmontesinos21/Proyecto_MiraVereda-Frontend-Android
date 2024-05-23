package com.example.miravereda.API;


import com.example.miravereda.base.Parameters;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Connector {

    private static Connector connector;
    private static Conversor conversor;
    private static CallMethods callMethodsObject;

    /**
     *
     * El contsructor con toda la informacion del conector
     */
    public static Connector getConector(){
        if(connector == null){
            connector = new Connector();
            conversor = Conversor.getConversor();
            callMethodsObject = CallMethods.getCallMethods();
        }
        return connector;
    }

    /**
     *
     * @param clazz
     * @param path la inforacion que recogemos
     * @return devuelve una lista que a convertido  a lista desde un JSON
     * @param <T>
     */
    public <T> List<T> getAsList(Class<T> clazz, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        System.out.println(url);
        String jsonResponse = callMethodsObject.get(url);
        System.out.println(jsonResponse);
        if(jsonResponse != null)
            return conversor.fromJsonList(jsonResponse, clazz);
        return null;
    }

    /**
     *
     * @param clazz
     * @path la inforacion que recogemos
     * @return devuelve una lista que a convertido  a lista desde un JSON
     * @param <T>
     */

    public <T> T get(Class<T> clazz, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonResponse = callMethodsObject.get(url);
        if(jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    /**
     *
     * @param path la ip que le pasamos en formato String.
     * @return
     */

    public String getString(String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonResponse = callMethodsObject.get(url);
        if(jsonResponse != null)
            return jsonResponse;
        return null;
    }

    /**
     *
     * @param clazz
     * @param data la ip que le peasamos donde hara las llamadas
     * @param path y el patch qye son las opciones
     * @return
     * @param <T>
     * @param <T2>
     */

    public <T, T2> T post(Class<T> clazz, T2 data, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.post(url, body);
        if(jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    /**
     *
     * @param data la ip que le peasamos donde hara las llamadas
     * @param path y el patch qye son las opciones
     * @return
     * @param <T>
     */


    public <T> String postString(T data, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        return callMethodsObject.post(url, body);
    }

    /**
     *
     * @param clazz
     * @param data
     * @param path
     * @return
     * @param <T>
     * @param <T2>
     */

    public <T, T2> List<T> postAsList(Class<T> clazz, T2 data, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.post(url, body);
        if(jsonResponse != null)
            return conversor.fromJsonList(jsonResponse, clazz);
        return null;
    }

    /**
     *
     * @param clazz
     * @param data
     * @param path
     * @return
     * @param <T>
     * @param <T2>
     */

    public <T,T2> T put(Class<T> clazz, T2 data, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.put(url, body);
        if(jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    /**
     *
     * @param data
     * @param path
     * @param <T>
     */

    public <T> void put(T data, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        callMethodsObject.putWithoutReturn(url, body);
    }

    /**
     *
     * @param clazz
     * @param path
     * @return
     * @param <T>
     */

    public <T> T delete(Class<T> clazz, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonResponse = callMethodsObject.delete(url);
        if(jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    /**
     *
     * @param data
     * @param path
     * @return
     * @param <T>
     */

    public <T> String delete2(T data, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        return callMethodsObject.deleteWithBody(url, body);
    }

}
