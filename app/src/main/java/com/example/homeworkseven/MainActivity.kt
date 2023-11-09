package com.example.homeworkseven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homeworkseven.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var inputEditText: AppCompatEditText
    private lateinit var checkBox: CheckBox
    private lateinit var addFieldButton: AppCompatButton
    private lateinit var layoutContainer: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            inputEditText = etInput
            checkBox= chbxIsNumeric
            addFieldButton= btnAddField
            layoutContainer = container
        }

        var lastElementId: Int = addFieldButton.id

        addFieldButton.setOnClickListener {
            val userInput = getInputValue()
            userInput?.let {
                lastElementId = addField(checkBox.isChecked, it, lastElementId)
                clearDesign()
            }
        }
    }

    private fun getInputValue(): String? {
        val input = inputEditText.text.toString()
        if (input.isEmpty()) {
            showToast("Input is empty")
            return null
        }
        return input
    }

    private fun clearDesign() {
        inputEditText.text?.clear()
        checkBox.isChecked = false
    }

    private fun addField(isNumeric: Boolean, text: String, id: Int): Int {
        val appCompatEditText = AppCompatEditText(this)
        appCompatEditText.id = View.generateViewId()
        appCompatEditText.hint = text
        if (isNumeric) appCompatEditText.inputType = 0x00000002
        else appCompatEditText.inputType = 0x00000001
        layoutContainer.addView(appCompatEditText)

        val editTextParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )

        editTextParams.startToStart = layoutContainer.id
        editTextParams.endToEnd = layoutContainer.id
        editTextParams.topToBottom = id
        appCompatEditText.layoutParams = editTextParams

        return appCompatEditText.id
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}