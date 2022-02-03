package com.rizqirama.utsmobiledua.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizqirama.utsmobiledua.R
import com.rizqirama.utsmobiledua.model.Data
import java.lang.invoke.MethodType

class AdapterData(var data: ArrayList<Data>) : RecyclerView.Adapter<AdapterData.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val a = data[position]
        holder.tvName.text = a.name
    }
}