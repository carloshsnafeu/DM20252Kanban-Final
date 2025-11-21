package com.carlos.task.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.carlos.task.R
import com.carlos.task.databinding.FragmentRegisterBinding
import com.carlos.task.util.initToolbar
import com.carlos.task.util.showBottomSheet

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val email = binding.etEmail.text.toString().trim()
        val senha = binding.etPassword.text.toString().trim()

        if (email.isBlank()) {
            showBottomSheet(message = getString(R.string.email_empty_register_fragment))
            return
        }

        if (senha.isBlank()) {
            showBottomSheet(message = getString(R.string.password_empty_register_fragment))
            return
        }

        findNavController().navigate(R.id.action_global_homeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
