package com.example.patient.presntation.features.patients.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.patient.domain.model.patiens.PatientRemoteModel
import com.example.patient.presntation.databinding.RowPatientBinding

class PatientsAdapter :
    ListAdapter<PatientRemoteModel,PatientsAdapter.PatientsViewHolder>(DiffCallback) {
    var indexLastSelected = -1
    inner class PatientsViewHolder(private val binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(model: com.example.patient.domain.model.patiens.PatientRemoteModel, position:Int){
                binding.model = model
                binding.cvPatients.setOnClickListener {
                    if (position != indexLastSelected){
                        if (indexLastSelected != -1){
                            getItem(position).selected = false
                            notifyItemChanged(indexLastSelected)
                        }
                        indexLastSelected = position
                        getItem(position).selected = true
                        notifyItemChanged(position)
                    }

                }
            }
    }
    private object DiffCallback : DiffUtil.ItemCallback<PatientRemoteModel>(){
        override fun areItemsTheSame(
            oldItem: PatientRemoteModel,
            newItem: PatientRemoteModel
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: PatientRemoteModel,
            newItem: PatientRemoteModel
        ): Boolean {
            return oldItem == newItem
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding = RowPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model,position)
    }
}