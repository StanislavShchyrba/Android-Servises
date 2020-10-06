package com.example.sortingapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public enum SortingMethod implements Parcelable {
    BUBBLESORT {
        @Override
        public int getCaptionStringId() {
            return R.string.bubble_sort;
        }
    },
    QUICKSORT {
        @Override
        public int getCaptionStringId() {
            return R.string.quicksort;
        }
    },
    COUNTINGSORT {
        @Override
        public int getCaptionStringId() {
            return R.string.counting_sort;
        }
    };

    public int getCaptionStringId() {
        throw new AssertionError("getCaptionStringId() should be overridden!");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }

    public static final Creator<SortingMethod> CREATOR = new Parcelable.Creator<SortingMethod>() {
        @NonNull
        @Override
        public SortingMethod createFromParcel(Parcel source) {
            int ordinal = source.readInt();

            return SortingMethod.values()[ordinal];
        }

        @NonNull
        @Override
        public SortingMethod[] newArray(int size) {
            return new SortingMethod[size];
        }
    };
}
