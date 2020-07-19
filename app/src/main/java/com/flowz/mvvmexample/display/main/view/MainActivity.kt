package com.flowz.mvvmexample.display.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.flowz.mvvmexample.R
import com.flowz.mvvmexample.data.model.User
import com.flowz.mvvmexample.data.model.api.ApiHelper
import com.flowz.mvvmexample.data.model.api.ApiServiceImpl
import com.flowz.mvvmexample.display.main.view.adapter.MainAdapter
import com.flowz.mvvmexample.display.main.view.base.ViewModelFactory
import com.flowz.mvvmexample.display.main.view.viewmodel.MainViewModel
import com.flowz.mvvmexample.utills.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupDisplay()
        setupViewModel()
        setupObserver()
    }

    private fun setupDisplay(){

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())

        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context,(recyclerView.layoutManager as LinearLayoutManager).orientation))

        recyclerView.adapter = adapter
    }

    private fun setupObserver(){

        mainViewModel.getUsers().observe(this, Observer {

            when(it.status){
                Status.SUCCESS->{
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }

                Status.LOADING ->{

                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE

                }

                Status.ERROR->{
                    //fix error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>){
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun  setupViewModel(){
        mainViewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(MainViewModel::class.java)
    }
}
