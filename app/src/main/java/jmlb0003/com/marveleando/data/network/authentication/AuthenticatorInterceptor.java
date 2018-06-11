package jmlb0003.com.marveleando.data.network.authentication;

import java.io.IOException;

import jmlb0003.com.marveleando.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class AuthenticatorInterceptor implements Interceptor {

    private static final String QUERY_TIMESTAMP = "ts";
    private static final String QUERY_API_KEY = "apikey";
    private static final String QUERY_HASH = "hash";

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final String timeStamp = String.valueOf(System.currentTimeMillis());
        final String hash = HashHelper.generate(
                timeStamp,
                BuildConfig.MARVEL_API_PRIVATE_KEY,
                BuildConfig.MARVEL_API_PUBLIC_KEY);

        final Request currentRequest = chain.request();

        final HttpUrl url = currentRequest.url().newBuilder()
                .addQueryParameter(QUERY_TIMESTAMP, timeStamp)
                .addQueryParameter(QUERY_API_KEY, BuildConfig.MARVEL_API_PUBLIC_KEY)
                .addQueryParameter(QUERY_HASH, hash).build();

        final Request newRequest = currentRequest.newBuilder().url(url).build();

        return chain.proceed(newRequest);
    }

}
