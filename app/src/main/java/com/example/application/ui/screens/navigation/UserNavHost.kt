import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.application.AddBookBar
import com.example.application.AddBookDestination
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
                navigateToAdmin = { navController.navigate("${UserDashboardDestination.route}/$it") },
                navigateToUserDashboard = { navController.navigate("${UserDashboardDestination.route}/$it") }
            )
        }
        composable(
            route = UserDashboardDestination.routeWithArgs,
            arguments = listOf(navArgument(UserDashboardDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            UserDashboardWithTopBar(
                navigateToAddBook = { navController.navigate(AddBookDestination.route) },
                navigateToProfilePage = { userId -> navController.navigate("${ProfileDestination.route}/$userId") },
                navigateToAdminUsersList = { navController.navigate(AdminUsersListDestination.route) },
                navigateToWelcomePage = { navController.navigate(WelcomePageDestination.route) }
            )
        }

        composable(route = AdminUsersListDestination.route) {
            AdminUsersListWithTopBar(
                navigateToUserDashboard = { navController.navigate(UserDashboardDestination.route) },

                    navigateBack = { navController.navigateUp() }

                )


        }


        composable(
            route = AdminDashboardDestination.routeWithArgs,
            arguments = listOf(navArgument(AdminDashboardDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            AdminDashboardScreenWithTopBar(
                navigateToAddBook = { navController.navigate(AddBookDestination.route) },
                navigateToProfilePage = { userId -> navController.navigate("${ProfileDestination.route}/$userId") },
                navigateToUserDashboard = { navController.navigate(UserDashboardDestination.route) }
            )
        }

        composable(route = AddBookDestination.route) {
            AddBookBar(
                navigateToUserDashboard = { navController.navigate(UserDashboardDestination.route) },
                navigateToRegister = { navController.navigate(RegistrationDestination.route) },
                navigateToAdminDashboard = { navController.navigate(AdminDashboardDestination.route) },
                        navigateBack = { navController.navigateUp() }
            )
        }
    }
}
