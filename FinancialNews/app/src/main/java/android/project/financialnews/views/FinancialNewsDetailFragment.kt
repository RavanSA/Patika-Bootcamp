package android.project.financialnews.views

import android.os.Bundle
import android.project.financialnews.R
import android.project.financialnews.data.remote.dto.Article
import android.project.financialnews.databinding.FinancialNewsDetailFragmentBinding
import android.project.financialnews.databinding.FinancialNewsListFragmentBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

class FinancialNewsDetailFragment : Fragment() {

    private var _binding: FinancialNewsDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FinancialNewsDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setUpView()
    }

    private fun setUpView() {
        arguments?.let { bundle ->
            val getSentData = bundle.getString("newsList")
            getSentData?.let { newsData ->
                val receiveArticle = Article.fromJson(newsData)
                binding.also {
                    it.title.text = receiveArticle.title
                    it.description.text = receiveArticle.description
                    it.content.text = receiveArticle.content.repeat(5)
                    it.newsDetailArrowBack.setOnClickListener { backButton() }

                    Glide.with(this@FinancialNewsDetailFragment)
                        .load(receiveArticle.urlToImage)
                        .placeholder(R.drawable.test_picture)
                        .into(it.newsImage)
                }
            }
        }
    }

    private fun backButton(){
        navController.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}