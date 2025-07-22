package com.example.cinestream.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.cinestream.R
import com.example.cinestream.databinding.FragmentCastInfoBinding
import com.example.cinestream.databinding.FragmentHomeBinding
import com.example.cinestream.viewmodel.DetailCastViewModel
import java.text.SimpleDateFormat
import java.util.*

class CastInfoFragment : Fragment() {

    private var _binding: FragmentCastInfoBinding? =null
    private val binding get() = _binding!!

    private val viewModel: DetailCastViewModel by viewModels({ requireActivity() })

    var birthday: String =""
    var place_of_birth : String =""
    var gender: String = ""
    var biografy: String = ""
    var birth: String = ""
    var also_known_as: List<String>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCastInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val personId = arguments?.getInt("PERSON_ID") ?: return
        viewModel.fetchDetailCast(personId)

        viewModel.detailCast.observe(viewLifecycleOwner) { detail ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${detail?.profile_path}")
                .into(binding.imgPoster)

            birthday = detail?.birthday.toString()
            place_of_birth = detail?.place_of_birth.toString()

            val layoutGender = binding.layout
            if (detail?.gender != 1){
                gender = "Male"
                layoutGender.setBackgroundColor(Color.parseColor("#E3F2FD"))
            }else{
                layoutGender.setBackgroundColor(Color.parseColor("#FCE4EC"))
                gender = "Female"
            }
            also_known_as = detail?.also_known_as
            birth = detail?.birthday.toString()
            biografy = detail?.biography.toString()
            val alsoKnownAs = detail?.also_known_as?.joinToString(", ") ?: ""

            binding.tvAge.text = "${hitungUmur(detail?.birthday ?: "")} Year"
            binding.tvBirthday.text = "Birthday: ${birthday}"
            binding.tvPlaceOfBirth.text = "Place of Birth: ${place_of_birth}"
            binding.tvGender.text = "Gender: ${gender}"
            binding.tvKnownAs.text = "Known As: ${alsoKnownAs}"
            binding.tvBiografy.text = biografy
            binding.tvBirth.text = birth
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun hitungUmur(tanggalLahirStr: String): Int {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val tanggalLahir = sdf.parse(tanggalLahirStr)
            val dob = Calendar.getInstance()
            val today = Calendar.getInstance()
            dob.time = tanggalLahir!!

            var umur = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                umur--
            }
            umur
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

}