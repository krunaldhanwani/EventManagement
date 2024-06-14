package com.example.eventapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.eventapp.adapter.HomeBannerViewPagerAdapter
import com.example.eventapp.model.image
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeBannerViewPagerAdapter: HomeBannerViewPagerAdapter
    private var images = ArrayList<image>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }


    private fun initData() {
        addData()
        homeBannerViewPagerAdapter = HomeBannerViewPagerAdapter(requireContext(),images)
        vpImage.adapter = homeBannerViewPagerAdapter
        vpImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        vpImage.offscreenPageLimit = 1
        viewpagerAdapter()
    }
    private fun addData() {
        images.add(image(R.drawable.a3))
        images.add(image(R.drawable.a1))
        images.add(image(R.drawable.a2))
        images.add(image(R.drawable.a5))
    }

    private fun viewpagerAdapter() {
        val nextItemVisiblePx = 0
        val currentItemHorizontalMarginPx = 0
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.00f * kotlin.math.abs(position))
        }
        vpImage.setPageTransformer(pageTransformer)

        val tabLayoutMediator = TabLayoutMediator(indicator , vpImage, true) { _, _ -> }
        tabLayoutMediator.attach()
    }
}