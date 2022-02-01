package com.cliabhach.terrapin.red.shell.details

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.State
import com.cliabhach.terrapin.red.shell.R
import kotlin.properties.Delegates

/**
 * This'll show a light highlight around the currently-selected item in the RecyclerView.
 */
class IdempotentHighlightDecoration : ItemDecoration() {

    private var offset by Delegates.notNull<Int>()

    /**
     * A drawing palette - this caches objects for faster execution of [onDrawOver].
     */
    private val palette = DecorationPalette()

    var currentHighlightId = -1L

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        if (parent.shouldBeHighlighted(view)) {
            offset = view.resources.getDimensionPixelSize(R.dimen.standard_padding)

            outRect.set(offset, offset, offset, offset)
        } else {
            outRect.setEmpty()
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        c.save()

        // TODO: Draw some kind of highlight around the currentHighlightId view

        c.restore()
    }

    // -- private methods

    /**
     * Check if we should mark a highlight around the given child.
     *
     * Only one element should have a highlight at any given time.
     */
    private fun RecyclerView.shouldBeHighlighted(child: View): Boolean {
        return currentHighlightId == getChildItemId(child)
    }

    // -- overrides so that we can use 'remove' to remove all other highlight decorations
    // from a RecyclerView, without needing to keep track of original object refs.

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IdempotentHighlightDecoration) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}

internal class DecorationPalette {
    // TODO: Add objects that'll be useful in onDraw/onDrawOver
}
