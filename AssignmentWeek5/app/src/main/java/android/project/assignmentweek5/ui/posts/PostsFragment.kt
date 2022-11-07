package android.project.assignmentweek5.ui.posts

import android.os.Bundle
import android.project.assignmentweek5.R
import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.project.assignmentweek5.databinding.FragmentPostsBinding
import android.project.assignmentweek5.ui.posts.adapter.PostListener
import android.project.assignmentweek5.ui.posts.adapter.PostsAdapter
import android.project.assignmentweek5.ui.posts.viewmodel.PostsViewModel
import android.project.assignmentweek5.utilities.Resources
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostsFragment : Fragment(), PostListener {

    private lateinit var binding:FragmentPostsBinding
    private lateinit var navController: NavController
    private val postsViewModel by viewModels<PostsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        observePosts()
    }

    private fun observePosts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postsViewModel.postsState.collect { uiState ->
                    when (uiState) {
                        is Resources.Success -> {
                            binding.rvPostsList.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            val adapter = uiState.data?.let { PostsAdapter(it, this@PostsFragment) }
                            binding.rvPostsList.adapter = adapter
                        }
                        is Resources.Loading -> {}
                        is Resources.Error -> {}
                        else -> {}
                    }
                }
            }
        }
    }

    override fun onClicked(post: PostsDTOItem) {
        navController.navigate(R.id.action_postsFragment_to_postDetailFragment, Bundle().apply {
            putString("postId", post.id.toString())
        })
    }

}