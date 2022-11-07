package android.project.assignmentweek5.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.project.assignmentweek5.R
import android.project.assignmentweek5.databinding.FragmentPostsBinding
import android.project.assignmentweek5.databinding.FragmentUsersBinding
import android.project.assignmentweek5.ui.posts.adapter.PostsAdapter
import android.project.assignmentweek5.ui.users.adapter.UsersAdapter
import android.project.assignmentweek5.ui.users.viewmodel.UsersViewModel
import android.project.assignmentweek5.utilities.Resources
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private lateinit var navController: NavController
    private val usersViewModel by viewModels<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        observeUsers()
    }

    private fun observeUsers() {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    usersViewModel.usersState.collect { uiState ->
                        when (uiState) {
                            is Resources.Success -> {
                                binding.rvUsersList.layoutManager =
                                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                                val adapter = UsersAdapter(uiState.data!!)
                                binding.rvUsersList.adapter = adapter
                            }
                            is Resources.Loading -> {}
                            is Resources.Error -> {}
                            else -> {}
                        }
                    }
                }
            }
    }
}