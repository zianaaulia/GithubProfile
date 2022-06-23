package zianaaulia.polbeng.ac.id.githubprofile.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import zianaaulia.polbeng.ac.id.githubprofile.models.GithubUser

interface GithubUserService {
    @GET("users/{login}")
    fun loginUser(
        @Path("login")userLogin: String
    ): Call<GithubUser>
}
