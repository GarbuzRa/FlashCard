package com.example.flashcard.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flashcard.data.remote.User
import com.example.flashcard.databinding.FragmentRegistationBinding
import com.example.flashcard.domain.model.UserDomain
import com.example.flashcard.presentation.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistationFragment : Fragment() {
    private lateinit var binding: FragmentRegistationBinding
    private val viewModel by viewModel<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener{
            registerTest()
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.responce.observe(viewLifecycleOwner) {userResponce ->
            userResponce?.let {
                if (it.isSuccess) {
                    val userData = it.getOrNull()
                    Toast.makeText(requireContext(), "Пользователь зарегестрирован ${userData}", Toast.LENGTH_SHORT).show()
                } else {
                    val exception = it.exceptionOrNull()
                    Toast.makeText(requireContext(), "Ошибка ${exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun register() {
        val password = binding.RegistrPassword.text.toString()
        val confirmPassword = binding.ConfirmPassword.text.toString()
        val email = binding.RegistrEmail.text.toString()

        if (password == confirmPassword) {
            val user = UserDomain(email, password)
            viewModel.registerUser(user)
        } else {
            Toast.makeText(requireContext(), "Пальчики толстые, да?)) Сложно попасть по клавишам?)))", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerTest() {
        val password = "PSW"
        val confirmPassword = "SWP"
        val email = "example@mail.com"

        if (password == confirmPassword) {
            val user = UserDomain(email, password)
            viewModel.registerUser(user)
        } else {
            Toast.makeText(requireContext(), "Пальчики толстые, да?)) Сложно попасть по клавишам?)))", Toast.LENGTH_SHORT).show()
        }
    }
}