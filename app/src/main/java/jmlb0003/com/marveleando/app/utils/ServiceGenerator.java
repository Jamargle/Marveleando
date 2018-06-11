package jmlb0003.com.marveleando.app.utils;

import jmlb0003.com.marveleando.BuildConfig;
import jmlb0003.com.marveleando.data.network.authentication.AuthenticatorInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(final Class<S> serviceClass) {
        final OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthenticatorInterceptor())
                .build();

        final Retrofit retrofit = retrofitBuilder
                .client(httpClient)
                .build();
        return retrofit.create(serviceClass);
    }

}
