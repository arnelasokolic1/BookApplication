import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.application.AddBookDestination
import com.example.application.ui.screens.*
import com.example.application.ui.screens.AdminDashboardDestination.userIdArg

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
                navigateToAdmin = { navController.navigate("${UserDashboardDestination.route}/$it") },
                navigateToUserDashboard = { navController.navigate("${UserDashboardDestination.route}/$it") }
            )
        }
        // staviti rutu u navigate()
        //kreirati AdmindASHBOARD RUTU
        composable(
            route = UserDashboardDestination.routeWithArgs,
            arguments = listOf(navArgument(UserDashboardDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            UserDashboardWithTopBar(
                navigateToRegister = { navController.navigate(RegistrationDestination.route) },
                navigateToProfilePage = { userId -> navController.navigate("${ProfileDestination.route}/$userId") },
                navigateToWelcomePage = { navController.navigate(WelcomePageDestination.route) })


        }

        composable(
            route = AddBookDestination.route) {
                type = NavType.IntType
            })
        ) {
            AddBookWithTopBar(
                navigateToRegister = { navController.navigate(RegistrationDestination.route) },
                navigateToAdminDashboard = { navController.navigate("${UserDashboardDestination.route}/$it") }
                navigateToWelcomePage = { navController.navigate(WelcomePageDestination.route) })

        }
    }

}