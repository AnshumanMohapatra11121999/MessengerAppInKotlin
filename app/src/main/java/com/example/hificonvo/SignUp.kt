package com.example.hificonvo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUp : AppCompatActivity() {
    private  lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private  lateinit var edtPassWord: EditText

    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth

    private lateinit var mDbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtName=findViewById(R.id.edt_name)
        edtEmail=findViewById(R.id.edt_email)
        edtPassWord=findViewById(R.id.edt_Password)
        btnSignUp=findViewById(R.id.btnSignUp)
        
        
        btnSignUp.setOnClickListener {
            val name=edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassWord.text.toString()
            signUp(name,email,password)

        }
    }
    private fun signUp(name:String,email:String, password:String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    // Sign in success, update UI with the signed-in user's information
                    if (task.isSuccessful) {
                        val intent = Intent(this@SignUp, MainActivity::class.java)
                        finish()
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this@SignUp, "SignUp attempt failed.Some Error occured", Toast.LENGTH_SHORT).show()

                    }
                }


            }


    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
    mDbRef =FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))

    }
}
