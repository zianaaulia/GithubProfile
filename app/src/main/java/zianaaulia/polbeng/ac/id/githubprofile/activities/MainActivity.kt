package zianaaulia.polbeng.ac.id.githubprofile.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.request.RequestOptions
import zianaaulia.polbeng.ac.id.githubprofile.GlideApp
import zianaaulia.polbeng.ac.id.githubprofile.R
import zianaaulia.polbeng.ac.id.githubprofile.databinding.ActivityMainBinding
import zianaaulia.polbeng.ac.id.githubprofile.helpers.Config
import zianaaulia.polbeng.ac.id.githubprofile.models.GithubUser
import zianaaulia.polbeng.ac.id.githubprofile.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        mainViewModel.githubUser.observe(this) { user ->
            setUserData(user)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        binding.btnSearchUserLogin.setOnClickListener {
            val userLogin = binding.etSearchUserLogin.text.toString()
            var query = Config.DEFAULT_USER_LOGIN
            if (userLogin.isNotEmpty()) {
                query = userLogin
            }
            mainViewModel.searchUser(query)
        }
    }
    private fun setUserData(githubUser: GithubUser) {
        binding.tvUser.text = githubUser.toString()
        GlideApp.with(applicationContext)
            .load(githubUser.avatarUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .error(R.drawable.ic_baseline_broken_image_24))
            .into(binding.imgUser)
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}