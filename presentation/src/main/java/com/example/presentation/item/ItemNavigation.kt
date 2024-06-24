package com.example.presentation.item


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.item.detail.ItemDetailLayout
import com.example.presentation.item.list.ItemListLayout


private object AppDestinations {
    const val ITEMS_ROUTE = "items"
    const val ITEM_ROUTE = "item"
    const val ITEM_DETAIL_ID_KEY = "id"
}

@Composable
fun ItemNavigation(
    startDestination: String = AppDestinations.ITEMS_ROUTE,
    navController: NavHostController = rememberNavController()
) {
    val actions = remember(navController) { ItemActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            AppDestinations.ITEMS_ROUTE
        ) {
            ItemListLayout(
                selectedItem = actions.selectedItem,
            )
        }
        composable(
            "${AppDestinations.ITEM_ROUTE}/{${AppDestinations.ITEM_DETAIL_ID_KEY}}",
            arguments = listOf(
                navArgument(AppDestinations.ITEM_DETAIL_ID_KEY) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            ItemDetailLayout(
                id = arguments.getString(AppDestinations.ITEM_DETAIL_ID_KEY) ?: "",
                navigateUp = actions.navigateUp
            )
        }
    }
}

private class ItemActions(
    navController: NavHostController
) {
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    val selectedItem: (String) -> Unit = { id: String ->
        navController.navigate("${AppDestinations.ITEM_ROUTE}/$id")
    }
}