package com.example.miravereda.API;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

interface ApiService {
    /**
     *
     * @param url  es la ip a la que realizamos la llamada
     * @return un get que nos devuelve la informacion
     */
    @GET
    Call<ResponseBody> getCall(@Url String url);

    /**
     *
     * @param url es la ip  a la que realizamos las llamadas
     * @param data es lo que enviamos.
     * @return post les manamos  la informacion
     */

    @POST
    Call<ResponseBody> postCall(@Url String url, @Body RequestBody data);

    /**
     *
     * @param url la ip a la que realizamos las llamadas
     * @param data la información que le pasamos
     * @return put para actualizar la información
     */

    @PUT
    Call<ResponseBody> putCall(@Url String url,@Body RequestBody data);


    /**
     *
     * @param url a la ip donde hacemos las llamadas
     * @return eliminado
     */
    @DELETE
    Call<ResponseBody> deleteCall(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Call<ResponseBody> deleteWithBodyCall(@Url String url, @Body RequestBody data);
}
