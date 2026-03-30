# 📱 Zelus - Denúncia de Problemas Urbanos

<p align="center">
  <img src="app/src/main/res/drawable/zelus_app.png" width="200" alt="Logo Zelus"/>
</p>

<p align="center">
  <b>Aplicativo Android para denúncia de problemas urbanos com câmera e GPS</b>
</p>

---

## 👥 Equipe

| Nome | Matrícula |
|------|-----------|
| Cristiano da Costa Silva | 2415527 |
| Laís Dantas Ferreira | 2418863 |
| Bruno Clal de Almeida | 2425038 |
| Isadora Furtado Menezes | 2415625 |

**Disciplina:** N700 - Desenvolvimento para Plataformas Móveis

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Problema Identificado](#-problema-identificado)
- [Solução Proposta](#-solução-proposta)
- [Funcionalidades](#-funcionalidades)
- [Sensores Utilizados](#-sensores-utilizados)
- [Arquitetura e Estrutura](#-arquitetura-e-estrutura)
- [Tecnologias e Dependências](#-tecnologias-e-dependências)
- [Permissões do App](#-permissões-do-app)
- [Fluxo de Navegação](#-fluxo-de-navegação)
- [Como Executar](#-como-executar)
- [Estrutura de Arquivos](#-estrutura-de-arquivos)

---

## 📖 Sobre o Projeto

O **Zelus** é um aplicativo Android nativo desenvolvido em **Kotlin** com **Jetpack Compose** e **Material Design 3**, que permite aos cidadãos registrar denúncias de problemas urbanos de forma rápida e prática. O usuário pode tirar uma foto do problema, capturar automaticamente a localização via GPS e descrever a ocorrência, contribuindo para a melhoria da infraestrutura urbana da sua cidade.

O nome "Zelus" vem do grego e significa **zelo, cuidado** — representando o cuidado do cidadão com o espaço urbano onde vive.

---

## 🔍 Problema Identificado

Problemas urbanos como buracos nas ruas, postes quebrados, acúmulo de lixo e vazamentos de água são recorrentes nas cidades brasileiras. Muitas vezes, os moradores não possuem um canal simples e acessível para reportar essas situações aos órgãos responsáveis. A falta de comunicação eficiente entre cidadãos e o poder público resulta em demora na resolução desses problemas, impactando diretamente a qualidade de vida da população.

---

## 💡 Solução Proposta

O Zelus oferece uma plataforma mobile que permite ao cidadão:

- Fotografar o problema urbano diretamente pelo aplicativo utilizando a **câmera** do dispositivo.
- Capturar automaticamente o **endereço** da ocorrência via **GPS** (FusedLocationProvider + Geocoder).
- Classificar o tipo de problema (buraco, poste quebrado, lixo, vazamento, etc.).
- Descrever o problema em detalhes através de um formulário intuitivo.
- Consultar o histórico de todas as denúncias registradas.

---

## ✅ Funcionalidades

- **Tela Splash** — Tela inicial com logo, nome do app e botão de acesso.
- **Tela Home** — Menu principal com opções de nova denúncia, ver denúncias e sobre o app.
- **Nova Denúncia** — Captura de foto via câmera do dispositivo.
- **Localização Automática** — Detecção do endereço via GPS com exibição em tela.
- **Formulário de Denúncia** — Dados pessoais, tipo de problema (dropdown) e descrição textual.
- **Histórico de Denúncias** — Lista com foto, tipo, endereço e descrição de cada denúncia.
- **Tela Sobre** — Informações sobre o propósito e funcionalidades do aplicativo.
- **Navegação por Bottom Navigation Bar** com ícones e indicador visual de aba ativa.

---

## 📡 Sensores Utilizados

### 1. Câmera
- Utilizada para capturar a foto do problema urbano.
- Integrada via `ActivityResultContracts.TakePicturePreview()`.
- Permissão solicitada em tempo de execução (`android.permission.CAMERA`).

### 2. GPS (Localização)
- Utilizado para obter a localização atual do usuário e converter em endereço legível.
- Integrado via **FusedLocationProviderClient** (Google Play Services).
- Conversão de coordenadas (latitude/longitude) para endereço com **Geocoder**.
- Permissões: `ACCESS_FINE_LOCATION` e `ACCESS_COARSE_LOCATION`.

---

## 🏗 Arquitetura e Estrutura

O projeto segue uma arquitetura baseada em **Activities + Composables**, onde:

- **`MainActivity`** — Activity de entrada (Splash Screen) que direciona para o `FormularioActivity`.
- **`FormularioActivity`** — Activity principal que gerencia toda a navegação entre telas via estado (`telaAtual`). Controla o fluxo completo de denúncia: câmera → GPS → formulário → salvar.
- **Composables** — Cada tela é um `@Composable` independente e reutilizável.
- **Modelo de dados** — `data class Denuncia` com tipo, descrição, endereço e imagem (Bitmap).
- **Armazenamento** — Lista em memória (`mutableStateOf`) que funciona como banco de dados local durante a sessão.

---

## 🛠 Tecnologias e Dependências

| Tecnologia | Versão | Uso |
|---|---|---|
| **Kotlin** | — | Linguagem principal |
| **Jetpack Compose** | BOM 2024.02.00 | Framework de UI declarativa |
| **Material Design 3** | (via Compose) | Componentes visuais e padrões de design |
| **Activity Compose** | 1.8.2 | Integração de Activities com Compose |
| **Google Play Services Location** | 21.3.0 | Sensor GPS (FusedLocationProvider) |
| **AndroidX Core KTX** | 1.12.0 | Extensões Kotlin para Android |
| **AndroidX AppCompat** | 1.6.1 | Compatibilidade de componentes |
| **Material Components** | 1.11.0 | Componentes Material Design |
| **JUnit** | 4.13.2 | Testes unitários |
| **Espresso** | 3.5.1 | Testes de interface |

---

## 🔒 Permissões do App

```xml
<!-- Câmera do dispositivo -->
<uses-permission android:name="android.permission.CAMERA" />

<!-- GPS - Localização precisa -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

<!-- GPS - Localização aproximada -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
