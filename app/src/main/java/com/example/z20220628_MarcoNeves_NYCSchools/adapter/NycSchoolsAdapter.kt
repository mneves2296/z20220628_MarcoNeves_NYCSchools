package com.example.z20220628_MarcoNeves_NYCSchools.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.z20220628_MarcoNeves_NYCSchools.R
import com.example.z20220628_MarcoNeves_NYCSchools.model.SchoolDTO

class NycSchoolsAdapter(private val onClick: OnSchoolSelected,
                        private val context: Context): RecyclerView.Adapter<SchoolViewHolder>() {

    interface OnSchoolSelected{
        fun openDetailsFragment(currentSchool: SchoolDTO)
    }

    private lateinit var myContext: Context

    private var schoolList: List<SchoolDTO> = listOf()
    var schoolDTOS: List<SchoolDTO> = listOf()
        set(value) {
            field = value
            schoolList = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = schoolList.size

    /**
     * Allow for querying by [schoolName]
     */
    fun search(schoolName: String) {
        schoolList = if(schoolName.trim().isEmpty()) schoolDTOS
        // disregard capitalization when matching the school name
        else schoolDTOS.filter { it.school_name.lowercase().startsWith(schoolName.lowercase())}
        notifyDataSetChanged()
    }

    /**
     * Takes care of inflating the item layout [R.layout.school_line_item]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        myContext = context
        return SchoolViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.school_line_item,
                parent,
                false
            )
        )
    }

    /**
     * Holds view references and passes them on to be bound to views.
     */
    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val currentSchool = schoolList[position]
        holder.bindData(
            schoolName = currentSchool.school_name,
            totalStudents = myContext.getString(
                R.string.total_students,
                currentSchool.num_of_sat_test_takers
            )
        )

        // on-click listener.
        holder.itemView.setOnClickListener {
            onClick.openDetailsFragment(currentSchool)
        }
    }
}

/**
 * Handles the binding of the data to the views.
 */
class SchoolViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val schoolName: TextView = view.findViewById(R.id.school_name)
    private val totalStudents: TextView = view.findViewById(R.id.tvTotalStudents)

    fun bindData(schoolName: String?, totalStudents: String?) {
        this.schoolName.text = schoolName
        this.totalStudents.text = totalStudents
    }
}

