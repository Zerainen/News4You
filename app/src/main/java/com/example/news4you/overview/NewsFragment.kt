package com.example.news4you.overview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.news4you.databinding.NewsFragmentBinding
import com.example.news4you.NewsArticleRecyclerViewAdapter

class NewsFragment(val category: String) : Fragment() {

    companion object {
        fun newInstance(category: String) = NewsFragment(category)
    }


    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: NewsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val viewModelFactory = NewsViewModelFactory(category, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        binding = NewsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = NewsArticleRecyclerViewAdapter(NewsArticleRecyclerViewAdapter.NewsArticleListener { article ->
            viewModel.onArticleClicked(article)
        })
        binding.newsRecyclerView.adapter = adapter

        viewModel.navigateToSelectedArticle.observe(viewLifecycleOwner, Observer {article ->
            if (article != null) {
                this.findNavController().navigate(
                    CollectionNewsFragmentsDirections.actionCollectionNewsFragmentsToDetailFragment2(
                        article
                    )
                )
                viewModel.navigationCompleted()
            }
        })

        return binding.root
    }

}