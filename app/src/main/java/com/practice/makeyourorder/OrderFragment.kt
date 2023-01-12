package com.practice.makeyourorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.practice.makeyourorder.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        // Setting the toolbar as action bar
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.makeAnOrderFab.setOnClickListener {
            val pizzaType = binding.pizzaChoiceRadioGroup.checkedRadioButtonId
            if (pizzaType != -1) {
                var text = when (pizzaType) {
                    R.id.margarita_radio_button -> "Пицца Маргарита"
                    R.id.carbonara_radio_button -> "Пицца Карбонара"
                    else -> "Пицца Пепперони"
                }
                if (binding.extraCheeseChip.isChecked) text += ", дополнительный сыр"
                if (binding.jalapenoChip.isChecked) text += " с перцем халапеньо"
                Snackbar.make(binding.makeAnOrderFab, text, Snackbar.LENGTH_LONG)
                    .setAction("Отмена") {
                        //Отмена действия
                        it.isActivated = false
                    }.setAnchorView(binding.makeAnOrderFab).show()
            } else {
                Toast.makeText(
                    activity,
                    "Пожалуйста, сначала выберите тип пиццы",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}