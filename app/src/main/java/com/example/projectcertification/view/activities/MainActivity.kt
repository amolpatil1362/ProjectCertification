package com.example.projectcertification.view.activities

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.projectcertification.*
import com.example.projectcertification.databinding.ActivityMainBinding
import com.example.projectcertification.model.Words
import com.example.projectcertification.view.adapters.WordListAdapter


class MainActivity : AppCompatActivity() {

    private val channelId = "i.android.notifications"
    private val notificationDescription = "Word notification"

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as ProjectApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dataList: List<Words>? = null



        wordViewModel.getAllWords().observe(this) {
            // Update the cached copy of the words in the adapter.
            // words.let { adapter.submitList(it) }
            dataList = it
            binding.rv.adapter =
                dataList?.let { WordListAdapter({ person -> onWordItemClick(person) }, it) }

        }

        binding.rv.setHasFixedSize(true)

        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddWordActivity::class.java)
            resultLauncher.launch(intent)
        }


    }

    private fun onWordItemClick(word: Words) {
        Toast.makeText(this, word.strName, Toast.LENGTH_SHORT).show()
    }


    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data

                data?.getStringExtra(AddWordActivity.EXTRA_REPLY)?.let { reply ->
                    val word = Words(reply)
                    wordViewModel.insertWord(word)
                    sendNotification(word.strName)
                }

            }
        }


    private fun sendNotification(text: String) {
        createNotificationChannel()
        // Create an explicit intent for an Activity in your app
        /*  val intent = Intent(this, AddWordActivity::class.java).apply {
              flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
          }
          val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)*/

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_add_black_24dp)
            .setContentTitle("Word Added")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            //.setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(1234, builder.build())
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "DefaultChannel"
            val descriptionText = notificationDescription
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}