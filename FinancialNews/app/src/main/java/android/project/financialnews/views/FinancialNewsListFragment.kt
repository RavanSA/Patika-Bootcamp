package android.project.financialnews.views

import android.os.Bundle
import android.project.financialnews.R
import android.project.financialnews.adapters.FinancialNewsListener
import android.project.financialnews.adapters.FinancialNewsRecyclerAdapter
import android.project.financialnews.data.remote.dto.Article
import android.project.financialnews.databinding.FinancialNewsListFragmentBinding
import android.project.financialnews.utilities.Resource
import android.project.financialnews.viewmodels.FinancialNewsListViewModel
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FinancialNewsListFragment : Fragment(), FinancialNewsListener {

    private lateinit var navController: NavController
    private val viewModel by viewModels<FinancialNewsListViewModel>()

    private var _binding: FinancialNewsListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FinancialNewsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.newsList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resource.Success -> {
                    val adapter = result.data?.articles?.let {
                        FinancialNewsRecyclerAdapter(
                            requireContext(),
                            it,
                            this@FinancialNewsListFragment
                        )
                    }
                    binding.rvNewsList.adapter = adapter
                    result.data?.let { adapter?.differ?.submitList(it.articles) }
                    result.data?.articles?.let { headerOfNews(it) }
                    binding.tvNewsTitle.text = "Breaking News"
                    hideProgressBar()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error Occurred",Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> { showProgressBar() }
            }
        }
    }

    private fun headerOfNews(newsList: List<Article>) {
        val headerNews = newsList.shuffled().take(1)[0]
        binding.also {
            it.tvHeaderNews.text = headerNews.title
            it.cvMainNewsPicture.setOnClickListener {
                navController.navigate(R.id.action_financialNewsListFragment_to_financialNewsDetailFragment, Bundle().apply {
                    putString("newsList", headerNews.toJson())
                })
            }

            Glide.with(this@FinancialNewsListFragment)
                .load(headerNews.urlToImage)
                .placeholder(R.drawable.test_picture)
                .into(it.ivHeaderNews)
         }

    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.INVISIBLE
    }


    override fun onClicked(news: Article) {
        navController.navigate(R.id.action_financialNewsListFragment_to_financialNewsDetailFragment, Bundle().apply {
            putString("newsList", news.toJson())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}