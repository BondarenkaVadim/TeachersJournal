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
//Fragment()
class AddTeacherFragment : AppCompatActivity() {
    // TODO: Rename and change types of parameters
    private val db by lazy { TeachersDatabase(this) }
    private var teacherId = 0

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_teacher)
        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/

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
                        edit_title.text.toString(),
                        edit_note.text.toString()
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
                        edit_title.text.toString(),
                        edit_note.text.toString()
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
            edit_note.setText(teacher.firstName)
            edit_note.setText(teacher.secondName)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun intentType(): Int {
        return intent.getIntExtra("intent_type", 0)
    }

/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = TeachersDatabase.getInstance(application).teachersDatabaceDao


        val buttonAdd = view.findViewById<Button>(com.example.teachersjournal.R.id.button_Addteacher)
        buttonAdd?.setOnClickListener {

            val teacherViewModel = TeacherViewModel(dataSource, application)
            teacherViewModel.addTeacher(NameTeacher.text.toString(), SecondName.text.toString())


            findNavController().navigate(R.id.action_addTeacherFragment_to_teacherFragment, null)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            com.example.teachersjournal.R.layout.fragment_add_teacher,
            container,
            false
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddTeacherFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTeacherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}