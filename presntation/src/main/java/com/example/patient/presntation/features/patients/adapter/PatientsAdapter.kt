package com.example.patient.presntation.features.patients.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patient.presntation.databinding.RowPatientBinding

class PatientsAdapter(private val patients:List<com.example.patient.domain.model.patiens.PatientRemoteModel>) :
    RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {
    var indexLastSelected = -1
    inner class PatientsViewHolder(private val binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(model: com.example.patient.domain.model.patiens.PatientRemoteModel, position:Int){
                binding.model = model
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