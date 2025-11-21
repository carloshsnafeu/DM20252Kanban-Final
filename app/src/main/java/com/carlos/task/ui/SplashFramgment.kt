package com.carlos.task.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.carlos.task.R
import com.carlos.task.databinding.FragmentSplashBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SplashFramgment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun checkAuth() {
        findNavController().navigate(R.id.action_splashFramgment_to_autentication)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}