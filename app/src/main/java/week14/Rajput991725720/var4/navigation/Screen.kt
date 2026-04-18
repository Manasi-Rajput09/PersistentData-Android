package week14.Rajput991725720.var4.navigation

sealed class Screen(val route: String) {
    object Screen1 : Screen("screen1")
    object Screen2 : Screen("screen2/{id}/{name}/{age}") {
        fun createRoute(id: String, name: String, age: String) = "screen2/$id/$name/$age"
    }
    object Screen3 : Screen("screen3")
}