package ru.otus.cookbook.ui

import androidx.recyclerview.widget.DiffUtil
import ru.otus.cookbook.data.RecipeListItem

class RecipeDiffCallback: DiffUtil.ItemCallback<RecipeListItem>() {
    override fun areItemsTheSame(oldItem: RecipeListItem, newItem: RecipeListItem): Boolean {
        return when{
            oldItem is RecipeListItem.CategoryItem && newItem is RecipeListItem.CategoryItem ->
                oldItem.name == newItem.name
            oldItem is RecipeListItem.RecipeItem && newItem is RecipeListItem.RecipeItem ->
                oldItem.id == newItem.id
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: RecipeListItem, newItem: RecipeListItem): Boolean {
        return oldItem == newItem
    }
}
