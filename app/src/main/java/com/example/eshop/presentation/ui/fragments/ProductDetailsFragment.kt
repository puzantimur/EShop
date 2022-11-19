package com.example.eshop.presentation.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.eshop.R
import com.example.eshop.databinding.ProductDetailsFragmentBinding
import com.example.eshop.presentation.ui.adapters.DetailAdapter
import com.example.eshop.presentation.ui.adapters.ImageDetailAdapter
import com.example.eshop.presentation.ui.utils.HorizontalMarginDecoration
import com.example.eshop.presentation.ui.utils.Lce
import com.example.eshop.presentation.ui.viewModels.DetailsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsFragment : Fragment() {

    private var _binding: ProductDetailsFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val tabNames: Array<String> = arrayOf(
        "Shop", "Details", "Features"
    )

    private val detailsViewModel by viewModel<DetailsViewModel>()

    private val adapterImages by lazy {
        ImageDetailAdapter(requireContext())
    }

    private val adapterDetails by lazy {
        DetailAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ProductDetailsFragmentBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            imagesPager.adapter = adapterImages
            setupCarousel()
            pager.adapter = adapterDetails

            TabLayoutMediator(tab, pager) { tab, position ->
                tab.text = tabNames[position]
            }.attach()

            back.setOnClickListener {
                findNavController().popBackStack()
            }
            addToCart.setOnClickListener {
                Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show()
            }

            detailsViewModel
                .detailFlow
                .onEach { lce ->
                    when (lce) {
                        is Lce.Content -> {
                            adapterDetails.submitList(listOf(lce.data, lce.data, lce.data))
                            add.text = lce.data.price
                            loading.isVisible = false
                            adapterImages.submitList(lce.data.images)
                            titleOfItem.text = lce.data.title
                            ratingBar.rating = lce.data.rating
                            favorite.setOnClickListener {
                                if (lce.data.isFavorites) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Deleted from favorites",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    favorite.foreground = ResourcesCompat.getDrawable(
                                        resources,
                                        R.drawable.ic_baseline_favorite_grey,
                                        null
                                    )
                                    lce.data.isFavorites = false
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Added to favorites",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    favorite.foreground = ResourcesCompat.getDrawable(
                                        resources,
                                        R.drawable.ic_baseline_favorite_white,
                                        null
                                    )
                                    lce.data.isFavorites = true
                                }
                            }
                            if (lce.data.isFavorites) {
                                favorite.foreground = ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.ic_baseline_favorite_white,
                                    null
                                )
                            }
                        }
                        is Lce.Error -> {
                            Toast.makeText(
                                requireContext(),
                                lce.throwable.message,
                                Toast.LENGTH_LONG
                            )
                                .show()
                            loading.isVisible = false
                        }
                        Lce.Loading -> binding.loading.isVisible = true
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupCarousel() {
        binding.imagesPager.offscreenPageLimit = 1
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_detail_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            page.alpha = 0.5f + (1 - kotlin.math.abs(position))
        }
        binding.imagesPager.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginDecoration(
            requireContext(),
            R.dimen.viewpager_detail_current_item_horizontal_margin
        )
        binding.imagesPager.addItemDecoration(itemDecoration)
    }
}
