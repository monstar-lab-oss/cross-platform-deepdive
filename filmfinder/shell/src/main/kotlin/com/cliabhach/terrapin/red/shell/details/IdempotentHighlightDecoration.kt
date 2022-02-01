package com.cliabhach.terrapin.red.shell.details

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.State
import com.cliabhach.terrapin.red.shell.R
import kotlin.math.roundToInt
import kotlin.properties.Delegates

/**
 * This'll show a light highlight around the currently-selected item in the RecyclerView.
 *
 * I'm heavily indebted to [androidx.recyclerview.widget.DividerItemDecoration] for
 * demonstrating what kinds of assumptions to avoid.
 */
class IdempotentHighlightDecoration : ItemDecoration() {

    private var offset by Delegates.notNull<Int>()

    /**
     * A drawing palette - this caches objects for faster execution of [onDrawOver].
     *
     * Contains a [Paint] and a [Rect].
     */
    private val palette = DecorationPalette()

    var currentHighlightId = -1L

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        if (parent.shouldBeHighlighted(view)) {
            offset = view.resources.getDimensionPixelSize(R.dimen.highlight_border)

            outRect.set(offset, offset, offset, offset)
        } else {
            outRect.setEmpty()
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        val left: Int
        val right: Int

        c.save()

        // TODO: Figure out padding/margin
        left = 0
        right = parent.width

        // We only want to highlight one child, but that one might not be on screen right now.
        parent.children.filter {
            parent.shouldBeHighlighted(it)
        }.forEach { child ->
            with(palette) {
                // Code based on 'drawVertical' function in DividerItemDecoration
                parent.getDecoratedBoundsWithMargins(child, viewBounds)

                val bottom: Int = viewBounds.bottom + child.translationY.roundToInt()
                val top: Int = bottom - offset

                highlightBounds.set(left, top, right, bottom)

                c.drawRect(highlightBounds, paint)
            }
        }

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
    internal val paint by lazy {
        Paint().also {
            it.color = Color.GREEN
        }
    }

    /**
     * The drawing bounds of a given View.
     */
    internal val viewBounds = Rect()

    internal val highlightBounds = Rect()
}
