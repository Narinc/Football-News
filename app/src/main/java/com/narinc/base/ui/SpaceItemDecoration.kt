package com.narinc.base.ui

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceItemDecoration : ItemDecoration {
    private val start: Int
    private val top: Int
    private val end: Int
    private val bottom: Int

    private constructor(space: Int) {
        start = space
        top = space
        end = space
        bottom = space
    }

    private constructor(horizontal: Int, vertical: Int) {
        start = horizontal
        top = vertical
        end = horizontal
        bottom = vertical
    }

    private constructor(start: Int, top: Int, end: Int, bottom: Int) {
        this.start = start
        this.top = top
        this.end = end
        this.bottom = bottom
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = bottom
        outRect.top = top
        outRect.left = start
        outRect.right = end
    }

    companion object {
        fun newInstance(context: Context, space: Int): SpaceItemDecoration {
            return SpaceItemDecoration(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    space.toFloat(),
                    context.resources.displayMetrics
                ).toInt()
            )
        }

        fun newInstance(context: Context): SpaceItemDecoration {
            val metrics = context.resources.displayMetrics
            val space =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, metrics).toInt()
            return SpaceItemDecoration(space)
        }

        fun newInstance(
            context: Context,
            horizontal: Int,
            vertical: Int
        ): SpaceItemDecoration {
            return SpaceItemDecoration(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    horizontal.toFloat(),
                    context.resources.displayMetrics
                ).toInt(),
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    vertical.toFloat(),
                    context.resources.displayMetrics
                ).toInt()
            )
        }

        fun newInstance(
            context: Context,
            start: Int,
            top: Int,
            end: Int,
            bottom: Int
        ): SpaceItemDecoration {
            return SpaceItemDecoration(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    start.toFloat(),
                    context.resources.displayMetrics
                ).toInt(), TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    top.toFloat(),
                    context.resources.displayMetrics
                ).toInt(), TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    end.toFloat(),
                    context.resources.displayMetrics
                ).toInt(), TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    bottom.toFloat(),
                    context.resources.displayMetrics
                ).toInt()
            )
        }
    }
}
