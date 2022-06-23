package com.example.homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.saveButton.setOnClickListener{
            checkOnSaveButtonClick()
        }
        binding.clearButton.setOnLongClickListener{
            clearLines()
            return@setOnLongClickListener true
        }
    }

    private fun checkOnSaveButtonClick(){
        val viewList = mapOf(binding.email to "Email", binding.userName to "Username",
                                binding.firstName to "First name", binding.lastName to "Last name",
                                binding.age to "Age")
        var toastStartText: String
        for(i in viewList.keys)
            if(i.text.toString().isEmpty()){
                toastStartText = viewList[i].toString()
                myToast("$toastStartText shouldn't be empty")
                return
            }
        if(binding.userName.text.toString().length<10) {
            myToast("Username length should be at least 10")
            return
        }
        val email = binding.email.text.toString()
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            myToast("Email is not valid")
            return
        }
        //input type does not allow non integer or negative values
        //anyway, but since it was required
        try{
            if(binding.age.text.toString().toInt()<0)
                myToast("Age should be more then 0")
        }catch(e: Exception) {
            myToast("Age should be an integer")
        }
    }
    private fun clearLines(){
        binding.age.text = null
        binding.lastName.text = null
        binding.firstName.text = null
        binding.userName.text = null
        binding.email.text = null
    }
    private fun myToast(text: String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }
}