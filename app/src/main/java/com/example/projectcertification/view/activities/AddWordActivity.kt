package com.example.projectcertification.view.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.projectcertification.R
import com.example.projectcertification.databinding.ActivityAddWordBinding

class AddWordActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        val binding = ActivityAddWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.et.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = binding.et.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }


    }







    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}