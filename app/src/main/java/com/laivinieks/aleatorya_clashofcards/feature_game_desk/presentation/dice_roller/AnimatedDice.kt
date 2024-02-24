package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.dice_roller

import android.util.Log
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Cubic
import androidx.graphics.shapes.RoundedPolygon
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.ui.theme.diceTextColor


@Composable
fun AnimatedDice(rolling: Boolean, items: List<Int>, selected: (Int) -> Unit) {
    val rotationTransition = rememberInfiniteTransition(label = "infRot")
    val rotation by rotationTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (rolling) 3600f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3600, easing = EaseOutQuart),
            repeatMode = RepeatMode.Reverse
        ), label = "dice roll"
    )

    Box(modifier = Modifier.size(150.dp)) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .offset { IntOffset(0, 120.dp.roundToPx()) }
                .scale(scaleX = 0.6f, scaleY = 0.25f)


                .blur(
                    radius = 100.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded,
                )
                .background(Color.Black.copy(alpha = .5f))
        )
        Dice(
            rotationY = if (rolling) rotation else 0f, selected = selected, items = items
        )
    }

}

@Composable
fun Dice(
    rotationY: Float,
    items: List<Int>,
    selected: (Int) -> Unit
) {
    val diceSize = 150.dp

    Box(
        modifier = Modifier
            .size(diceSize)
            .graphicsLayer(
                rotationY = rotationY,
                transformOrigin = TransformOrigin.Center
            ),
        contentAlignment = Alignment.Center
    ) {

        Face(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            rotation = rotationY,
            selected = selected,
            list = items
        )

    }
}

@Composable
fun Face(modifier: Modifier = Modifier, rotation: Float, list: List<Int>, selected: (Int) -> Unit) {

    var number by remember { mutableIntStateOf(list.first()) }
    var isRolled by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(rotation) {
        if (rotation > 90 && rotation < 3510 && rotation.toInt() % 360 < 90) {
            number = list.random()
            isRolled = true
        }
        if (isRolled && rotation == 0f) {
            selected(number)
            isRolled = false
        }

    }

    val hexagon = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.25f)
        )
    }
    val clip1 = remember(hexagon) {
        RoundedPolygonShape(polygon = hexagon)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .rotate(90f)
                .clip(clip1)
                .size(150.dp)
                .zIndex(5f)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.medieval_paper_backgrund_2,
                ),

                contentScale = ContentScale.FillBounds, contentDescription = ""
            )
            Text(
                color = diceTextColor,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 80.sp),
                modifier = Modifier
                    .rotate(-90f)
                    .offset(x = (-25).dp, y = 50.dp)
                    .alpha(if (rotation < 3500 && rotation % 360 > 180) 0f else 1f),
                text = number.toString(),
            )
        }

    }


}

fun List<Cubic>.toPath(path: Path = Path(), scale: Float = 1f): Path {
    path.rewind()
    firstOrNull()?.let { first ->
        path.moveTo(first.anchor0X * scale, first.anchor0Y * scale)
    }
    for (bezier in this) {
        path.cubicTo(
            bezier.control0X * scale, bezier.control0Y * scale,
            bezier.control1X * scale, bezier.control1Y * scale,
            bezier.anchor1X * scale, bezier.anchor1Y * scale
        )
    }
    path.close()
    return path
}

class RoundedPolygonShape(
    private val polygon: RoundedPolygon
) : Shape {
    private val matrix = Matrix()
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = polygon.cubics.toPath()
        // below assumes that you haven't changed the default radius of 1f, nor the centerX and centerY of 0f
        // By default this stretches the path to the size of the container, if you don't want stretching, use the same size.width for both x and y.
        matrix.scale(size.width / 2f, size.height / 2f)
        matrix.translate(1f, 1f)
        path.transform(matrix)

        return Outline.Generic(path)
    }
}