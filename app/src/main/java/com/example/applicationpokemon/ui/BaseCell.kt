package com.example.applicationpokemon.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseCell
    (view: View): RecyclerView.ViewHolder(view) {
    var onClick: ((Int) -> Unit)? = null

    init {
        view.setOnClickListener {
            val index = adapterPosition
            if (index != RecyclerView.NO_POSITION) {
                onClick?.invoke(index)
            }
        }
    }

    open fun setSelection(value:Boolean){}

    open fun prepareForReuse(){}

}