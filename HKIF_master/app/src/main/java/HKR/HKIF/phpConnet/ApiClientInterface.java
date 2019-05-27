package HKR.HKIF.phpConnet;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiClientInterface {
    @FormUrlEncoded
    @POST("save.php")
    Call<PersonPHP> savePerson(
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone_number") String phone_number
    );
}
