package com.example.eshop.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.eshop.R
import com.example.eshop.databinding.ContentFragmentBinding
import com.example.eshop.presentation.ui.utils.Lce
import com.example.eshop.presentation.ui.viewModels.CartViewModel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContentFragment : Fragment() {

    private var _binding: ContentFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val cartViewModel by viewModel<CartViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ContentFragmentBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.navigation_shop_list)
        badge.isVisible = true


        with(binding) {
            val nestedController =
                (childFragmentManager.findFragmentById(R.id.page_container) as NavHostFragment)
                    .navController
            bottomNavigation.setupWithNavController(nestedController)


            cartViewModel
                .cartFlow
                .onEach {lce ->
                    when(lce) {
                        is Lce.Content -> {
                            if(lce.data.basket.size == 0 ) {
                                badge.isVisible = false
                            } else {
                                badge.number = lce.data.basket.size
                            }
                        }
                        is Lce.Error -> {}
                        Lce.Loading -> {}
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
