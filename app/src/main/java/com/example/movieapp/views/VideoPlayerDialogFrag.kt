package com.example.movieapp.views

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.movieapp.R
import kotlinx.android.synthetic.main.custom_video_player.*
import kotlinx.android.synthetic.main.related_movie_item.*
import java.util.*

class VideoPlayerDialogFrag: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Objects.requireNonNull(dialog)?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.custom_video_player,null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        playVideo()

    }

    fun playVideo(){
        val mediaController = MediaController(context)
        mediaController.setAnchorView(movieVideoView)

        val movieUri: Uri = Uri.parse("https://www.youtube.com/watch?v=VT1CYvzoaBk")
        movieVideoView.setMediaController(mediaController)
        movieVideoView.setVideoURI(movieUri)
        movieVideoView.requestFocus()
        movieVideoView.start()

    }

}