package com.alexsapps.learncountriesandflagsgame.presentation.ui.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexsapps.learncountriesandflagsgame.data.entities.OneLifeStatsCapital
import com.alexsapps.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val countriesAndFlagsDao: CountriesAndFlagsDao
): ViewModel() {

    private var allCountriesInTheTable = countriesAndFlagsDao.getAllCountries()

    init {
        allCountriesInTheTable.observeForever{
            countries ->
                if(countries != null) {
                    Log.e("PICASO", "${countries.size} ")
                }
        }
    }



    fun completeDirectoryWithFlagsImages(context: Context) {

        Log.e("PICASO", "${allCountriesInTheTable.value}" )
        allCountriesInTheTable.value?.forEach {
            countries -> if (!checkIfFileExists(countries.countryName)) {
                saveImageToLocal(loadImageFromUrl(countries.flagUrl, context), countries.countryName, context)
        }
        }
    }

    fun insertTest() {
        viewModelScope.launch {
            countriesAndFlagsDao.insert(OneLifeStatsCapital(1, "alex", 20))
        }
    }


    private fun checkIfFileExists(flagName: String): Boolean {
        val directory =
            File("/storage/emulated/0/Android/data/com.example.learncountriesandflagsgame/files/Pictures")
        val file = File(directory, "image_${flagName}.png")
        return file.exists()
    }

    private fun saveImageToLocal(imageView: ImageView,flagName: String, context: Context) {

        val drawable = imageView.drawable
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap

            val file = File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "image_${flagName}.png"
            )

            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
            fos.close()

            MediaScannerConnection.scanFile(
                context,
                arrayOf(file.absolutePath),
                arrayOf("image/png"),
                null
            )


        }
    }

    private fun loadImageFromUrl(imageUrl: String, context: Context) : ImageView {
        var imageView = ImageView(context)

        Picasso
            .get()
            .load(imageUrl)
            .into(imageView)

        return imageView
    }
}