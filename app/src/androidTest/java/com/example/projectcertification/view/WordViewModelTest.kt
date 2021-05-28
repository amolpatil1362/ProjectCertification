package com.example.projectcertification.view

/*import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import com.example.projectcertification.WordViewModel
import com.example.projectcertification.database.WordDatabase
import com.example.projectcertification.model.Words
import com.example.projectcertification.repository.WordRepository
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue


import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.MatcherAssert.assertThat*/

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import com.example.projectcertification.WordViewModel
import com.example.projectcertification.database.WordDatabase
import com.example.projectcertification.model.Words
import com.example.projectcertification.repository.WordRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WordViewModelTest {

    private lateinit var viewModel: WordViewModel
    private lateinit var database: WordDatabase

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUpViewModel() {

        //val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            WordDatabase::class.java
        ).allowMainThreadQueries().build()

        val repository = WordRepository(database.wordDao())
        viewModel = WordViewModel(repository)
    }

    @Test
    fun temptest() {

        viewModel.insertWord(Words("TEST"))


        val value = viewModel.getAllWords().getOrAwaitValue()

        assertThat(value[0], not(nullValue()))

        //assertThat(value, CoreMatchers.`is`(true))

    }
}