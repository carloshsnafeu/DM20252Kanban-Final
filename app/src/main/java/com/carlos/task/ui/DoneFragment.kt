package com.carlos.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlos.task.R
import com.carlos.task.data.model.Status
import com.carlos.task.data.model.Task
import com.carlos.task.databinding.FragmentDoneBinding
import com.carlos.task.ui.adapter.TaskAdapter

class DoneFragment : Fragment() {

    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecyclerViewTask()
        getTask()
    }

    private fun initListeners() {
        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fromTaskFragment)
        }
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext()) { task, option ->
            optionSelected(task, option)
        }
        with(binding.recyclerViewTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_BACK ->
                Toast.makeText(requireContext(), "Voltar", Toast.LENGTH_SHORT).show()
            TaskAdapter.SELECT_REMOVER ->
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            TaskAdapter.SELECT_EDIT ->
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            TaskAdapter.SELECT_DETAILS ->
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
            TaskAdapter.SELECT_NEXT ->
                Toast.makeText(requireContext(), "Próximo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getTask() {
        val taskList = listOf(
            Task("30", "Configurar Navigation Component e Safe Args", Status.DONE),
            Task("31", "Otimizar listas com DiffUtil e ListAdapter", Status.DONE),
            Task("32", "Habilitar ProGuard e shrinkResources para release", Status.DONE),
            )
        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
