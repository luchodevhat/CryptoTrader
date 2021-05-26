package com.platzi.android.firestore.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.platzi.android.firestore.R
import com.platzi.android.firestore.model.User
import com.platzi.android.firestore.model.network.CallBack
import com.platzi.android.firestore.model.network.FireStoreService
import com.platzi.android.firestore.model.network.USERS_COLLECTION_NAME
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception


const val USERNAME_KEY = "username_key"

class LoginActivity : AppCompatActivity() {


    private val TAG = "LoginActivity"
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var firestoreService: FireStoreService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firestoreService = FireStoreService(FirebaseFirestore.getInstance())
    }


    fun onStartClicked(view: View) {
        view.isEnabled = false
        auth.signInAnonymously()
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        val userName = username.text.toString()
                        val user = User()
                        user.userName = userName
                        saveUserAndStartMainActivity(user, view)
                    } else {
                        showErrorMessage(view)
                        view.isEnabled = true
                    }
                }
    }

    private fun saveUserAndStartMainActivity(user: User, view: View) {
        firestoreService.setDocument(user, USERS_COLLECTION_NAME, user.userName, object : CallBack<Void> {

            override fun onSuccess(result: Void?) {
                startMainActivity(user.userName)

            }

            override fun onFailed(exception: Exception) {
                showErrorMessage(view)
                Log.e(TAG, "Error", exception)

            }


        })


    }


    private fun showErrorMessage(view: View) {
        Snackbar.make(view, getString(R.string.error_while_connecting_to_the_server), Snackbar.LENGTH_LONG)
                .setAction("Info", null).show()
    }

    private fun startMainActivity(username: String) {
        val intent = Intent(this@LoginActivity, TraderActivity::class.java)
        intent.putExtra(USERNAME_KEY, username)
        startActivity(intent)
        finish()
    }

}
