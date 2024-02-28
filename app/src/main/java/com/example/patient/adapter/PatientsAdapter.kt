package com.example.patient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patient.data.model.PatientRemoteModel
import com.example.patient.databinding.RowPatientBinding
import com.example.patient.features.patients.PatientViewModel

class PatientsAdapter(private val list:List<PatientRemoteModel>) :
    RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {
    inner class PatientsViewHolder(private val binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(model:PatientRemoteModel){

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding = RowPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        holder.bind(list[position])
    }
}