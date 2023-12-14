@file:Suppress("NAME_SHADOWING")

package com.campbuddy.compose

import androidx.annotation.FloatRange
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campbuddy.toDp
import com.campbuddy.toPx
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

//TODO: FIX COLORS

@Composable
fun BoldStartText(modifier: Modifier = Modifier, a: String, b: String, fontSize: TextUnit = 15.sp) {
    Row(modifier = modifier) {
        Text(
            text = a,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = ColorScheme.primary
        )
        Text(text = b, fontSize = fontSize, color = ColorScheme.secondary)
    }
}

@Composable
fun LabeledSwitch(
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        label?.invoke()
        Text(
            "OFF",
            fontSize = 12.sp,
            color = ColorScheme.secondary,
            modifier = Modifier.padding(start = 8.dp)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = switchColors(),
            interactionSource = interactionSource,
            modifier = Modifier
                .wrapContentSize()
                .padding(0.dp)
        )
        Text("ON", fontSize = 12.sp, color = ColorScheme.secondary)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small
) {
    OutlinedTextField(
        enabled = enabled,
        readOnly = readOnly,
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth(),
        singleLine = singleLine,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines ?: 1,
        interactionSource = interactionSource,
        shape = shape,
        colors = editTextColors()
    )
}

@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    options: List<String> = listOf(),
    label: String = "",
    selected: Int,
    onClick: ((Int) -> Unit)
) {
    Column(modifier = modifier) {
        Text(text = label, fontSize = 15.sp, color = ColorScheme.primary)
        options.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 15.dp)
            ) {
                RadioButton(
                    selected = index == selected,
                    onClick = { onClick(index) },
                    modifier = Modifier
                        .width(20.dp)
                        .height(25.dp),
                    colors = radioButtonColors()
                )

                val annotatedText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = if (index == selected) ColorScheme.secondary else ColorScheme.tertiary,
                            fontSize = 15.sp
                        )
                    )
                    { append(item) }
                }

                ClickableText(
                    text = annotatedText,
                    onClick = { onClick(index) },
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}

@Composable
fun HorizontalRadioGroup(
    modifier: Modifier = Modifier,
    options: List<String> = listOf(),
    label: String = "",
    selected: Int,
    onClick: ((Int) -> Unit),
) {
    Row(
        modifier = modifier.padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 15.sp, color = ColorScheme.primary)
        options.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                RadioButton(
                    selected = index == selected,
                    onClick = { onClick(index) },
                    modifier = Modifier
                        .width(20.dp)
                        .height(25.dp),
                    colors = radioButtonColors()
                )

                val annotatedText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = if (index == selected) ColorScheme.primary else ColorScheme.secondary,
                            fontSize = 15.sp
                        )
                    )
                    { append(item) }
                }

                ClickableText(
                    text = annotatedText,
                    onClick = { onClick(index) },
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}

inline fun Modifier.nrClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Composable
fun LabeledCheckbox(
    label: @Composable (() -> Unit),
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Row(
        modifier = modifier.nrClickable(onClick = { onCheckedChange(!checked) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            checked = checked,
            enabled = enabled,
            interactionSource = interactionSource,
            colors = checkBoxColors(),
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.padding(5.dp))
        label()
    }
}

@Composable
inline fun FrameBox(
    modifier: Modifier = Modifier,
    a: String = "",
    b: String = "",
    crossinline content: @Composable () -> Unit
) {
    Column {
        BoldStartText(
            a = a,
            b = b,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, bottom = 3.dp, top = 15.dp)
        )

        Surface(
            modifier = modifier
                .fillMaxSize()
                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(10.dp))
                .padding(12.dp)
        ) {
            content()
        }
    }
}

@Composable
inline fun FrameBox(
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit,
    crossinline content: @Composable () -> Unit
) {
    Column {
        label()

        Surface(
            modifier = modifier
                .fillMaxSize()
                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(10.dp))
                .padding(12.dp)
        ) {
            content()
        }
    }
}

/**
 * Create round slider, pass list of colors to be its gradient stroke.
 */

@Composable
fun ArcSlider(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 360.0)
    angle: Double,
    @FloatRange(from = 0.0, to = 360.0)
    startAngle: Double = 0.0,
    @FloatRange(from = 0.0, to = 360.0)
    sweepAngle: Double = 180.0,
    onChange: (Double, Double) -> Unit = { _, _ -> },
    strokeCap: StrokeCap = StrokeCap.Round,
    strokeWidth: Float = 10.dp.toPx(),
    pointerDraw: ((Double) -> Unit)? = null,
    pointerStyle: DrawStyle = Fill,
    pointerColor: Color = Color.Black,
    pointerRadius: Float = 15.dp.toPx(),
    colorList: List<Color>
) {
    val brush by remember(startAngle, sweepAngle, colorList) {
        mutableStateOf(
            if (startAngle > (startAngle + sweepAngle) % 360) {
                var start = startAngle / 360
                val range = sweepAngle / 360
                val step = range / (colorList.size - 1)

                val colorList = colorList.toMutableList()
                val colors: MutableList<Pair<Float, Color>> = mutableListOf()

                while (start < 1 && colorList.isNotEmpty()) {
                    colors.add(Pair(start.toFloat(), colorList[0]))
                    colorList.removeAt(0)
                    start += step
                }

                if (colorList.isNotEmpty()) {
                    colors.add(Pair(1f, colorList[0]))

                    start -= 1

                    for (i in colorList.size - 1 downTo 0) {
                        colors.add(0, Pair((start + step * i).toFloat(), colorList.last()))
                        colorList.removeLast()
                    }
                }

                Brush.sweepGradient(*colors.toTypedArray())
            } else {
                val start = startAngle / 360
                val range = sweepAngle / 360
                val step = range / (colorList.size - 1)

                val colors = Array(colorList.size) {
                    Pair((start + step * it).toFloat(), colorList[it])
                }

                Brush.sweepGradient(*colors)
            }
        )
    }

    ArcSlider(
        modifier,
        angle,
        startAngle,
        sweepAngle,
        onChange,
        strokeCap,
        strokeWidth,
        pointerDraw,
        pointerStyle,
        pointerColor,
        pointerRadius,
        brush
    )
}

