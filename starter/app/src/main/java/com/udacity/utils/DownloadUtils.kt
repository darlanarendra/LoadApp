package com.udacity.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.udacity.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DownloadDetails(
                           val title:String?,
                           val status:Int,
                           val id:Int
) : Parcelable


fun Context.downloadFile(urlToDownload:String, title:String):Long{
    val request = DownloadManager.Request(Uri.parse(urlToDownload))
        .setTitle(title)
        .setDescription(getString(R.string.app_description))
        .setRequiresCharging(false)
        .setAllowedOverMetered(true)
        .setAllowedOverRoaming(true)
    var downloadManager = getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
    return downloadManager.enqueue(request)

}


fun Context.getDownloadDetails(downloadID:Long):DownloadDetails{
    val downloadManager = getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
    val query = DownloadManager.Query().setFilterById(downloadID)
    val cursor = downloadManager.query(query)
    if(cursor.moveToFirst()){
        val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
        val title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE))
        return DownloadDetails(title,status,downloadID.toInt())
    }
    return DownloadDetails(null,DownloadManager.STATUS_FAILED,-1)
}