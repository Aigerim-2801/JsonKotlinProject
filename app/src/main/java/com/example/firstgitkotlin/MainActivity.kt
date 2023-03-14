package com.example.firstgitkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.firstgitkotlin.databinding.ActivityMainBinding
import com.example.firstgitkotlin.json.MainItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapterr: Adapter
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val inputStream = assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val mainItem = Gson().fromJson(jsonString, MainItem::class.java)
            adapterr = Adapter(supportFragmentManager, lifecycle, mainItem)

            binding.textViewYear.text = mainItem.AcademicYear

            binding.pager.adapter = adapterr

            TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
                tab.text = getString(R.string.semestr, mainItem.Semesters[position].Number)
            }.attach()

        }
        catch (e: Exception) {
            e.printStackTrace()
        }


        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
                binding.tabLayout.tabGravity = TabLayout.GRAVITY_CENTER

            }
        })
        binding.btnLeft.setOnClickListener {
            val selectedTabIndex = binding.tabLayout.selectedTabPosition
            if (selectedTabIndex == 0) {
                finish()
            } else
                binding.tabLayout.getTabAt(selectedTabIndex - 1)?.select()
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

    }
}
