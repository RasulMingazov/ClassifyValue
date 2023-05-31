package com.jeanbernad.classifyvalue.core.presentation.paint.pojo

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class PaintModel() : Parcelable {

    private val lines: SnapshotStateList<LineModel> = mutableStateListOf()
    private val paintColor: MutableState<Color> = mutableStateOf(Color.Black)
    private val paintWidth: MutableState<Dp> = mutableStateOf(12.dp)
    private val backgroundColor: MutableState<Color> = mutableStateOf(Color.White)

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(lines.toList())
        writeFloat(paintWidth.value.value)
        writeLong(paintColor.value.value.toLong())
        writeLong(backgroundColor.value.value.toLong())
    }

    constructor(parcel: Parcel) : this() {
        parcel.readTypedList(lines, LineModel.CREATOR)
        paintWidth.value = parcel.readFloat().dp
        paintColor.value = Color(parcel.readLong())
        backgroundColor.value = Color(parcel.readLong())
    }

    fun drawLines(scope: DrawScope) {
        val linesPaths = lines.map { it.linePath() }
        for (path in linesPaths) {
            scope.drawPath(
                path,
                color = paintColor.value,
                style = Stroke(
                    width = paintWidth.value.value,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }
    }

    fun addLine(offset: Offset) {
        val point = PointModel.Item(offset)
        val line = LineModel().apply { addPoint(point) }
        lines.add(line)
    }

    fun addPoint(offset: Offset) {
        val point = PointModel.Item(offset)
        lines[lines.lastIndex].addPoint(point)
    }

    fun updatePaintWidth(width: Dp) {
        paintWidth.value = width
    }

    fun updatePaintColor(color: Color) {
        paintColor.value = color
    }

    fun updateBackgroundColor(color: Color) {
        backgroundColor.value = color
    }

    fun backgroundColor() = backgroundColor.value

    fun reset() {
        lines.clear()
    }

    companion object CREATOR : Parcelable.Creator<PaintModel> {
        override fun createFromParcel(parcel: Parcel): PaintModel {
            return PaintModel(parcel)
        }

        override fun newArray(size: Int): Array<PaintModel?> {
            return arrayOfNulls(size)
        }
    }

}