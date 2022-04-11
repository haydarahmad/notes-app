package com.haydar.notesapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.haydar.notesapp.R
import com.haydar.notesapp.data.local.entity.Notes
import com.haydar.notesapp.data.local.entity.Priority
import com.haydar.notesapp.databinding.RowItemNotesBinding

class HomeAdapter  : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var listNotes = ArrayList<Notes>()

    inner class MyViewHolder(val binding: RowItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNotes.get(position)
        holder.binding.apply {
            mNotes = data
            executePendingBindings()

        }
    }

    override fun getItemCount(): Int = listNotes.size

    fun setData(data: List<Notes>?) {
        if (data == null) return
        val diffCallback = DiffCallback(listNotes, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listNotes.clear()
        listNotes.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}