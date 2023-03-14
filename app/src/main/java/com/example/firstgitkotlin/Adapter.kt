package com.example.firstgitkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.firstgitkotlin.json.MainItem


class Adapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val mainItem: MainItem
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = mainItem.Semesters.size

    override fun createFragment(position: Int): Fragment {
        val sem = mainItem.Semesters[position]
        val f = Fragment1()
        val args = Bundle()
        args.putSerializable("semestr", sem)
        f.arguments = args
        return f
    }
}