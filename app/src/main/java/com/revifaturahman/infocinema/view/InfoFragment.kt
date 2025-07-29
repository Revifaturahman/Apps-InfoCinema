package com.revifaturahman.infocinema.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.revifaturahman.infocinema.R
import com.revifaturahman.infocinema.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.infoText.post {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
            binding.infoText.startAnimation(animation)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}