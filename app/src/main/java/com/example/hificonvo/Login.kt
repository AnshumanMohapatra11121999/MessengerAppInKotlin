package com.example.hificonvo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {
    private lateinit var edtEmail:EditText
    private  lateinit var edtPassWord:EditText
    private  lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtEmail=findViewById(R.id.edt_email)
        edtPassWord=findViewById(R.id.edt_Password)
        btnLogin=findViewById(R.id.btnLogin)
        btnSignUp=findViewById(R.id.btnSignUp)
        btnSignUp.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassWord.text.toString()
        login(email,password)

        }
    }

   private fun login(email:String,password:String) {
       mAuth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener(this) { task ->
               if (task.isSuccessful) {
                   // Sign in success, update UI with the signed-in user's information
                   val intent = Intent(this@Login, MainActivity::class.java)
                   finish()
                   startActivity(intent)

               } else {
                   Toast.makeText(
                       this@Login, "User Does Not Exist", Toast.LENGTH_SHORT).show()

               }
           }
   }
}
