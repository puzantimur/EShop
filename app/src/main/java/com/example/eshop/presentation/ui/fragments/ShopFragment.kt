package com.example.eshop.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.eshop.databinding.CartFragmentBinding
import com.example.eshop.presentation.ui.adapters.BasketAdapter
import com.example.eshop.presentation.ui.utils.Lce
import com.example.eshop.presentation.ui.viewModels.CartViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopFragment : Fragment() {

    private var _binding: CartFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val cartViewModel by viewModel<CartViewModel>()

    private val adapter by lazy {
        BasketAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return CartFragmentBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.adapter = adapter
            cartViewModel
                .cartFlow
                .onEach { lce ->
                    when (lce) {
                        is Lce.Content -> {
                            cardView.isVisible = true
                            loading.isVisible = false
                            adapter.submitList(lce.data.basket)
                            totalCount.text = lce.data.total
                            delivery.text = lce.data.delivery
                        }
                        is Lce.Error -> Toast.makeText(
                            requireContext(),
                            lce.throwable.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        Lce.Loading -> {
                            loading.isVisible = true
                        }
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
