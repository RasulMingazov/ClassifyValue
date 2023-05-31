package com.jeanbernad.classifyvalue.core.presentation.paint.pojo

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.geometry.Offset

abstract class PointModel {

    abstract fun x(): Float
    abstract fun y(): Float

    object Init : PointModel() {

        override fun x() = -1f

        override fun y() = -1f
    }

    class Item : PointModel, Parcelable {

        private var x: Float = -1f
        private var y: Float = -1f

        constructor()

        constructor(parcel: Parcel) : this() {
            this.x = parcel.readFloat()
            this.y = parcel.readFloat()
        }

        constructor(offset: Offset) : this() {
            this.x = offset.x
            this.y = offset.y
        }

        constructor(x: Float, y: Float) : this() {
            this.x = x
            this.y = y
        }

        constructor(oldPoint: PointModel, newPoint: PointModel) : this() {
            this.x = (oldPoint.x() + newPoint.x()) / 2f
            this.y = (oldPoint.y() + newPoint.y()) / 2f
        }

        override fun x() = x

        override fun y() = y

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeFloat(x)
            parcel.writeFloat(y)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Item> {
            override fun createFromParcel(parcel: Parcel): Item {
                return Item(parcel)
            }

            override fun newArray(size: Int): Array<Item?> {
                return arrayOfNulls(size)
            }
        }
    }
}