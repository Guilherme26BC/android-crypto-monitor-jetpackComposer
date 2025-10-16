package guilherme26bc.com.github.android_crypto_monitor_jetpackcomposer.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BitcoinScreen(
    modifier: Modifier = Modifier,
    // Instancia o ViewModel. O Compose vai gerenciar seu ciclo de vida.
    viewModel: BitcoinViewModel = viewModel()
) {
    // Coleta o estado do ViewModel. A tela será recomposta sempre que o estado mudar.
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        ToolbarMain()

        // Centraliza o conteúdo da tela
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Renderiza a UI de acordo com o estado atual
            when (val state = uiState) {
                is TickerUiState.Loading -> {
                    // Mostra um indicador de progresso enquanto carrega
                    CircularProgressIndicator()
                }
                is TickerUiState.Success -> {
                    // Mostra as informações quando os dados são recebidos
                    val ticker = state.ticker
                    QuoteInformation(
                        value = viewModel.formatCurrency(ticker.last),
                        date = viewModel.formatDate(ticker.date),
                        onRefreshClick = {
                            // Chama a função do ViewModel para atualizar os dados
                            viewModel.fetchTicker()
                        }
                    )
                }
                is TickerUiState.Error -> {
                    // Mostra uma mensagem de erro
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Erro ao carregar dados:\n${state.message}",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        RefreshButton(onClick = { viewModel.fetchTicker() })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarMain() {
    TopAppBar(
        title = {
            Text(
                text = "Crypto Monitor",
                color = Color.White
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun QuoteInformation(
    value: String,
    date: String,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth() // Ajustado para preencher a largura
            .padding(16.dp), // Adicionado padding
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Última atualização:")
        Text(text = date)

        Spacer(modifier = Modifier.height(24.dp))

        RefreshButton(onClick = onRefreshClick)
    }
}

@Composable
fun RefreshButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(120.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Usando cor do tema
            contentColor = Color.White
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text("Atualizar")
    }
}