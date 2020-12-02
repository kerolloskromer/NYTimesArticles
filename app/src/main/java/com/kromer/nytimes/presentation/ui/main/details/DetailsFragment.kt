package com.kromer.nytimes.presentation.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.kromer.nytimes.databinding.FragmentDetailsBinding
import com.kromer.nytimes.extensions.hide
import com.kromer.nytimes.extensions.show
import com.kromer.nytimes.presentation.base.BaseFragment
import com.kromer.nytimes.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val viewModel: DetailsViewModel by viewModels()
    private var articleId: Long = -1

    override fun getVBInflater(): (LayoutInflater) -> FragmentDetailsBinding =
        FragmentDetailsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtras()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getArticle(articleId)
    }

    private fun getExtras() {
        val args = DetailsFragmentArgs.fromBundle(requireArguments())
        articleId = args.articleId
    }

    private fun setupObservers() {
        viewModel.article.observe(
            viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Status.LOADING -> {
                        binding.progressBar.show()
                    }

                    Status.ERROR -> {
                        binding.progressBar.hide()

                    }

                    Status.SUCCESS -> {
                        binding.progressBar.hide()

                        val image: String = if (it.data?.media?.isNotEmpty()!!) {
                            it.data.media[0].mediaMetadata[0].url
                        } else {
                            ""
                        }
                        binding.ivPhoto.load(image)
                        binding.tvTitle.text = it.data.title
                        binding.tvAbstract.text = it.data.abstractTxt
                        binding.tvByline.text = it.data.byline
                        binding.tvDate.text = it.data.publishedDate
                    }
                }
            }
        )
    }
}
