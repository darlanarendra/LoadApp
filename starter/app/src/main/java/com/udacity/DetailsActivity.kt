package com.udacity

import android.app.DownloadManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.udacity.databinding.ActivityDetailBinding
import com.udacity.utils.DownloadDetails
import com.udacity.utils.cancelNotification

/**
 *
 * @author Narendra Darla
 */
class DetailsActivity:AppCompatActivity() {

    companion object{
        const val EXTRA_DETAILS = "details"
        val TAG = DetailsActivity::class.java.simpleName
    }

    private lateinit var downloadDetails: DownloadDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        downloadDetails = intent.getParcelableExtra(EXTRA_DETAILS) ?:return
        cancelNotification(downloadDetails.id)
        with(binding.contentDetail){
            Log.v(TAG, "DetailsActivity"+ downloadDetails.status)
            if(downloadDetails.status == DownloadManager.STATUS_FAILED){
                Log.v(TAG, "DetailsActivity"+ downloadDetails.status)
                statusIcon.setImageResource(R.drawable.ic_error)
                downloadTitle.contentDescription = "Download Failed"
            }else{
                statusIcon.setImageResource(R.drawable.ic_success)
                downloadTitle.contentDescription = "Download Succeeded"
            }

            downloadTitle.setText(downloadDetails.title?:"")
            downloadId.setText(downloadDetails.id.toString()?:"")
        }
        binding.fab.setOnClickListener{
            onCloseFABClicked()
        }
    }

    private fun onCloseFABClicked() {

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}