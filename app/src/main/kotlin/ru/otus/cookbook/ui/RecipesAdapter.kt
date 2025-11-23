package ru.otus.cookbook.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.otus.cookbook.data.RecipeListItem
import ru.otus.cookbook.databinding.VhRecipeCategoryBinding
import ru.otus.cookbook.databinding.VhRecipeItemBinding

class RecipesAdapter(val onRecipeClick: (Int) -> Unit):
    ListAdapter<RecipeListItem, RecyclerView.ViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType){
            VIEW_TYPE_CATEGORY -> {
                val binding = VhRecipeCategoryBinding.inflate(inflater, parent, false)
                RecipeCategoryViewHolder(binding)
            }
            VIEW_TYPE_RECIPE -> {
                val binding = VhRecipeItemBinding.inflate(inflater, parent, false)
                RecipeItemViewHolder(binding)
            }
            else -> throw RuntimeException("RuntimeException")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder){
            is RecipeCategoryViewHolder -> {
                val category = item as RecipeListItem.CategoryItem
                holder.binding.textViewCategory.text = category.category.name
            }
            is RecipeItemViewHolder -> {
                val recipeItem = item as RecipeListItem.RecipeItem
                with(holder){
                    binding.textBackground.text = recipeItem.title.first().toString()
                    binding.title.text = recipeItem.title
                    binding.description.text = recipeItem.description
                    Glide.with(binding.root.context)
                        .load(recipeItem.imageUrl)
                        .centerCrop()
                        .into(binding.imageViewRecipe)
                    binding.root.setOnClickListener { onRecipeClick(item.id) }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RecipeListItem.CategoryItem -> VIEW_TYPE_CATEGORY
            is RecipeListItem.RecipeItem -> VIEW_TYPE_RECIPE
        }
    }

    companion object{
        private const val VIEW_TYPE_CATEGORY = 1
        private const val VIEW_TYPE_RECIPE = 2
    }
}
