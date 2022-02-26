package com.example.android__theaudiodb.exposition.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import com.example.android__theaudiodb.R
import com.google.android.material.tabs.TabLayout

class RankingFragment : Fragment(R.layout.fragment_ranking) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        changeToMusicTitlesView()
        setUpTabLayout(view.findViewById(R.id.ranking_tab))
    }

    private fun setUpTabLayout(tabLayout: TabLayout) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when {
                    tab === null -> return
                    tab.text == "Albums" -> changeToAlbumsView()
                    tab.text == "Titres" -> changeToMusicTitlesView()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }

    private fun changeToMusicTitlesView() {
        childFragmentManager.beginTransaction().replace(
            R.id.ranking_tab_container,
            RankingTabMusicTitlesFragment()
        ).commit()
    }

    private fun changeToAlbumsView() {
        childFragmentManager.beginTransaction().replace(
            R.id.ranking_tab_container,
            RankingTabAlbumsFragment()
        ).commit()
    }
}