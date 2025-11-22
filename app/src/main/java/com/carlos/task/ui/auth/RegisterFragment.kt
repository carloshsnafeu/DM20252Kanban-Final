package com.carlos.task.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.carlos.task.R
import com.carlos.task.databinding.FragmentRegisterBinding
import com.carlos.task.util.initToolbar
import com.carlos.task.util.showBottomSheet
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

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

        auth = FirebaseAuth.getInstance()

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

        binding.progressBar.isVisible = true
        registerUser(email, senha)
    }

    private fun registerUser(email: String, password: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.progressBar.isVisible = false
                        // Navegar para o HomeFragment
                        findNavController().navigate(R.id.action_global_homeFragment)
                    } else {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            requireContext(),
                            task.exception?.message ?: getString(R.string.error_generic),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } catch (e: Exception) {
            binding.progressBar.isVisible = false
            Toast.makeText(
                requireContext(),
                e.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
