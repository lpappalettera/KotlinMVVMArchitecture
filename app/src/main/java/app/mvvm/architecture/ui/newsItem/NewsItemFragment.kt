package app.mvvm.architecture.ui.newsItem

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import app.mvvm.architecture.R
import app.mvvm.architecture.databinding.FragmentNewsItemBinding
import app.mvvm.architecture.util.extensions.FragmentExtensions.viewBinding

class NewsItemFragment : Fragment(R.layout.fragment_news_item) {

    private val args: NewsItemFragmentArgs by navArgs()

    private val binding by viewBinding(FragmentNewsItemBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = args.newsItem.title
        binding.subtitle.text = args.newsItem.description
        binding.content.text = args.newsItem.content
    }
}