package br.com.arllain.myweather.util

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File

object ReadFile {

    fun readSafeFile(file: File, applicationContext: Context): String {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        var result = ""
        if (file.exists()) {
            val encryptedFile = EncryptedFile.Builder(
                file,
                applicationContext,
                masterKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            encryptedFile.openFileInput().use { inputStream ->
                result = inputStream.readBytes().decodeToString()
            }
        }
        return result
    }

}