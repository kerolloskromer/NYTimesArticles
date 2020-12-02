package com.kromer.nytimes.presentation.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kromer.nytimes.R
import com.kromer.nytimes.databinding.FragmentArticlesBinding
import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.extensions.hide
import com.kromer.nytimes.extensions.show
import com.kromer.nytimes.presentation.base.BaseFragment
import com.kromer.nytimes.utils.Status
import com.kromer.nytimes.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticlesFragment : BaseFragment<FragmentArticlesBinding>() {

    private val viewModel: ArticlesViewModel by viewModels()
    private val items: ArrayList<Article> = ArrayList()
    private lateinit var adapter: ArticlesAdapter

    override fun getVBInflater(): (LayoutInflater) -> FragmentArticlesBinding =
        FragmentArticlesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        reset()
        getData(1)
    }

    private fun setupObservers() {
        viewModel.popularArticles.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    showLoading()
                }

                Status.ERROR -> {
                    showError(it.message!!)
                }

                Status.SUCCESS -> {
                    notifyAdapter(it.data!!)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = ArticlesAdapter(
            items = items,
            itemClick = { onItemClick(it) }
        )
        binding.recyclerView.adapter = adapter
    }

    private fun onItemClick(item: Article) {
        val action = ArticlesFragmentDirections.actionNavigationArticlesToDetails(item.id)
        findNavController().navigate(action)
    }

    private fun notifyAdapter(newItems: List<Article>) {
        if (newItems.isNotEmpty()) {
            items.addAll(newItems)
            adapter.notifyDataSetChanged()
        }

        if (items.isNotEmpty()) {
            showData()
        } else {
            showError(getString(R.string.no_data))
        }
    }

    private fun showLoading() {
        binding.progressBar.show()
    }

    private fun showError(error: String) {
        binding.recyclerView.hide()
        binding.progressBar.hide()
        binding.textView.show()

        binding.textView.text = error
    }

    private fun showData() {
        binding.recyclerView.show()
        binding.progressBar.hide()
        binding.textView.hide()
    }

    private fun reset() {
        binding.recyclerView.hide()
        binding.progressBar.show()
        binding.textView.hide()
        items.clear()
    }

    private fun getData(period: Int) {
        viewModel.getPopularArticles(
            period,
            Utils.isNetworkAvailable(requireContext())
        )
    }
}