package com.practice.makeyourorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        // Setting the toolbar as action bar
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val fab = view.findViewById<FloatingActionButton>(R.id.make_an_order_fab)
        fab.setOnClickListener {
            val pizzaChoiceRadioGroup = view.findViewById<RadioGroup>(R.id.pizza_choice_radio_group)
            val pizzaType = pizzaChoiceRadioGroup.checkedRadioButtonId
            if (pizzaType != -1) {
                var text = when (pizzaType) {
                    R.id.margarita_radio_button -> "Пицца Маргарита"
                    R.id.carbonara_radio_button -> "Пицца Карбонара"
                    else -> "Пицца Пепперони"
                }
                val extraCheeseChip = view.findViewById<Chip>(R.id.extra_cheese_chip)
                val extraJalapenoChip = view.findViewById<Chip>(R.id.jalapeno_chip)
                if (extraCheeseChip.isChecked) text += ", дополнительный сыр"
                if (extraJalapenoChip.isChecked) text += " с перцем халапеньо"
                Snackbar.make(fab, text, Snackbar.LENGTH_LONG).setAction("Отмена") {
                    //Отмена действия
                    it.isActivated = false
                }.setAnchorView(fab).show()
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
}