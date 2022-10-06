package com.example.teachersjournal.teacher

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersjournal.R
import com.example.teachersjournal.TextItemViewHolder
import com.example.teachersjournal.database.teachers.Teachers

class TeacherAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<Teachers>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() =
        data.size


    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
    //    val item = data[position]
    //    holder.textView.text = item.teacherQuality.toString()
        holder.textView.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }
}