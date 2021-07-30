package com.example.movieapp.views

import android.media.MediaPlayer;
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment(R.layout.fragment_start) {

    private val TAG = "StartFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // declaring a null variable for VideoView
        var simpleVideoView: VideoView? = null

        // declaring a null variable for MediaController
        var mediaControls: MediaController? = null

        if (mediaControls == null) {
            // creating an object of media controller class
            mediaControls = MediaController(context)

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.bg_videos)
        }

//        bg_videos.setVideoPath(
//            "https://www.youtube.com/watch?v=6nqKBKw7bDA")

        mediaControls = MediaController(context)
        mediaControls.setAnchorView(bg_videos)
        bg_videos.setMediaController(mediaControls)

        bg_videos.setOnPreparedListener { mp ->
            mp.isLooping = true
            Log.i(TAG, "Duration = " + bg_videos.duration)
        }
        bg_videos.start()

    }

}