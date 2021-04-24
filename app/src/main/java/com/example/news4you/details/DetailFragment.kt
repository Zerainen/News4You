package com.example.news4you.details

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news4you.databinding.DetailFragmentBinding


class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val article = DetailFragmentArgs.fromBundle(requireArguments()).selectedArticle
        val viewModelFactory = DetailViewModelFactory(article, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        val binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.shareArticleButton.setOnClickListener {
            viewModel.selectedArticle.value?.url?.let { it -> onShareButtonClicked(it) }
        }

        binding.openArticleButton.setOnClickListener {
            openArticleWebsite()
        }

        return binding.root
    }
    fun onShareButtonClicked(articleUrl: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, articleUrl)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context?.startActivity(shareIntent)
    }

    fun openArticleWebsite() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(viewModel.selectedArticle.value?.url)
        }
        context?.startActivity(sendIntent)
    }
}