package com.example.teachersjournal.teacher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.teachersjournal.database.teachers.Teachers
import com.example.teachersjournal.databinding.ItemTeacherBinding

import kotlinx.coroutines.flow.Flow


class TeacherAdapter (var teachers: ArrayList<Teachers>, var listener: OnAdapterListener):

    RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {



    private var oldData = emptyList<Teachers>()

   // class TeacherViewHolder(val view: View) : RecyclerView.ViewHolder(view)
   class TeacherViewHolder(val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root)
    //___
    class ButtonViewHolder(layout: ConstraintLayout) : ViewHolder(layout)
    //___

    override fun getItemCount() : Int{
        return teachers.size
    }



    interface OnAdapterListener {
        fun onClick(teachers: Teachers)
        fun onUpdate(teachers: Teachers)
        fun onDelete(teachers: Teachers)
    }

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
    return TeacherViewHolder(
        ItemTeacherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
}


//    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
//        val teachers = teachers[position]
//        holder.view.text_title.text = teachers.firstName
//        holder.view.text_title.setOnClickListener{
//            listener.onClick(teachers)
//        }
//        holder.view.icon_edit.setOnClickListener{
//            listener.onUpdate(teachers)
//        }
//        holder.view.icon_delete.setOnClickListener {
//            listener.onDelete(teachers)
//        }
//    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teachers = teachers[position]
        holder.binding.textTitle.text = teachers.firstName
        holder.binding.textTitle.setOnClickListener{
            listener.onClick(teachers)
        }
        holder.binding.iconEdit.setOnClickListener{
            listener.onUpdate(teachers)
        }
        holder.binding.iconDelete.setOnClickListener {
            listener.onDelete(teachers)
        }
    }


    fun setData(newData: List<Teachers>){
        teachers.clear()
        teachers.addAll(newData)


    }


}