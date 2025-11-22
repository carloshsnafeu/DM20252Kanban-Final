package com.carlos.task.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.carlos.task.R
import com.carlos.task.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SplashFramgment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed( { checkAuth() }, 3000 )

        auth = FirebaseAuth.getInstance()

    }

    private fun checkAuth() {
        try {
            val currentUser = auth.currentUser

            if (currentUser != null) {
                // Usuário logado → vai para Home
                findNavController().navigate(
                    resId = R.id.action_splashFramgment_to_homeFragment
                )
            } else {
                // Usuário não logado → vai para tela de autenticação
                findNavController().navigate(
                    resId = R.id.action_splashFramgment_to_autentication
                )
            }

        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                e.message.toString(),
                Toast.LENGTH_SHORT
            ).show()

            // Em caso de erro, volta para autenticação
            findNavController().navigate(
                resId = R.id.action_splashFramgment_to_autentication
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}