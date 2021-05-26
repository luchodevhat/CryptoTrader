package com.platzi.android.firestore.model.network

import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.platzi.android.firestore.model.Crypto
import com.platzi.android.firestore.model.User


const val CRYPTO_COLLECTION_NAME = "cryptos"
const val USERS_COLLECTION_NAME = "users"

class FireStoreService(val firebaseFirestore: FirebaseFirestore) {

    fun setDocument(data: Any, collectionName: String, id: String, callBack: CallBack<Void>) {
        firebaseFirestore.collection(collectionName).document(id).set(data)
            .addOnSuccessListener { callBack.onSuccess(null)}
                .addOnFailureListener { exception -> callBack.onFailed(exception) }
    }

    fun updateUser(user:User, callBack: CallBack<User>?) {
        firebaseFirestore.collection(USERS_COLLECTION_NAME).document(user.userName).update("cryptosList", user.cryptosList)
                .addOnSuccessListener { result ->
                    if(callBack != null)
                        callBack.onSuccess(user)
                    }.addOnFailureListener { exception -> callBack?.onFailed(exception) }
                }
    fun updateCrypto(crypto: Crypto) {
        firebaseFirestore.collection(CRYPTO_COLLECTION_NAME).document(crypto.getDocumentId()).update("available", crypto.available)
    }





    }