/**
 * Create round slider, pass brush to be used as stroke.
 */
@Composable
fun ArcSlider(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 360.0)
    angle: Double,
    @FloatRange(from = 0.0, to = 360.0)
    startAngle: Double = 0.0,
    @FloatRange(from = 0.0, to = 360.0)
    sweepAngle: Double = 360.0,
    onChange: (Double, Double) -> Unit = { _, _ -> },
    strokeCap: StrokeCap = StrokeCap.Round,
    strokeWidth: Float = 10.dp.toPx(),
    pointerDraw: ((Double) -> Unit)? = null,
    pointerStyle: DrawStyle = Fill,
    pointerColor: Color = Color.Black,
    pointerRadius: Float = 15.dp.toPx(),
    brush: Brush
) {
    var dragOffset by remember { mutableStateOf(Offset.Zero) }
    var pointerOffset by remember { mutableStateOf(Offset.Zero) }
    var radius by remember { mutableFloatStateOf(0f) }

    val endAngle by remember { mutableDoubleStateOf((startAngle + sweepAngle) % 360) }
    val midAngle by remember { mutableDoubleStateOf(endAngle + (360.0 - sweepAngle) / 2.0) }

    var isSliding by remember { mutableStateOf(false) }

    fun calculateAngle(d: Offset) {
        val x = d.x - radius
        val y = d.y - radius
        val c = sqrt((x * x + y * y).toDouble())

        var angle = Math.toDegrees(acos(x / c))
        if (y < 0) angle = 360 - angle

        //Keep in range
        if (sweepAngle != 360.0) {
            if (endAngle < startAngle) {
                when (angle) {
                    in endAngle..midAngle -> angle = endAngle
                    in midAngle..startAngle -> angle = startAngle
                }
            } else if (endAngle > startAngle) {
                if (midAngle > 360) {
                    val correctedMiddle = midAngle - 360
                    when (angle) {
                        in endAngle..360.0, in 0.0..correctedMiddle -> angle = endAngle
                        in correctedMiddle..startAngle -> angle = startAngle
                    }
                } else {
                    when (angle) {
                        in endAngle..midAngle -> angle = endAngle
                        in midAngle..360.0, in 0.0..startAngle -> angle = startAngle
                    }
                }
            }
        }

        onChange(angle, ((angle + 360 - startAngle) % 360) / sweepAngle)
    }

    Box(
        modifier = modifier
    ) {
        //Draw path
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    radius = (it.size.width / 2).toFloat()
                }
        ) {
            drawArc(
                brush = brush,
                startAngle = startAngle.toFloat(),
                sweepAngle = sweepAngle.toFloat(),
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = strokeCap)
            )
        }

        //Draw pointer hitbox
        Box(
            modifier = Modifier
                .absoluteOffset(dragOffset.x.toDp(), dragOffset.y.toDp())
                .size(pointerRadius.toDp() * 2)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            isSliding = true
                        },
                        onDrag = { change, _ ->
                            calculateAngle(dragOffset + change.position)
                            change.consume()
                        },
                        onDragEnd = {
                            isSliding = false
                            dragOffset = pointerOffset
                        }
                    )
                }
        )

        //Draw pointer
        Canvas(
            modifier = Modifier
                .absoluteOffset(pointerOffset.x.toDp(), pointerOffset.y.toDp()) //X
                .size(pointerRadius.toDp() * 2)
        ) {
            if (pointerDraw != null) pointerDraw(angle)
            else drawCircle(
                color = pointerColor,
                radius = pointerRadius,
                center = center,
                style = pointerStyle
            )
        }
    }

    val x = (radius + radius * cos(Math.toRadians(angle))).toFloat()
    val y = (radius + radius * sin(Math.toRadians(angle))).toFloat()
    pointerOffset = Offset(x, y) - Offset(pointerRadius, pointerRadius)
    if (!isSliding) dragOffset = pointerOffset
}

@Composable
fun BasicButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke = BorderStroke(2.dp, ColorScheme.secondary),
    contentPadding: PaddingValues = PaddingValues(13.dp),
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(enabled, role = Role.Button, onClick = onClick)
            .border(border, shape)
            .padding(contentPadding),
        contentAlignment = contentAlignment
    ) {
        content()
    }
}