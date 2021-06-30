package app.mvvm.architecture.ui.newsOverview

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.mvvm.architecture.R
import app.mvvm.architecture.databinding.FragmentNewsOverviewBinding
import app.mvvm.architecture.databinding.ItemviewNewsBinding
import app.mvvm.architecture.model.NewsItem
import app.mvvm.architecture.util.ComposedAdapter
import app.mvvm.architecture.util.Resource
import app.mvvm.architecture.util.extensions.FragmentExtensions.viewBinding
import app.mvvm.architecture.util.viewHolderFrom
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsOverviewFragment : Fragment(R.layout.fragment_news_overview) {

    private val newsOverviewViewModel: NewsOverviewViewModel by viewModel()

    private val binding by viewBinding(FragmentNewsOverviewBinding::bind)

    private val adapter= ComposedAdapter(
            items = emptyList<NewsItem>(),
            viewHolderCreator = { parent, _ ->
                parent.viewHolderFrom(ItemviewNewsBinding::inflate)
            },
            viewHolderBinder = { viewHolder, item, _ ->
                viewHolder.binding.title.text = item.title
                viewHolder.binding.subtitle.text = item.description
                viewHolder.binding.subtitle.isVisible = item.description != null
                viewHolder.itemView.setOnClickListener {
                    navigateToNewsItemFragment(item)
                }
            }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewCountries.adapter = adapter

        newsOverviewViewModel.newsLiveData.observe(viewLifecycleOwner) { newsResource ->
            if (newsResource is Resource.Loading)
                binding.progressBar.show()
            else
                binding.progressBar.hide()

            newsResource.error?.getContentIfNotHandled()?.showSnackBar(view)
            newsResource.data?.let { countries ->
                adapter.update(countries) {
                    it.title + it.author
                }
            }
        }
    }

    private fun navigateToNewsItemFragment(newsItem: NewsItem) {
        findNavController().navigate(
                NewsOverviewFragmentDirections.actionNewsOverviewFragmentToNewsItemFragment(newsItem)
        )
    }
}