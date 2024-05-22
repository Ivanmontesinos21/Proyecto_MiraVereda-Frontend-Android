package com.example.miravereda.API;


import com.example.miravereda.base.Parameters;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Connector {

    private static Connector connector;
    private static Conversor conversor;
    private static CallMethods callMethodsObject;

    public static Connector getConector(){
        if(connector == null){
            connector = new Connector();
            conversor = Conversor.getConversor();
            callMethodsObject = CallMethods.getCallMethods();
        }
        return connector;
    }

    public <T> List<T> getAsList(Class<T> clazz, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        System.out.println(url);
        String jsonResponse = callMethodsObject.get(url);
        System.out.println(jsonResponse);
        if(jsonResponse != null)
            return conversor.fromJsonList(jsonResponse, clazz);
        return null;
    }


    public <T> T get(Class<T> clazz, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonResponse = callMethodsObject.get(url);
        if(jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public String getString(String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonResponse = callMethodsObject.get(url);
        if(jsonResponse != null)
            return jsonResponse;
        return null;
    }

    public <T, T2> T post(Class<T> clazz, T2 data, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonObject = conversor.toJson(data);
        System.out.println(jsonObject);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.post(url, body);
        if(jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public <T,T2> T put(Class<T> clazz, T2 data, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        String jsonResponse = callMethodsObject.put(url, body);
        if(jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public <T> void put(T data, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonObject = conversor.toJson(data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
        callMethodsObject.putWithoutReturn(url, body);
    }

    public <T> T delete(Class<T> clazz, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonResponse = callMethodsObject.delete(url);
        if(jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }


}
