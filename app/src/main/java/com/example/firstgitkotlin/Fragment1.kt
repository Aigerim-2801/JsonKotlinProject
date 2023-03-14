package com.example.firstgitkotlin

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstgitkotlin.databinding.Fragment1LayoutBinding
import com.example.firstgitkotlin.json.SemestersItem
import java.io.Serializable


class Fragment1 : Fragment(R.layout.fragment1_layout) {

    private var _binding: Fragment1LayoutBinding? = null
    private val binding get() = _binding!!

    private val itemAdapter: ItemAdapter = ItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = Fragment1LayoutBinding.bind(view)

        val sem = arguments?.getSerializableCompat("semestr", SemestersItem::class.java)

        if(sem?.Disciplines?.isNotEmpty() == true){

            val layoutManager = LinearLayoutManager(requireContext())
            binding.items.layoutManager = layoutManager
            binding.items.adapter = itemAdapter
            itemAdapter.setList(sem.Disciplines)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun <T : Serializable?> Bundle.getSerializableCompat(key: String, clazz: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getSerializable(key, clazz)!!
    else (getSerializable(key) as T)
}

