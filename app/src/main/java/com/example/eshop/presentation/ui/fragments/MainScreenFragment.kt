package com.example.eshop.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.eshop.R
import com.example.eshop.databinding.MainScreenBinding
import com.example.eshop.presentation.ui.adapters.BestSellerAdapter
import com.example.eshop.presentation.ui.adapters.CategoryAdapter
import com.example.eshop.presentation.ui.adapters.HotSalesAdapter
import com.example.eshop.presentation.ui.utils.HorizontalMarginDecoration
import com.example.eshop.presentation.ui.utils.Lce
import com.example.eshop.presentation.ui.viewModels.MainScreenViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {

    private var _binding: MainScreenBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val brands by lazy { resources.getStringArray(R.array.brands) }
    private val brandItemAdapter by lazy {
        ArrayAdapter(requireContext(), R.layout.brand_item, brands)
    }

    private val prices by lazy { resources.getStringArray(R.array.price) }
    private val priceAdapter by lazy {
        ArrayAdapter(requireContext(), R.layout.price_item, prices)
    }

    private val size by lazy { resources.getStringArray(R.array.size) }
    private val sizeAdapter by lazy {
        ArrayAdapter(requireContext(), R.layout.size_item, size)
    }

    private val adapterBestSellers by lazy {
        BestSellerAdapter(
            requireContext(),
            onBestSellerClicked = {
                findNavController().navigate(MainScreenFragmentDirections.toDetails())
            })
    }

    private val adapterHotSales by lazy {
        HotSalesAdapter(
            requireContext(),
            onBuyClicked = {
                findNavController().navigate(MainScreenFragmentDirections.toDetails())
            })
    }

    private val adapterCategory by lazy {
        CategoryAdapter(
            requireContext(),
            onCategoryClicked = {}
        )
    }

    private val viewModel by viewModel<MainScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MainScreenBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerViewCategory.adapter = adapterCategory
            hotSalesRecycler.adapter = adapterHotSales
            bestSalesRecycler.layoutManager = GridLayoutManager(requireActivity(), 2)
            bestSalesRecycler.adapter = adapterBestSellers

            setupCarousel()

            settingsFilter.setOnClickListener {
                setupDialogFilter()
            }

            viewModel
                .categoryFlow
                .onEach {
                    adapterCategory.submitList(it)
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            viewModel
                .shopFlow
                .onEach { lce ->
                    when (lce) {
                        is Lce.Content -> {
                            loading.isVisible = false
                            hotSales.isVisible = true
                            textSeeMore.isVisible = true
                            hotSalesRecycler.isVisible = true
                            bestSellers.isVisible = true
                            textSeeMore2.isVisible = true
                            bestSalesRecycler.isVisible = true
                            adapterHotSales.submitList(lce.data.homeStore)
                            adapterBestSellers.submitList(lce.data.bestSeller)
                        }
                        is Lce.Error -> {
                            loading.isVisible = false
                            Toast.makeText(
                                requireContext(),
                                "Check your Internet",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Lce.Loading -> loading.isVisible = true
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun setupCarousel() {
        binding.hotSalesRecycler.offscreenPageLimit = 1
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_main_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            page.alpha = 0.25f + (1 - kotlin.math.abs(position))
        }
        binding.hotSalesRecycler.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginDecoration(
            requireContext(),
            R.dimen.viewpager_main_current_item_horizontal_margin
        )
        binding.hotSalesRecycler.addItemDecoration(itemDecoration)
    }

    private fun setupDialogFilter() {
        val dialog = BottomSheetDialog(
            requireContext()
        )
        val bottomSheetView = LayoutInflater.from(requireContext()).inflate(
            R.layout.filter_item, null
        )
        bottomSheetView.findViewById<AutoCompleteTextView>(R.id.auto_complete_brand)
            .setAdapter(brandItemAdapter)
        bottomSheetView.findViewById<AutoCompleteTextView>(R.id.auto_complete_price)
            .setAdapter(priceAdapter)
        bottomSheetView.findViewById<AutoCompleteTextView>(R.id.auto_complete_size)
            .setAdapter(sizeAdapter)
        bottomSheetView.findViewById<TextView>(R.id.done).setOnClickListener {
            dialog.dismiss()
            //can add some action except current
        }
        bottomSheetView.findViewById<ImageView>(R.id.close).setOnClickListener {
            dialog.dismiss()
        }
        dialog.setContentView(bottomSheetView)
        dialog.show()
    }
}
