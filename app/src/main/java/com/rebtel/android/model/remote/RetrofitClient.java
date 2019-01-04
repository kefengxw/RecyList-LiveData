package com.rebtel.android.model.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rebtel.android.model.data.ExternalDataConfiguration;
import com.rebtel.android.model.data.HomeApplication;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static volatile Retrofit mInstanceRc = null;

    public static synchronized Retrofit getInstanceRc() {
        if (mInstanceRc == null) {
            mInstanceRc = buildRetrofit();
        }
        return mInstanceRc;
    }

    public static void destroyInstanceRc() {
        mInstanceRc = null;
    }

    //Might many remote service to Expansion, check it later
    public static <T> T createService(Class<T> service) {
        return getInstanceRc().create(service);
    }

    private static Retrofit buildRetrofit() {
        Retrofit.Builder rbuilder = new Retrofit.Builder();
        rbuilder.baseUrl(ExternalDataConfiguration.BASE_URL)
                //.client(buildOkHttpClient())
                .callbackExecutor(HomeApplication.getInstanceEx().getNetworkIO())
                .addCallAdapterFactory(buildCallAdapterFactory())
                .addConverterFactory(buildConverterFactory());
        return rbuilder.build();
    }

    private static OkHttpClient buildOkHttpClient() {
        OkHttpClient.Builder obuilder = new OkHttpClient.Builder();
        return obuilder.build();
    }

    private static Converter.Factory buildConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }

    private static CallAdapter.Factory buildCallAdapterFactory() {
        return new LiveDataCallAdapterFactory();
    }
}
