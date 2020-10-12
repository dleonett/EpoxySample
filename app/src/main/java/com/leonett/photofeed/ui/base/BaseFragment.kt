package com.leonett.photofeed.ui.base

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.util.LoaderDialog

abstract class BaseFragment : Fragment() {

    protected abstract val layoutId: Int
    private var loaderDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initVars()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        restoreState(savedInstanceState)
        observeViewModels()
    }

    abstract fun initVars()

    abstract fun initViews(view: View)

    protected open fun restoreState(savedInstanceState: Bundle?) {}

    protected open fun observeViewModels() {}

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showToast(resId: Int) {
        showToast(getString(resId))
    }

    fun showSnackbar(view: View, resId: Int) {
        showSnackbar(view, getString(resId))
    }

    fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.gray_extra_dark))
            .show()
    }

    fun showProgressDialog(resId: Int, cancelable: Boolean = false) {
        showProgressDialog(getString(resId), cancelable)
    }

    fun showProgressDialog(message: String, cancelable: Boolean = false) {
        loaderDialog = LoaderDialog.create(requireContext(), message, cancelable)
        loaderDialog?.show()
    }

    fun hideProgressDialog() {
        loaderDialog?.dismiss()
    }

    fun hideKeyboard() {
        val view = requireActivity().currentFocus
        view?.let { v ->
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun openUrl(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

}