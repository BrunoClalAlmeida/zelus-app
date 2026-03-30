// ============================================================
// Modelos.kt - Modelo de dados do app Zelus
// Define a estrutura da Denuncia com tipo, descricao,
// endereco (GPS) e imagem (Camera)
// ============================================================

package com.example.projectandroid.telas

import android.graphics.Bitmap

data class Denuncia(
    val tipo: String,
    val descricao: String,
    val endereco: String,
    val imagem: Bitmap?
)