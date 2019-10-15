package com.example.sampletv.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.leanback.app.BrowseSupportFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletv.R
import com.example.sampletv.adapters.ItemListAdapter
import kotlinx.android.synthetic.main.fragment_analysis.*

class SampleFragmentB : Fragment(), BrowseSupportFragment.MainFragmentAdapterProvider {

    private val topics = listOf(
        "Education",
        "Finance",
        "Government",
        "Entertainment",
        "Technology",
        "Math",
        "Biology",
        "Physics",
        "Chemistry",
        "Space",
        "Sports",
        "Music",
        "Animal",
        "Countries",
        "Weather",
        "Politics",
        "Traffic",
        "Poverty",
        "Social Media",
        "Internet",
        "Housing"
    )
    private lateinit var mTextView: Button
    var items: ArrayList<String> = ArrayList()
    private var mainFragmentAdapter = BrowseSupportFragment.MainFragmentAdapter(this)
    override fun getMainFragmentAdapter(): BrowseSupportFragment.MainFragmentAdapter<SampleFragmentB> {
        return mainFragmentAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMainFragmentAdapter().fragmentHost.showTitleView(false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_analysis, container, false)
        var recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.verticalRecycler)
        var vw: View = view.findViewById<View>(R.id.vw)

        /*items.add("A")
        items.add("B")
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = MainAdapter(items, view.context)*/
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = ItemListAdapter(topics)
        vw.setOnFocusChangeListener{  _, hasFocus ->
            Toast.makeText(view.context, "Focused View", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}