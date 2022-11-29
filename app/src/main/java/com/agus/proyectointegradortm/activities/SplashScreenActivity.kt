package com.agus.proyectointegradortm.activities

import android.app.KeyguardManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.preferences.PreferencesManager
import java.util.concurrent.Executor


class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 1500 // 1 sec
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var executor: Executor
    private lateinit var callBack: BiometricPrompt.AuthenticationCallback
    private var keyguardManager: KeyguardManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // Escondemos la action bar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        //Pasamos al login
        Handler().postDelayed({
            if (MyApplication.preferences.getRemainingLogins() == 0) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                MyApplication.preferences.minusOneLogin()
                generateInstanceOfBiometricPrompt()
                authenticateWithBiometrics()
            }
        }, SPLASH_TIME_OUT)

    }


    private fun generateInstanceOfBiometricPrompt() {
        executor =  ContextCompat.getMainExecutor(this)
        callBack = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                finish()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                // Enviar al home directamente
                startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
                finish()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                finish()
            }
        }
        biometricPrompt =  BiometricPrompt(this, executor, callBack)
    }

    private fun authenticateWithBiometrics() {
        val promptInfo = BiometricPrompt.PromptInfo.Builder().apply {
            setTitle("Inicio de sesion")
            setDescription("Utilice la huella o su PIN")
            setNegativeButtonText("Cancelar")
            setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK)
        }.build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager?.let { manager ->
                if (manager.isKeyguardSecure) {
                    biometricPrompt.authenticate(promptInfo)
                }
            }
        } else {
            biometricPrompt.authenticate(promptInfo)
        }
    }
}