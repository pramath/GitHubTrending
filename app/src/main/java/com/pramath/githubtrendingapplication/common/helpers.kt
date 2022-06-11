package com.pramath.githubtrendingapplication.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.pramath.githubtrendingapplication.TrendingApp

fun isInternetAvailable(): Boolean {
    return try {
        val cm =
            TrendingApp.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        activeNetwork?.isConnectedOrConnecting == true
    } catch (e: Exception) {
        false
    }

/*  fun loadImage(
          nameIv: ImageView,
          textTv: TextView,
          imageIv: ImageView,
          pb: ProgressBar,
          imagePath: String,
          firstName: String,
          lastName: String, context: Context
      ) {
          if (imagePath == "" || imagePath == null) {
              nameIv.visibility = View.VISIBLE
              textTv.visibility = View.VISIBLE
              imageIv.visibility = View.GONE
              pb.visibility = View.GONE

              val fName: String =
                  if (firstName.isNullOrBlank() || firstName.isNullOrEmpty())
                      "N"
                  else
                      firstName.substring(0, 1).toUpperCase()

              val lName: String =
                  if (lastName.isNullOrBlank() || lastName.isNullOrEmpty())
                      "A"
                  else
                      lastName.substring(0, 1).toUpperCase()

              textTv.text = fName + lName
          } else if (imagePath != "") {

              val contains = File(imagePath).extension in arrayOf("png", "jpg", "jpeg")

              if (contains) {
                  nameIv.visibility = View.GONE
                  textTv.visibility = View.GONE
                  imageIv.visibility = View.VISIBLE
                  pb.visibility = View.GONE

                  loadImage(
                      imageIv,
                      avatarPath,
                      context = context,
                      isCircle = true
                  )
              } else {
                  nameIv.visibility = View.VISIBLE
                  textTv.visibility = View.VISIBLE
                  imageIv.visibility = View.GONE
                  pb.visibility = View.GONE

                  val fName: String =
                      if (firstName.isNullOrBlank() || firstName.isNullOrEmpty())
                          "N"
                      else
                          firstName.substring(0, 1)

                  val lName: String =
                      if (lastName.isNullOrBlank() || lastName.isNullOrEmpty())
                          "A"
                      else
                          lastName.substring(0, 1)

                  textTv.text = fName + lName
              }
          }
      }
  */
}
