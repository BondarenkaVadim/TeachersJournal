package com.example.teachersjournal.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.teachersjournal.R
import com.example.teachersjournal.database.teachers.Constant
import com.example.teachersjournal.database.teachers.Teachers
import com.example.teachersjournal.database.teachers.TeachersDatabase
import com.google.api.Distribution
import kotlinx.android.synthetic.main.fragment_add_teacher.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddTeacherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTeacherFragment : AppCompatActivity() {
    // TODO: Rename and change types of parameters
    private val db by lazy { TeachersDatabase(this) }
    private var teacherId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_teacher)


        setupView()
        setupLstener()

    }

    private fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        when(intentType()){
            Constant.TYPE_CREATE -> {
                supportActionBar!!.title = "New Theacher"
                button_save.visibility = View.VISIBLE
                button_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                supportActionBar!!.title = "Read"
                button_save.visibility = View.GONE
                button_update.visibility = View.GONE
                getTeacher()
            }
            Constant.TYPE_UPDATE -> {
                supportActionBar!!.title = "Edit"
                button_save.visibility = View.GONE
                button_update.visibility = View.VISIBLE
                getTeacher()
            }
        }
    }



    private fun setupLstener() {
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.teachersDatabaceDao().insert(
                    Teachers(
                        0,
                        first_name.text.toString(),
                        second_name.text.toString()
                    )
                )
                finish()
            }
        }
        button_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.teachersDatabaceDao().update(
                    Teachers(
                        teacherId,
                        first_name.text.toString(),
                        second_name.text.toString()
                    )
                )
                finish()
            }
        }
    }

    private fun getTeacher() {
        teacherId = intent.getIntExtra("teacher_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val teacher = db.teachersDatabaceDao().getTeacher(teacherId).get(0)
            first_name.setText(teacher.firstName)
            second_name.setText(teacher.secondName)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun intentType(): Int {
        return intent.getIntExtra("intent_type", 0)
    }
}