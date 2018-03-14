package com.example.exomediadashshowcase

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mp4Urls =
            listOf("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_2mb.mp4",
                    "http://techslides.com/demos/sample-videos/small.mp4")

    private var mp4WithUrlsWithDash =
            listOf("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_2mb.mp4",
                    "http://techslides.com/demos/sample-videos/small.mp4",
                    "http://rdmedia.bbc.co.uk/dash/ondemand/elephants_dream/1/client_manifest-all.mpd")

    private var dashUrl = listOf("http://rdmedia.bbc.co.uk/dash/ondemand/elephants_dream/1/client_manifest-all.mpd")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dashOnlyButton.setOnClickListener { startFragment(createPlayerFragment(dashUrl)) }
        dashAfterMp4Button.setOnClickListener { startFragment(createPlayerFragment(mp4WithUrlsWithDash)) }
        mp4OnlyButton.setOnClickListener { startFragment(createPlayerFragment(mp4Urls)) }
    }

    private fun startFragment(fragment: Fragment) =
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.playerContainer, fragment)
                    .commit()

    private fun createPlayerFragment(urls: List<String>) =
            PlayerFragment()
                    .apply {
                        arguments = Bundle().apply {
                            putStringArrayList("clipUrls", ArrayList(urls))
                        }
                    }
}
