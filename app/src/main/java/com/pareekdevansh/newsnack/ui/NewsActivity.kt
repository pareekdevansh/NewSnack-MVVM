package com.pareekdevansh.newsnack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.pareekdevansh.newsnack.R
import com.pareekdevansh.newsnack.databinding.ActivityNewsBinding
import com.pareekdevansh.newsnack.db.ArticleDatabase
import com.pareekdevansh.newsnack.repository.NewsRepository

lateinit var binding : ActivityNewsBinding
lateinit var newsRepository : NewsRepository
class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var viewModel: NewsViewModel


        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this , viewModelProviderFactory)[NewsViewModel::class.java]

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        setupWithNavController(binding.bottomNavigationView , navHostFragment.navController)

}

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return NewsViewModelProviderFactory(newsRepository)
    }
}