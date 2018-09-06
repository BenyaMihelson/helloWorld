package com.example.mykhalchenko_b.perfectlearn.features

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.mykhalchenko_b.perfectlearn.R
import com.example.mykhalchenko_b.perfectlearn.features.root.RootActivity
import com.example.mykhalchenko_b.perfectlearn.utils.Prefs
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener  {
    private val RC_SIGN_IN = 9001
    private val TAG = SignInActivity::class.simpleName
    private var mGoogleApiClient: GoogleApiClient? = null

    var prefs: Prefs? = null

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d("bett", "onConnectionFailed:" + connectionResult);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        prefs = Prefs(this)

        if (prefs!!.isRegistered){
            Log.d(TAG, "registered")
        }else{
            Log.d(TAG, "not registered")
        }

        updateUI(false)

        val gso: GoogleSignInOptions = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        btnLogin.setOnClickListener{
            val  signInIntent: Intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        btnLogout?.setOnClickListener(View.OnClickListener {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    object : ResultCallback<Status> {
                        override fun onResult(status: Status) {
                            updateUI(false)
                        }
                    })
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            updateUI(true)
        }
    }

    private fun saveUserInfo() {
        val acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)

        if (acct != null){
         prefs!!.isRegistered = true
        }

    }

    private fun updateUI(isLogin: Boolean) {
        if (isLogin) {
            btnLogin.visibility = View.GONE
            btnLogout.visibility = View.VISIBLE
            saveUserInfo()
            RootActivity.start(this)
        } else {
            btnLogin.visibility = View.VISIBLE
            btnLogout.visibility = View.GONE
        }
    }

    companion object {
        fun start(context: Context) {
            val intent =  Intent(context, SignInActivity::class.java)
            context.startActivity(intent)
        }
    }

}
