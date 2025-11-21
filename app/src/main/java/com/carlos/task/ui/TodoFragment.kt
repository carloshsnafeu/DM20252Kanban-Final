package com.carlos.task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlos.task.R
import com.carlos.task.data.model.Status
import com.carlos.task.data.model.Task
import com.carlos.task.databinding.FragmentTodoBinding
import com.fabio.task.ui.adapter.TaskAdapter


class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecyclerViewTask()
        getTask()
    }

    private fun initListeners(){
        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fromTaskFragment)
        }
    }

    private fun initRecyclerViewTask(){
        taskAdapter = TaskAdapter(requireContext()) {task, option -> optionSelected(task,option)}

        with(binding.recyclerViewTask){
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun optionSelected(task:Task, option: Int){
        when (option){
            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Próximo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTask() {
        val taskList = listOf(
            Task("0", "Refatorar arquitetura para MVVM", Status.TODO),
            Task("1", "Criar tela de onboarding com ViewPager", Status.TODO),
            Task("2", "Implementar autenticação com Firebase", Status.TODO),
            Task("3", "Adicionar validação de formulário no cadastro", Status.TODO),
            Task("4", "Configurar Crashlytics e Analytics", Status.TODO),
            Task("5", "Implementar modo escuro (Dark Mode)", Status.TODO),
            Task("6", "Criar componente de Empty State", Status.TODO),
            Task("7", "Escrever testes unitários para UseCases", Status.TODO),
            Task("8", "Habilitar cache offline com Room", Status.TODO),
            Task("9", "Configurar CI simples no GitHub Actions", Status.TODO),

            )
        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

