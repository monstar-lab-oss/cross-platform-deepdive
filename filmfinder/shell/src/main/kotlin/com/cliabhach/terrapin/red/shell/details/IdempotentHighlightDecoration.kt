package com.cliabhach.terrapin.red.shell.details

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.annotation.StyleableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.use
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.State
import com.cliabhach.terrapin.red.shell.R
import kotlin.math.roundToInt

/**
 * This'll show a light highlight around the currently-selected item in the RecyclerView.
 *
 * I'm heavily indebted to [androidx.recyclerview.widget.DividerItemDecoration] for
 * demonstrating what kinds of assumptions to avoid.
 */
class IdempotentHighlightDecoration(
    theme: Resources.Theme
) : ItemDecoration() {

    /**
     * A drawing palette - this caches objects for faster execution of [onDrawOver].
     *
     * Contains a [Paint] and a [Rect].
     */
    private val palette = DecorationPalette(theme)

    var currentHighlightId = -1L

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        if (parent.shouldBeHighlighted(view)) {
            with(palette) {
                outRect.set(offset, offset, offset, offset)
            }
        } else {
            outRect.setEmpty()
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        val left: Int
        val right: Int

        c.save()

        // Reduce left and right values if the RecyclerView's own padding takes priority
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            c.clipRect(
                left,
                parent.paddingTop,
                right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        // We only want to highlight one child, but that one might not be on screen right now.
        parent.children.filter {
            parent.shouldBeHighlighted(it)
        }.forEach { child ->
            with(palette) {
                // Code based on 'drawVertical' function in DividerItemDecoration
                parent.getDecoratedBoundsWithMargins(child, viewBounds)

                val yTranslation = child.translationY.roundToInt()

                val bottom: Int = viewBounds.bottom + yTranslation
                val top: Int = viewBounds.top + yTranslation + offset

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

// NB: Strictly speaking, we could offer individualised appearances for each view in the
// RecyclerView by querying style attributes, using different themes for different types
// of ViewHolder....but in practice this app doesn't need that. Maybe something to add
// much later on?
internal class DecorationPalette(
    theme: Resources.Theme
) {
    /**
     * The 'thickness' or 'width' of the highlight.
     *
     * Called offset for its use in [IdempotentHighlightDecoration.getItemOffsets].
     */
    internal val offset by lazy {
        theme.resources.getDimensionPixelSize(R.dimen.highlight_border)
    }

    internal val paint by lazy {
        val p = Paint()

        theme.obtainStyledAttributes(R.style.Keratin_Theme, paintStyleables).use {
            p.color = theme.attributeToColor(it.getColorOrThrow(0))
            p.style = Paint.Style.STROKE
            p.strokeWidth = (offset / 2).toFloat()
        }
        p
    }

    /**
     * The drawing bounds of a given View.
     */
    internal val viewBounds = Rect()

    internal val highlightBounds = Rect()

    private companion object {

        const val COLOR_SECONDARY: Int = android.R.attr.textColorSecondary

        @StyleableRes
        val paintStyleables = intArrayOf(
            androidx.appcompat.R.attr.colorPrimary,
        )

        // Code inspired by NavigationBarMenuView::createDefaultColorStateList in MDC 1.6a1
        fun Resources.Theme.attributeToColor(defaultColor: Int): Int {
            val value = TypedValue()

            val baseColor = if (resolveAttribute(COLOR_SECONDARY, value, true)) {
                ResourcesCompat.getColorStateList(resources, value.resourceId, this)
            } else {
                null
            }

            return baseColor?.defaultColor ?: defaultColor
        }
    }
}
