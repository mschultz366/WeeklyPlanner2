package com.example.weeklyplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainClassList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_class_list)

        val hdlFirstName = findViewById<EditText>(R.id.hFirstName)
        val hdlLastName = findViewById<EditText>(R.id.hLastName)
        val hdlPhoneNumber = findViewById<EditText>(R.id.hPhoneNumber)

        val hdlMonth = findViewById<Spinner>(R.id.hMonthSpinner)
        val hdlDay = findViewById<EditText>(R.id.hDay)
        val hdlYear = findViewById<EditText>(R.id.hYear)

        val hdlSwitch = findViewById<Switch>(R.id.hSwitch)
        val hdlCertificate = findViewById<Spinner>(R.id.hCertificateSpinner)
        val promptCertificate = findViewById<TextView>(R.id.certPrompt)
        val hdlMajor = findViewById<Spinner>(R.id.hMajorSpinner)
        val promptMajor = findViewById<TextView>(R.id.majorPrompt)

        val hdlButton = findViewById<Button>(R.id.hNextButton)

        hdlFirstName.requestFocus()

        hdlSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                hdlMajor.visibility = View.VISIBLE
                promptMajor.visibility = View.VISIBLE
                hdlCertificate.visibility = View.GONE
                promptCertificate.visibility = View.GONE
            } else {
                hdlMajor.visibility = View.GONE
                promptMajor.visibility = View.GONE
                hdlCertificate.visibility = View.VISIBLE
                promptCertificate.visibility = View.VISIBLE
            }
        }

        hdlButton.setOnClickListener {
            if(checkData()) {
                val concatBirthDate = hdlMonth.isSelected.toString() + "-" +
                        hdlDay.text.toString() + "-" + hdlYear.text.toString()

                val nextScreen = Intent(this@MainClassList, ChooseClass::class.java)
                nextScreen.putExtra("First Name", hdlFirstName.text.toString())
                nextScreen.putExtra("Last Name", hdlLastName.text.toString())
                nextScreen.putExtra("Phone Number", hdlPhoneNumber.text.toString())
                nextScreen.putExtra("Birth Date", concatBirthDate)

                if(hdlMajor.visibility == View.VISIBLE) {
                    nextScreen.putExtra("is Degree or Certificate", "Major")
                    nextScreen.putExtra("Degree/Certificate", hdlMajor.selectedItem.toString())
                } else {
                    nextScreen.putExtra("is Degree or Certificate", "Certificate")
                    nextScreen.putExtra("Degree/Certificate", hdlCertificate.selectedItem.toString())
                }

                startActivity(nextScreen)
            }
        }
    }

    private fun checkData(): Boolean {
        val hdlFirstName = findViewById<EditText>(R.id.hFirstName)
        val hdlLastName = findViewById<EditText>(R.id.hLastName)
        val hdlPhoneNumber = findViewById<EditText>(R.id.hPhoneNumber)
        val hdlDay = findViewById<EditText>(R.id.hDay)
        val hdlYear = findViewById<EditText>(R.id.hYear)

        if(hdlFirstName.text.toString().isEmpty()) {
            // Error
            hdlFirstName.error = "Invalid Entry"
            hdlFirstName.requestFocus()
            return false
        }

        if(hdlLastName.text.toString().isEmpty()) {
            // Error
            hdlLastName.error = "Invalid Entry"
            hdlLastName.requestFocus()
            return false
        }

        if(hdlPhoneNumber.text.toString().isEmpty()) {
            // Error
            hdlPhoneNumber.error = "Invalid Entry"
            hdlPhoneNumber.requestFocus()
            return false
        }

        if(hdlDay.text.toString().isEmpty()) {
            // Error
            hdlDay.error = "Invalid Entry"
            hdlDay.requestFocus()
            return false
        }

        if(hdlYear.text.toString().isEmpty()) {
            // Error
            hdlYear.error = "Invalid Entry"
            hdlYear.requestFocus()
            return false
        }

        return true
    }
}