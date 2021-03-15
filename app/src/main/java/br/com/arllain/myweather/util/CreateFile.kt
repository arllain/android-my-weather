package br.com.arllain.myweather.util

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File

object CreateFile {

    fun createSafeFile(file: File, fileContent: String, applicationContext : Context) {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        if (!file.exists()) {
            val encryptedFile = EncryptedFile.Builder(
                file,
                applicationContext,
                masterKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            encryptedFile.openFileOutput().use { writer ->
                writer.write(fileContent.toByteArray())
            }
        }
    }

}