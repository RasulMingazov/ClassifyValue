package com.jeanbernad.classifyvalue.core.presentation.paint.pojo

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Path

class LineModel() : Parcelable {

    private val points: SnapshotStateList<PointModel.Item> = mutableStateListOf()

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(points.toList())
    }

    constructor(parcel: Parcel) : this() {
        parcel.readTypedList(this.points, PointModel.Item.CREATOR)
    }

    fun addPoint(point: PointModel.Item) = points.add(point)

    fun linePath(): Path = Path().apply {
        if (points.size > 1) {
            this.moveTo(points[0].x(), points[0].y())

            var oldPoint: PointModel = PointModel.Init
            for (i in 1 until points.size) {
                val newPoint: PointModel = points[i]
                if (oldPoint is PointModel.Item) {
                    val middlePoint = PointModel.Item(oldPoint, newPoint)
                    if (i == 1) this.lineTo(middlePoint.x(), middlePoint.y())
                    else this.quadraticBezierTo(newPoint.x(), newPoint.y(), middlePoint.x(), middlePoint.y())
                }
                oldPoint = newPoint
            }
            if (oldPoint is PointModel.Item) this.lineTo(oldPoint.x(), oldPoint.y())
        }
    }

    companion object CREATOR : Parcelable.Creator<LineModel> {
        override fun createFromParcel(parcel: Parcel): LineModel {
            return LineModel(parcel)
        }

        override fun newArray(size: Int): Array<LineModel?> {
            return arrayOfNulls(size)
        }
    }
}