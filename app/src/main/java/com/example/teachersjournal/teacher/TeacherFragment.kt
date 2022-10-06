package com.example.teachersjournal.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.teachersjournal.R
import com.example.teachersjournal.database.teachers.TeachersDatabase
import com.example.teachersjournal.databinding.FragmentTeacherBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_teacher.*

class TeacherFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentTeacherBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_teacher, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = TeachersDatabase.getInstance(application).teachersDatabaceDao
        val viewModelFactory = TeacherViewModelFactory(dataSource, application)
        val teacherViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(TeacherViewModel::class.java)

        binding.teacherViewModel = teacherViewModel
        binding.setLifecycleOwner(this)




        teacherViewModel.showSnackbarEvent.observe(this, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                teacherViewModel.doneShowingSnackbar()
            }
        })


        binding.buttonWeather.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_teacherFragment_to_weatherFragment)
        }


        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var text: TextView = view.findViewById(R.id.title_text)
        button_showAllTeacher.setOnClickListener {
            text.text = null

        }
    }
}