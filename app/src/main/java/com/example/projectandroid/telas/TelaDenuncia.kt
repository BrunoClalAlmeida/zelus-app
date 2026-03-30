// ============================================================
// TelaDenuncia.kt - Telas do fluxo de denuncia
// Inclui: captura de foto (Camera), localizacao (GPS)
// e formulario de descricao com dropdown de tipo de problema
// ============================================================

package com.example.projectandroid.telas

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectandroid.R

/**
 * Tela inicial do fluxo de nova denúncia.
 * Apresenta a área para visualizar a imagem capturada ou o status da câmera.
 *
 * @param imagem Imagem (Bitmap) capturada pelo usuário, se existir.
 * @param onConfirmarClick Ação disparada para avançar no fluxo (Confirmar Localização).
 * @param onRefazerFotoClick Ação disparada para descartar a foto atual e tentar novamente.
 * @param paddingBarra Padding necessário para evitar sobreposição com a barra de navegação (BottomBar).
 */
@Composable
fun TelaNovaDenuncia(imagem: Bitmap?, onConfirmarClick: () -> Unit, onRefazerFotoClick: () -> Unit, paddingBarra: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Fundo decorativo verde claro com bordas arredondadas no topo
        Surface(shape = RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp), color = Color(0xFFF3FFF5), modifier = Modifier.fillMaxSize().padding(top = 100.dp)) {}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = paddingBarra.calculateBottomPadding() + 16.dp)
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título da tela
            Text("Nova denúncia", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(30.dp))
            
            // Área de exibição da foto (ou um placeholder de "Câmera Pronta")
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF3B3253)),
                contentAlignment = Alignment.Center
            ) {
                if (imagem != null) Image(bitmap = imagem.asImageBitmap(), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                else Text("Câmera pronta", color = Color.White)
            }
            Spacer(modifier = Modifier.height(24.dp))
            
            // Botão principal para confirmar a localização e prosseguir
            Button(onClick = onConfirmarClick, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7EAB0)), shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth(0.6f).height(50.dp)) {
                Text("Confirmar localização", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            
            // Botão secundário para refazer a foto (com borda/Outlined)
            OutlinedButton(onClick = onRefazerFotoClick, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth(0.6f).height(50.dp), border = BorderStroke(2.dp, Color(0xFF13C69D))) {
                Icon(Icons.Default.Refresh, contentDescription = null, tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Refazer Foto", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * Tela intermediária do fluxo de denúncia para confirmar o endereço de forma visual (mapa).
 *
 * @param endereco Endereço completo formatado a ser exibido para o usuário.
 * @param ruaTitle Nome principal da rua para o cabeçalho.
 * @param onProsseguirClick Ação disparada para seguir para a etapa de preenchimento dos detalhes da denúncia.
 * @param paddingBarra Padding da barra inferior.
 */
@Composable
fun TelaLocalizacao(endereco: String, ruaTitle: String, onProsseguirClick: () -> Unit, paddingBarra: PaddingValues) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(48.dp))
        
        // Cabeçalho exibindo ícone de localização e a rua atual
        Row(modifier = Modifier.padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Black)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = ruaTitle, fontSize = 22.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, color = Color.Black, textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(30.dp))

        // Painel branco de fundo ocupando o resto da tela
        Surface(modifier = Modifier.fillMaxWidth().weight(1f), shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp), color = Color.White) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .padding(bottom = paddingBarra.calculateBottomPadding() + 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Card mostrando o endereço completo detalhado
                Surface(modifier = Modifier.fillMaxWidth().height(60.dp), shape = RoundedCornerShape(20.dp), color = Color(0xFFF3FFF5), border = BorderStroke(2.dp, Color(0xFF13C69D))) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(Color(0xFFEFFFF1)), contentAlignment = Alignment.Center)
                        { Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null, tint = Color.Black) }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(endereco, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Imagem estática simulando um mapa do local
                Box(modifier = Modifier.fillMaxWidth().height(350.dp).clip(RoundedCornerShape(30.dp)).background(Color(0xFFE0E0E0)), contentAlignment = Alignment.Center) {
                    Image(painter = painterResource(id = R.drawable.mapa_), contentDescription = "Mapa Detectado", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botão para avançar para a última etapa do fluxo
                Button(onClick = onProsseguirClick, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF13C69D)), modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(20.dp)) {
                    Text("Prosseguir para denúncia", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}

/**
 * Tela final do fluxo de denúncia. Exibe o formulário que coleta informações do usuário
 * e os detalhes do problema ocorrido.
 *
 * @param onEnviarClick Ação de finalizar/enviar a denúncia. Recebe como parâmetro o Tipo e a Descrição do problema.
 * @param paddingBarra Padding da barra inferior.
 */
@Composable
fun TelaDescricaoDenuncia(onEnviarClick: (String, String) -> Unit, paddingBarra: PaddingValues) {
    // Variáveis de estado para salvar os dados preenchidos no formulário (Dados pessoais)
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    // Variáveis de estado para controlar o tipo de problema escolhido no Menu (Dropdown)
    var tipoProblemaSelecionado by remember { mutableStateOf("SELECIONE O TIPO...") }
    var menuExpandido by remember { mutableStateOf(false) }
    val opcoesDeProblema = listOf("BURACO NA RUA", "POSTE QUEBRADO", "LIXO NA RUA", "VAZAMENTO DE ÁGUA", "OUTROS")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = paddingBarra.calculateBottomPadding())
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        // Cabeçalho da tela de descrição
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = Color.Black)
            Spacer(modifier = Modifier.width(10.dp))
            Text("Descrição da denúncia", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
        Spacer(modifier = Modifier.height(30.dp))

        // Sessão 1: Bloco de preenchimento dos "Dados Pessoais"
        Surface(shape = RoundedCornerShape(16.dp), color = Color.White, modifier = Modifier.fillMaxWidth()) {
            Column {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)).background(Color(0xFF13C69D)).padding(12.dp)) {
                        Text("DADOS PESSOAIS", color = Color.White, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
                    }
                    Spacer(modifier = Modifier.weight(0.5f))
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("NOME:") }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Transparent, focusedBorderColor = Color.LightGray))
                OutlinedTextField(value = telefone, onValueChange = { telefone = it }, label = { Text("TELEFONE:") }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Transparent, focusedBorderColor = Color.LightGray))
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("EMAIL:") }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Transparent, focusedBorderColor = Color.LightGray))
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Sessão 2: Seleção do "Tipo" de problema usando um menu expansível (DropdownMenu)
        Surface(shape = RoundedCornerShape(16.dp), color = Color.White, modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.clickable { menuExpandido = true }.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("TIPO:", fontWeight = FontWeight.Bold, color = Color.Gray, fontFamily = FontFamily.Monospace)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(tipoProblemaSelecionado, color = Color(0xFF13C69D), fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Escolher Problema", tint = Color.Black)
                    }
                }
                DropdownMenu(expanded = menuExpandido, onDismissRequest = { menuExpandido = false }, modifier = Modifier.background(Color.White)) {
                    opcoesDeProblema.forEach { opcao ->
                        DropdownMenuItem(
                            text = { Text(opcao, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold) },
                            onClick = { tipoProblemaSelecionado = opcao; menuExpandido = false }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Sessão 3: Campo de texto livre para detalhar a "Descrição" do problema
        Surface(shape = RoundedCornerShape(16.dp), color = Color.White, modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                placeholder = { Text("Descreva o problema em detalhes...", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth().height(180.dp).padding(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Transparent, focusedBorderColor = Color.Transparent),
                maxLines = 8
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        // Botão final que submete todas as informações capturadas
        Button(
            onClick = { onEnviarClick(tipoProblemaSelecionado, descricao) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF13C69D)),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Enviar denúncia", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}