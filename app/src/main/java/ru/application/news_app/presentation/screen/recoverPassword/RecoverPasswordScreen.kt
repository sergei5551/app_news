import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.application.news_app.R
import ru.application.news_app.presentation.navigation.Screen
import ru.application.news_app.presentation.screen.recoverPassword.RecoverPasswordEvent
import ru.application.news_app.presentation.screen.recoverPassword.RecoverPasswordScreenState
import ru.application.news_app.presentation.screen.recoverPassword.RecoverPasswordScreenViewModel
import ru.application.news_app.presentation.ui.component.CustomTextField

@Composable
fun RecoverPasswordScreen(
    onNavigationTo: (Screen) -> Unit
) {
    val viewModel = viewModel<RecoverPasswordScreenViewModel>()
    RecoverPasswordView(
        state = viewModel.state,
        onNavigationTo = onNavigationTo,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun RecoverPasswordView(
    onNavigationTo: (Screen) -> Unit = {},
    state: RecoverPasswordScreenState = RecoverPasswordScreenState(),
    onEvent: (RecoverPasswordEvent) -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFAAE16),
                        Color(0xFFE1CF29),
                        Color(0xFFF0E268),
                        Color(0xFFFEF6A8)
                    ),
                    startY = 0f,
                    endY = 2200f
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Название
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.n),
                    contentDescription = "char_N",
                    modifier = Modifier
                        .size(79.dp, 270.dp)
                        .padding(top = 50.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.e),
                    contentDescription = "char_E",
                    modifier = Modifier
                        .size(62.dp, 273.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.w),
                    contentDescription = "char_W",
                    modifier = Modifier
                        .size(96.dp, 273.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.s),
                    contentDescription = "char_S",
                    modifier = Modifier
                        .size(67.dp, 273.dp)
                        .padding(top = 50.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.recover_password),
                contentDescription = "login_image",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 200.dp)
                    .size(150.dp)
            )
        }

        Column (
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(35.dp)
        ) {

            CustomTextField(
                value = state.email,
                onValueChange = {
                    onEvent(RecoverPasswordEvent.EmailUpdated(it))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.enter_email_for_recover))
                },
                modifier = Modifier.padding(top = 100.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(50.dp)
            ) {
                IconButton(
                    onClick = {
                        onNavigationTo(Screen.Login)
                    },
                    shape = RoundedCornerShape(size = 60.dp),
                    modifier = Modifier.size(60.dp)
                        .background(
                            color = Color(0xFFf39f18),
                            shape = RoundedCornerShape(60.dp)
                        ),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
                )
                {
                    Icon(
                        imageVector = Icons.Filled.ArrowLeft,
                        contentDescription = "Arrow",
                        modifier = Modifier.size(36.dp)
                    )
                }

                IconButton(
                    onClick = {

                    },
                    shape = RoundedCornerShape(size = 60.dp),
                    modifier = Modifier.size(60.dp)
                        .background(
                            color = Color(0xFFf39f18),
                            shape = RoundedCornerShape(60.dp)
                        ),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
                )
                {
                    Icon(
                        imageVector = Icons.Filled.ArrowRight,
                        contentDescription = "Arrow",
                        modifier = Modifier.size(36.dp)
                    )
                }


            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun RecoverPasswordScreenPreview() {
    RecoverPasswordView()
}