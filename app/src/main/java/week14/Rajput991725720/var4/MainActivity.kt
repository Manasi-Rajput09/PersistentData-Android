package week14.Rajput991725720.var4


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import week14.Rajput991725720.var4.data.AppDb
import week14.Rajput991725720.var4.datastore.DataStoreManager
import week14.Rajput991725720.var4.ui.theme.FinalProjectRajput991725720Theme
import week14.Rajput991725720.var4.navigation.Screen
import week14.Rajput991725720.var4.ui.Screen1
import week14.Rajput991725720.var4.ui.Screen2
import week14.Rajput991725720.var4.ui.Screen3


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDb::class.java,
            "vote_db"
        ).fallbackToDestructiveMigration().build()

        val store = DataStoreManager(applicationContext)

        setContent {

            FinalProjectRajput991725720Theme {

                val nav = rememberNavController()


                NavHost(
                    navController = nav,
                    startDestination = Screen.Screen1.route
                ) {

                    composable(Screen.Screen1.route) {
                        Screen1(nav, store, db.voteDao())
                    }

                    composable(
                        route = Screen.Screen2.route,
                        arguments = listOf(
                            navArgument("id") { type = NavType.StringType },
                            navArgument("name") { type = NavType.StringType },
                            navArgument("age") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id") ?: ""
                        val name = backStackEntry.arguments?.getString("name") ?: ""
                        val age = backStackEntry.arguments?.getString("age") ?: ""
                        Screen2(nav, db.voteDao(), store, id, name, age)
                    }

                    composable(Screen.Screen3.route) {
                        Screen3(nav, db.voteDao(), store)
                    }
                }
            }
        }
    }
}