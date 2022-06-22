package com.pareekdevansh.newsnack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.pareekdevansh.newsnack.R
import com.pareekdevansh.newsnack.databinding.FragmentArticleBinding
import com.pareekdevansh.newsnack.models.Article
import com.pareekdevansh.newsnack.ui.NewsViewModel

class ArticleFragment:Fragment(R.layout.fragment_article) {
    private val args: ArticleFragmentArgs by navArgs()
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    lateinit var article: Article


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: NewsViewModel by activityViewModels()

        val article = args.article
        binding.articleWebView.apply {
            hideProgressBar()
            visibility = View.VISIBLE
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
            loadUrl(article.url)
        }

        binding.fabSaveArticle.setOnClickListener {
            viewModel.upsert(article)
            Snackbar.make(it, "Article saved Successfully", Snackbar.LENGTH_SHORT).show()
        }


    }
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}