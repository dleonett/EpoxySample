package com.leonett.photofeed.ui.adapter

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.*
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Section006
import com.leonett.photofeed.ui.feature.account.SectionsController

@EpoxyModelClass(layout = R.layout.item_section_006)
abstract class Section006Model : EpoxyModelWithHolder<Section006Holder>() {

    @EpoxyAttribute
    var section: Section006? = null

    override fun bind(holder: Section006Holder) {
        section?.let {
            holder.gridLayoutManager.spanCount = it.spanCount
            holder.controller.setData(it.sections)
        }
    }
}

class Section006Holder : EpoxyHolder() {

    lateinit var rvMain: EpoxyRecyclerView
    lateinit var controller: SectionsController
    lateinit var gridLayoutManager: GridLayoutManager

    override fun bindView(itemView: View) {
        rvMain = itemView.findViewById(R.id.rvMain)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        controller = SectionsController()

        rvMain.apply {
            gridLayoutManager = GridLayoutManager(context, 2)
            layoutManager = gridLayoutManager
            setController(controller)
            addItemDecoration(
                EpoxyItemSpacingDecorator(
                    resources.getDimension(R.dimen.spacing_xxxxs).toInt()
                )
            )
        }
    }
}