package com.ivansertic.livemtg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivansertic.livemtg.data.MatchViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var mMatchViewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter= adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        mMatchViewModel = ViewModelProvider(this).get(MatchViewModel::class.java)
        mMatchViewModel.readAllData.observe(viewLifecycleOwner, Observer { match ->
            adapter.setData(match)
        })

        return view
    }

}