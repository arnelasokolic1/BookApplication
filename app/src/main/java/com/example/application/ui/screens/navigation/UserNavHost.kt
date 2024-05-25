import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.application.ui.screens.LoginDestination
import com.example.application.ui.screens.LoginScreenWithTopBar
import com.example.application.ui.screens.ProfileDestination
import com.example.application.ui.screens.ProfileScreenWithTopBar
import com.example.application.ui.screens.RegistrationDestination
import com.example.application.ui.screens.RegistrationScreenWithTopBar
// import com.example.application.ui.screens.UserDashboardDestination
// import com.example.application.ui.screens.UserDashboardWithTopBar
import com.example.application.ui.screens.WelcomePageDestination
import com.example.application.ui.screens.WelcomePageWithTopBar

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun UserNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = WelcomePageDestination.route) {
        composable(route = WelcomePageDestination.route) {
            WelcomePageWithTopBar(
                context = navController.context,
                navigateToLogin = { navController.navigate(LoginDestination.route) }
            )
        }
        composable(route = RegistrationDestination.route) {
            RegistrationScreenWithTopBar(
                context = navController.context,
                navigateToLogin = { navController.navigate(LoginDestination.route) },
                navigateToProfilePage = { userId -> navController.navigate("${ProfileDestination.route}/$userId") }
            )
        }
        composable(
            route = ProfileDestination.routeWithArgs,
            arguments = listOf(navArgument(ProfileDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            ProfileScreenWithTopBar(navigateBack = { navController.navigateUp() })
        }
        composable(route = LoginDestination.route) {
            LoginScreenWithTopBar(
                navigateToRegister = { navController.navigate(RegistrationDestination.route) },
                navigateToProfilePage = { userId -> navController.navigate("${ProfileDestination.route}/$userId") },
                navigateToUserDashboard = { /* Define your UserDashboardDestination.route if necessary */ }
            )
        }

        // Uncomment and modify the below block if you want to add the user dashboard route
        /*
        composable(route = UserDashboardDestination.route) {
            UserDashboardWithTopBar(
                context = navController.context,
                navigateToLogin = { navController.navigate(LoginDestination.route) }
            )
        }
        */
    }
}
