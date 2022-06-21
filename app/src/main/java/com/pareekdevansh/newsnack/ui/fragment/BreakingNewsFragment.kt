package com.pareekdevansh.newsnack.ui.fragment

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pareekdevansh.newsnack.R
import com.pareekdevansh.newsnack.adapters.NewsAdapter
import com.pareekdevansh.newsnack.databinding.FragmentBreakingNewsBinding
import com.pareekdevansh.newsnack.db.ArticleDatabase
import com.pareekdevansh.newsnack.repository.NewsRepository
import com.pareekdevansh.newsnack.ui.NewsActivity
import com.pareekdevansh.newsnack.ui.NewsViewModel
import com.pareekdevansh.newsnack.util.Resource
import retrofit2.Response


class BreakingNewsFragment:Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel: NewsViewModel
    private var _binding : FragmentBreakingNewsBinding? = null
    // this property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    lateinit var newsAdapter : NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: NewsViewModel by activityViewModels()

        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner , Observer{ response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let{ message ->
                        Toast.makeText(requireContext(), "An error occurred : $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.pbBreakingNews.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        binding.pbBreakingNews.visibility = View.VISIBLE
    }
    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}