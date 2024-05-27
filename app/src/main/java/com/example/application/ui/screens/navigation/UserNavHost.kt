import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.application.ui.screens.*

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
                navigateToUserDashboard = { navController.navigate(UserDashboardDestination.route) }
            )
        }
        composable(route = UserDashboardDestination.route) {
            UserDashboardWithTopBar(
                navigateToRegister = { navController.navigate(RegistrationDestination.route) },
                navigateToProfilePage = { userId -> navController.navigate("${ProfileDestination.route}/$userId") }
            ) { navController.navigate(WelcomePageDestination.route) }
        }
    }
}
