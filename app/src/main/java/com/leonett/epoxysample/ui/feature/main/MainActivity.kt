package com.leonett.epoxysample.ui.feature.main

import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyItemSpacingDecorator
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.GenericItem
import com.leonett.epoxysample.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
        MainController.OnInteractionListener {

    private lateinit var mainController: MainController

    override val contentViewId: Int
        get() = R.layout.activity_main

    override fun initVars() {

    }

    override fun initViews() {
        setupRecyclerView()
        populateRecyclerView()
    }

    private fun setupRecyclerView() {
        mainController = MainController()
        mainController.setOnItemClickListener(this)

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(mainController)
            addItemDecoration(EpoxyItemSpacingDecorator(resources.getDimension(R.dimen.spacing_sm).toInt()))
        }
    }

    private fun populateRecyclerView() {
        mainController.setData(getString(R.string.header_text), GenericItem.generateItemsList())
    }

    override fun onItemClickListener(item: GenericItem) {
        showToast(item.title ?: "")
    }
}
