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
import com.carlos.task.databinding.FragmentTodoBinding
import com.carlos.task.ui.adapter.TaskAdapter


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

    private fun initListeners() {
        binding.floatingActionButton2.setOnClickListener {
            // Aqui mantém o ID da action que existe no seu nav_graph
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
            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(
                    requireContext(),
                    "Removendo ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(
                    requireContext(),
                    "Editando ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(
                    requireContext(),
                    "Detalhes ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(
                    requireContext(),
                    "Próximo",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getTask() {
        val taskList = listOf(
            Task("0", "Configurar login social com Google/Facebook", Status.TODO),
            Task("1", "Criar animação para transição entre telas", Status.TODO),
            Task("2", "Implementar persistência local usando Room", Status.TODO),
            Task("3", "Criar função de busca com debounce", Status.TODO),
            Task("4", "Adicionar suporte a múltiplos idiomas (i18n)", Status.TODO),
            Task("5", "Criar tela de configurações com tema claro/escuro", Status.TODO),
            Task("6", "Implementar swipe para remover nas listas", Status.TODO),
            Task("7", "Adicionar paginador em listas grandes", Status.TODO),
            Task("8", "Criar lógica de ordenação por prioridade", Status.TODO),
            Task("9", "Criar gráfico simples com MPAndroidChart", Status.TODO),
            Task("10", "Sincronizar tarefas offline com Firebase", Status.TODO),
            Task("11", "Implementar notificações locais para tarefas", Status.TODO),
            Task("12", "Criar testes unitários para o TaskAdapter", Status.TODO),
            Task("13", "Adicionar botão de compartilhar tarefa", Status.TODO),
            Task("14", "Validar campos com TextInputLayout", Status.TODO)
        )

        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
