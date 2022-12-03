package com.example.teachersjournal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersjournal.database.teachers.Constant
import com.example.teachersjournal.database.teachers.Teachers
import com.example.teachersjournal.database.teachers.TeachersDatabase
import com.example.teachersjournal.teacher.AddTeacherFragment
import com.example.teachersjournal.teacher.TeacherAdapter
import com.example.teachersjournal.teacher.TeacherViewModel
import com.example.teachersjournal.weather.overview.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var courseRV: RecyclerView
    private lateinit var newRecycleview: RecyclerView
    private lateinit var tempArrayList: ArrayList<Teachers>
    private lateinit var newArrayList: ArrayList<Teachers>

    private val db by lazy { TeachersDatabase(this) }
    lateinit var teacherAdapter: TeacherAdapter
    private val mainViewModel: TeacherViewModel by viewModels()
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button = findViewById(R.id.button_W)
        button.setOnClickListener {

            val myDialogFragment = WeatherFragment()
            val manager = supportFragmentManager
            myDialogFragment.show(manager, "myDialog")
        }




        setupView()
        setupListener()
        setupRecyclerView()


    }

  /*  override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    getItemsFromDb(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    getItemsFromDb(newText)
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }*/
/*
        private fun getItemsFromDb(searchText: String) {
            var searchText = searchText
            searchText = "%$searchText%"

            mainViewModel.searchDatabase(searchQuery = searchText)
                .observe(this@MainActivity, Observer { list ->
                list?.let {
                    Log.e("List = ", list.toString())
                }

            })

        }*/
    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {

        CoroutineScope(Dispatchers.IO).launch {
            teacherAdapter.setData(db.teachersDatabaceDao().gelAllTeacher())
            withContext(Dispatchers.Main) {
                teacherAdapter.notifyDataSetChanged()
            }
        }

    }

    private fun setupView() {
        supportActionBar!!.apply {
            title = "Teacher directory"
        }
    }

    private fun setupListener() {
        button_create.setOnClickListener {
            intentEdit(Constant.TYPE_CREATE, 0)
        }

    }

    private fun setupRecyclerView() {
        teacherAdapter = TeacherAdapter(
            arrayListOf(),
            object : TeacherAdapter.OnAdapterListener {
                override fun onClick(teachers: Teachers) {
                    intentEdit(Constant.TYPE_READ, teachers.teacherId)
                }

                override fun onUpdate(teachers: Teachers) {
                    intentEdit(Constant.TYPE_UPDATE, teachers.teacherId)
                }

                override fun onDelete(teachers: Teachers) {
                    deleteAlert(teachers)
                }
            })

        list_teacher.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = teacherAdapter
        }
    }

    private fun intentEdit(intent_type: Int, teacher_id: Int) {
        startActivity(
            Intent(this, AddTeacherFragment::class.java)
                .putExtra("intent_type", intent_type)
                .putExtra("teacher_id", teacher_id)
        )
    }
//______


//   ____

    private fun deleteAlert(teacher: Teachers) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Do you really want to delete")
            setMessage("Delete ${teacher.firstName}?")
            setNegativeButton("No") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Delete") { dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.teachersDatabaceDao().deleteTeacher(teacher)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        dialog.show()
    }


    /*   override fun onQueryTextSubmit(query: String?): Boolean {
           return true
       }

       override fun onQueryTextChange(query: String?): Boolean {
           if(query != null){
               searchDatabase(query)
           }
           return true
       }
    */

}
