package com.example.firstgitkotlin

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstgitkotlin.databinding.HoursItemBinding
import com.example.firstgitkotlin.json.DisciplineItem
import com.example.firstgitkotlin.json.LessonItem

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val disciplineList = mutableListOf<DisciplineItem>()

    private val green = Color.GREEN

    private val red = Color.RED


    fun setList(list: List<DisciplineItem>) {
        disciplineList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {

        val binding = HoursItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)

    }

    private fun isContainId(list: List<LessonItem>, id: String): SpannableStringBuilder {
        val find = list.find {
            it.LessonTypeId == id
        }
        val builder = SpannableStringBuilder()
        if (find != null) {
            val isEqual = find.RealHours == find.Hours
            builder
                .append(SpannableString(find.RealHours).apply {
                    setSpan(
                        ForegroundColorSpan(green),
                        0,
                        find.RealHours.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                })
                .append(" / ")
                .append(SpannableString(find.Hours).apply {
                    setSpan(
                         ForegroundColorSpan(if (isEqual) green else red),
                        0,
                        find.Hours.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                })
        } else {
            builder.append(" ")
        }
        return builder
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder){
            with(disciplineList[position]){
                binding.disciplineItem.text = this.DisciplineName.nameRu
                binding.lectureH.text = isContainId(this.Lesson,"1")
                binding.seminarH.text = isContainId(this.Lesson,"2")
                binding.labaratH.text = isContainId(this.Lesson,"3")

            }
        }
    }

    override fun getItemCount(): Int {
        return disciplineList.size
    }

    class ItemViewHolder(val binding: HoursItemBinding) : RecyclerView.ViewHolder(binding.root)
}
