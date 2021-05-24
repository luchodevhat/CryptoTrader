package com.platzi.android.firestore.model.network

import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore


const val CRYPTO_COLLECTION_NAME = "cryptos"
const val USERS_COLLECTION_NAME = "users"

class FireStoreService(val firebaseFirestore: FirebaseFirestore) {

    fun setDocument(data: Any, collectionName: String, id: String, callBack: CallBack<Void>) {
        firebaseFirestore.collection(collectionName).document(id).set(data)
            .addOnSuccessListener { callBack.onSuccess(null)}
            .addOnFailureListener( ) // working here

    }




}

private fun <TResult> Task<TResult>.addOnFailureListener(onFailed: Unit) {

}
