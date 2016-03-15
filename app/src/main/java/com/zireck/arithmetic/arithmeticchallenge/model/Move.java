package com.zireck.arithmetic.arithmeticchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.zireck.arithmetic.arithmeticchallenge.model.enums.Operations;

public class Move implements Parcelable {

    private Operations operation;
    private double factor;
    private String text;
    private int result;

    public Move(Operations operation, double factor, String text, int result) {
        this.operation = operation;
        this.factor = factor;
        this.text = text;
        this.result = result;
    }

    public Operations getOperation() {
        return operation;
    }

    public double getFactor() {
        return factor;
    }

    public String getText() {
        return text;
    }

    public int getResult() {
        return result;
    }

    protected Move(Parcel in) {
        operation = (Operations) in.readValue(Operations.class.getClassLoader());
        factor = in.readDouble();
        text = in.readString();
        result = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(operation);
        dest.writeDouble(factor);
        dest.writeString(text);
        dest.writeInt(result);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Move> CREATOR = new Parcelable.Creator<Move>() {
        @Override
        public Move createFromParcel(Parcel in) {
            return new Move(in);
        }

        @Override
        public Move[] newArray(int size) {
            return new Move[size];
        }
    };
}
