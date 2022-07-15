package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var inputTitle: TextInputEditText
    private lateinit var inputDescription: TextInputEditText
    private lateinit var inputDeuDate: TextInputEditText
    private lateinit var btnDelete: Button
    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        inputTitle = findViewById(R.id.detail_ed_title)
        inputDescription = findViewById(R.id.detail_ed_description)
        inputDeuDate = findViewById(R.id.detail_ed_due_date)
        btnDelete = findViewById(R.id.btn_delete_task)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, viewModelFactory)[DetailTaskViewModel::class.java]
        detailTaskViewModel.setTaskId(intent.getIntExtra(TASK_ID, 0))
        detailTaskViewModel.task.observe(this){
            if (it != null){
                inputTitle.setText(it.title)
                inputDescription.setText(it.description)
                inputDeuDate.setText(DateConverter.convertMillisToString(it.dueDateMillis))
            }
        }

        btnDelete.setOnClickListener {
            detailTaskViewModel.deleteTask()
            onBackPressed()
        }
    }
}