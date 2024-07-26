import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.animatePagerTransition(
    pagerState: PagerState,
    page: Int
): Modifier {
    return this.graphicsLayer {
        val pageOffSet = (
                (pagerState.currentPage - page) + pagerState
                    .currentPageOffsetFraction
                ).absoluteValue
        alpha = lerp(
            start = 1f,
            stop = 1f,
            fraction = 1f - pageOffSet.coerceIn(0f, 1f)
        )
        scaleX = lerp(
            start = 0.6f,
            stop = 1f,
            fraction = 1f - pageOffSet.coerceIn(0f, 1f)
        )
        scaleY = lerp(
            start = 0.6f,
            stop = 1f,
            fraction = 1f - pageOffSet.coerceIn(0f, 1f)
        )
    }
}