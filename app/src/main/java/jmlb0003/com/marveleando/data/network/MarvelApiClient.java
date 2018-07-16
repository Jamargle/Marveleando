package jmlb0003.com.marveleando.data.network;

import jmlb0003.com.marveleando.data.network.apicontract.MarvelApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApiClient {

    String KEY_NAME = "nameStartsWith";
    String KEY_LIMIT = "limit";
    String KEY_OFFSET = "offset";

    /**
     * Method to fetch a list of characters contained in a {@link MarvelApiResponse} object.
     *
     * @param searchText The service call will return all the characters whose name begings for
     *                   the {@link String} value of this param
     * @param limit      Maximum number of characters that will be returned by the service. The
     *                   service will throw an error if this value is more than 100.
     * @param offset     The number of characters matching the searchText to be skipped.
     * @return {@link Call} object with the response
     */
    @GET("v1/public/characters")
    Call<MarvelApiResponse> getListOfCharacters(
            @Query(KEY_NAME) String searchText,
            @Query(KEY_LIMIT) int limit,
            @Query(KEY_OFFSET) int offset);

}
