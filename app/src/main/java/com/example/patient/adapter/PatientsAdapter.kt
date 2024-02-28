package com.example.patient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patient.data.model.PatientRemoteModel
import com.example.patient.databinding.RowPatientBinding
import com.example.patient.features.patients.PatientViewModel

class PatientsAdapter(private val patients:List<PatientRemoteModel>) :
    RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {
    var indexLastSelected = -1
    inner class PatientsViewHolder(private val binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(model:PatientRemoteModel,position:Int){
                binding.cvPatients.setOnClickListener {
                    if (position != indexLastSelected){
                        if (indexLastSelected != -1){
                            patients[indexLastSelected].selected = false
                            notifyItemChanged(indexLastSelected)
                        }
                        indexLastSelected = position
                        patients[position].selected = true
                        notifyItemChanged(position)
                    }

                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding = RowPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return patients.size
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        holder.bind(patients[position],position)
    }
}