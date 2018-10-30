package example.smyy.plantcare.ui

import android.view.View

interface DetailCallback {

    fun onClickPlantImage(view: View)

    fun onClickRemove(view: View)

    fun onClickEdit(view: View)
}