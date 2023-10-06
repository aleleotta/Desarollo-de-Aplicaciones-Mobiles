package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myfirstapp.databinding.CalculatorBinding
import com.example.myfirstapp.databinding.LoginBinding
import com.example.myfirstapp.databinding.PauseBinding
import com.example.myfirstapp.databinding.WelcomeBinding

class MainActivity : AppCompatActivity() {
    private lateinit var login: LoginBinding
    private lateinit var welcome: WelcomeBinding
    private lateinit var pause: PauseBinding
    private lateinit var calculator: CalculatorBinding
    private lateinit var currentScreen: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login = LoginBinding.inflate(layoutInflater)
        currentScreen = login
        setContentView(login.root)
        val actualUser: String = "aleleotta"
        val actualPassword : String = "1234"
        login.loginButton.setOnClickListener {
            var user: String = login.userField.text.toString()
            var password: String = login.passwordField.text.toString()
            if (user == actualUser && password == actualPassword) {
                Log.d(":::TAG", "Pressed and login should work.")
                welcome = WelcomeBinding.inflate(layoutInflater)
                currentScreen = welcome
                setContentView(welcome.root)
                welcome.calcTrigger.setOnClickListener { //Calculator button is pressed.
                    calculator = CalculatorBinding.inflate(layoutInflater)
                    currentScreen = calculator
                    setContentView(calculator.root)
                }
            } else {
                Log.d(":::TAG", "Pressed but login failed.")
                val toast = Toast.makeText(this, "The username or password are incorrect.", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(currentScreen is LoginBinding) { //The condition checks which was the most recent view displayed on screen
            setContentView((currentScreen as LoginBinding).root) //LOGIN
        } else if(currentScreen is WelcomeBinding) {
            setContentView((currentScreen as WelcomeBinding).root) //WELCOME
        } else if(currentScreen is CalculatorBinding) {
            setContentView((currentScreen as CalculatorBinding).root) //CALCULATOR
        }
    }

    override fun onPause() {
        super.onPause()
        pause = PauseBinding.inflate(layoutInflater) //Goes on pause once the app is put on background.
        setContentView(pause.root)
    }

}