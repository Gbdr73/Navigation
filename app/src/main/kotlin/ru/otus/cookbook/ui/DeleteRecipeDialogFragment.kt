package ru.otus.cookbook.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.otus.cookbook.R
import androidx.navigation.fragment.findNavController
import kotlin.getValue

class DeleteRecipeDialogFragment: DialogFragment() {

    private val args by navArgs<DeleteRecipeDialogFragmentArgs>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val recipeTitle = args.recipeTitle
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.delete))
            .setMessage(getString(R.string.are_you_sure_want_to_delete, recipeTitle))
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                dismiss()
                setResult(true)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                dismiss()
                setResult(false)
            }
            .create()
    }

    private fun setResult(result: Boolean) {
        val navController = findNavController()
        navController.previousBackStackEntry?.savedStateHandle?.set(
            DIALOG_RESULT_KEY,
            result
        )
    }

    companion object {
        const val DIALOG_RESULT_KEY = "delete"
    }
}
