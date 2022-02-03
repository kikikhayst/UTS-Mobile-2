package com.rizqirama.utsmobiledua.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizqirama.utsmobiledua.R
import com.rizqirama.utsmobiledua.adapter.AdapterData
import com.rizqirama.utsmobiledua.model.Data
import com.rizqirama.utsmobiledua.room.MyDatabase
import com.rizqirama.utsmobiledua.ui.activity.Data.AddDataActivity

class DataFragment : Fragment() {
    lateinit var myDB : MyDatabase
    lateinit var btnAddData : Button
    lateinit var tvStatus : TextView
    lateinit var rvData : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_data, container, false)
        init(root)
        myDB = MyDatabase.getInstance(requireContext())!!
        displayData()
        mainButton()
        return root
    }

    private fun mainButton() {
        btnAddData.setOnClickListener {
            startActivity(Intent(requireActivity(), AddDataActivity::class.java))
        }
    }

    private fun displayData() {
        val listData = myDB.dataAccessObject().getAll()

        if (listData.isEmpty()) {
            tvStatus.visibility = View.VISIBLE
        } else {
            tvStatus.visibility = View.GONE
        }

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvData.adapter = AdapterData(listData as ArrayList<Data>)
        rvData.layoutManager = layoutManager
    }

    private fun init(view: View) {
        btnAddData = view.findViewById(R.id.btn_tambah_data)
        tvStatus = view.findViewById(R.id.tv_status)
        rvData = view.findViewById(R.id.rv_data)
    }

    override fun onResume() {
        displayData()
        super.onResume()
    }
}