package com.example.exomediadashshowcase

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devbrackets.android.exomedia.listener.OnCompletionListener
import com.devbrackets.android.exomedia.listener.OnErrorListener
import com.devbrackets.android.exomedia.listener.OnPreparedListener
import com.devbrackets.android.exomedia.ui.widget.VideoView
import kotlinx.android.synthetic.main.fragment_player.*

class PlayerFragment : Fragment(), OnPreparedListener, OnCompletionListener, OnErrorListener {

    var clipUrls: List<String>? = null
    private val videoView: VideoView by lazy {
        exomediaView
                .apply {
                    this.setOnPreparedListener(this@PlayerFragment)
                    this.setOnErrorListener(this@PlayerFragment)
                    this.setOnCompletionListener(this@PlayerFragment)
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_player, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clipUrls = arguments?.getStringArrayList("clipUrls")
        playNextVideo()
    }

    private fun playNextVideo() {
        clipUrls?.firstOrNull()?.let {
            videoView.setVideoURI(Uri.parse(it))
        }
        clipUrls = clipUrls?.drop(1)
    }

    override fun onPrepared() = videoView.start()

    override fun onCompletion() = playNextVideo()

    override fun onError(e: Exception?) = true
}