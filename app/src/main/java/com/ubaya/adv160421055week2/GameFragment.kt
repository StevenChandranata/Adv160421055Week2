package com.ubaya.adv160421055week2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.adv160421055week2.databinding.FragmentGameBinding

import java.util.Random


class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private var number1: Int = 0
    private var number2: Int = 0
    private var correctAnswer: Int = 0
    private  var score: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGameBinding.inflate(
            inflater,
            container, false
        )
        return binding.root
    }
    private fun generateNumbers() {
        val random = Random()
        number1 = random.nextInt(10) + 1
        number2 = random.nextInt(10) + 1
        correctAnswer = number1 + number2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateNumbers()
        if (arguments != null) {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtPlayerTurn.text = "$playerName's Turn"
        }else{
            binding.txtPlayerTurn.text = "player's Turn"
        }

        binding.txtquestion.text = "$number1 + $number2"
        binding.btnSubmit.setOnClickListener {
            val playerAnswer = binding.txtAnswer.text.toString()
            if (playerAnswer.toIntOrNull() == correctAnswer) {
                score++
                generateNumbers()
                binding.txtquestion.text = "$number1 + $number2"
                binding.txtAnswer.text?.clear()
            }
            else{
                val action = GameFragmentDirections.actionResultFragment(score.toString())
                Navigation.findNavController(it).navigate(action)
                binding.txtAnswer.text?.clear()
                score = 0
            }
        }

    }

}