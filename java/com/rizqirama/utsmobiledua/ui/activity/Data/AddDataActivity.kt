package com.rizqirama.utsmobiledua.ui.activity.Data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.util.DBUtil
import com.rizqirama.utsmobiledua.R
import com.rizqirama.utsmobiledua.model.Data
import com.rizqirama.utsmobiledua.room.MyDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_tambah_data.*
import java.util.*

class AddDataActivity : AppCompatActivity() {
    lateinit var myDB : MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data)

        myDB = MyDatabase.getInstance(this)!!

        btn_simpan_data.setOnClickListener {
            if (edt_kode_data.text.toString().isEmpty()) {
                edt_kode_data.error = "Kolom tidak boleh kosong"
                edt_kode_data.requestFocus()
            } else if (edt_nama_data.text.toString().isEmpty()) {
                edt_nama_data.error = "Kolom tidak boleh kosong"
                edt_nama_data.requestFocus()
            } else {
                val data = Data()
                data.name = edt_nama_data.text.toString()
                save(data)
            }
        }
    }

    private fun save(data: Data) {
        CompositeDisposable().add(Observable.fromCallable {myDB.dataAccessObject().insert(data)}
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    toast("Data kelas berhasil di simpan")
                    onBackPressed()
                }
        )
    }

    private fun toast(string : String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}