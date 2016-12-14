package r12.retrofitrssxmljson.GitHub;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("/users/{username}")
    Call<GitHubUser> getUser(@Path("username") String username);

}