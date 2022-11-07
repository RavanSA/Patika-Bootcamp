package android.project.assignmentweek5.ui.posts.postdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.project.assignmentweek5.databinding.FragmentPostDetailBinding
import android.project.assignmentweek5.ui.posts.adapter.PostsAdapter
import android.project.assignmentweek5.utilities.Resources
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentPostDetailBinding
    private val postDetailViewModel by viewModels<PostDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        binding.backButton = this@PostDetailFragment
        binding.viewModel = postDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        observePostDetail()

    }

    private fun observePostDetail() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postDetailViewModel.postDetailState.collect { uiState ->
                    when (uiState) {
                        is Resources.Success -> {
                            binding.postDetail = uiState.data
                            binding.favorite.setOnClickListener {
                                postDetailViewModel.isFavoriteButtonClicked()
                            }
                        }
                        is Resources.Loading -> {}
                        is Resources.Error -> {}
                        else -> {}
                    }
                }
            }
        }
    }

    fun navigateToBack() {
        navController.popBackStack()
    }

}